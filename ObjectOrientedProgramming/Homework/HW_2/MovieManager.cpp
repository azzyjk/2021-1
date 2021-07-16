#include "MovieManager.h"

MovieManager::MovieManager(string fileName) : cnt(0)
{
	cout << "MovieManager 생성자" << endl;
	int _raw, _col;
	string tmp, _name;
	ifstream fin(fileName);

	if (!fin.is_open()) {
		cerr << "File is not open!" << endl;
		exit(0);
	}

	fin >> num;
	this->movie = new Movie * [num];
	
	for (int i = 0; i < num; i++) {
		getline(fin, tmp);
		getline(fin, _name);
		fin >> _raw >> _col;

		this->movie[i] = new Movie(_name, _raw, _col);
		
		cnt += 1;
	}

	fin.close();
	cout << "MovieManger 생성자 끝" << endl;
}


MovieManager::MovieManager(MovieManager& manager)
{
	cout << "MovieManager 복사 생성자" << endl;
	this->num = manager.num;
	this->cnt = manager.cnt;
	this->movie = new Movie * [num];
	for (int i = 0; i < this->cnt; i++) {
		movie[i] = new Movie(*manager.movie[i]);
	}
}

MovieManager::MovieManager(MovieManager&& manager) noexcept :num(manager.num), cnt(manager.cnt), movie(manager.movie)
{
	cout << "MovieManager 이동 생성자" << endl;
	manager.movie = nullptr;
}

MovieManager::~MovieManager()
{
	cout << "MovieManager 소멸자" << endl;
	if (movie != nullptr) {
		for (int i = 0; i < cnt; i++) {
			delete movie[i];
		}
		delete[] movie;
		movie = nullptr;
	}
	
}

void MovieManager::startMenu()
{
	
	int select=0;
	while (select != 4) {
		cout << "201711425 정준원" << endl;
		cout << "=================== 해태 시네마 ======================" << endl;
		cout << "1) 영화 예매 2) 예매 취소 3) 예매 확인 4) 종료" << endl;
		cout << "메뉴를 선택하세요 : ";
		cin >> select;
		if (select == 1) {
			ticketing();
		}
		else if (select == 2) {
			cancelTicketing();
		}
		else if (select == 3) {
			checkTicketing();
		}
	}
	
	
}

int MovieManager::printMovie()
{
	int select;
	cout << left;
	for (int i = 0; i < cnt; i++) {
		cout << i + 1 << ") " << setw(20) << movie[i]->getName();
		if (movie[i]->remainSeats() == 0) cout << "매진" << endl;
		else cout << movie[i]->remainSeats() << "/" << movie[i]->getRaw() * movie[i]->getCol() << endl;;
	}
	cout << "예매할 영화를 선택하세요 : ";
	cin >> select;
	return select;
}

void MovieManager::ticketing()
{
	int selectMovie;
	char raw, col;
	int intRaw, intCol;

	selectMovie = printMovie()-1;
	movie[selectMovie]->printSeats();
	cout << "예매할 좌석을 선택하세요 ex) A 1, B 2) : ";
	cin >> raw >> col;
	intRaw = static_cast<int>(raw - 'A');
	intCol = static_cast<int>(col - '1');
	if (movie[selectMovie]->reserve(intRaw, intCol) == false) cout << "이미 예약된 자리입니다!" << endl;
	else {
		cout << "예약번호 : " << selectMovie << raw << col << endl;
	}
}

void MovieManager::cancelTicketing()
{
	char movieNum, raw, col;
	int intMovieNum, intRaw, intCol;
	cout << "=================== 예약  취소 ======================" << endl;
	cout << "예약번호를 입력하세요 : ";
	cin >> movieNum >> raw >> col;

	intMovieNum = static_cast<int>(movieNum - '0');
	intRaw = static_cast<int>(raw - 'A');
	intCol = static_cast<int>(col - '1');

	if (movie[intMovieNum]->isEmpty(intRaw, intCol)) cout << "예약번호가 존재하지 않습니다." << endl;
	else {
		movie[intMovieNum]->cancelReserve(intRaw, intCol);
		cout << "예약이 취소되었습니다." << endl;
	}
}

void MovieManager::checkTicketing()
{
	char movieNum, raw, col;
	int intMovieNum, intRaw, intCol;
	cout << "=================== 예약  확인 ======================" << endl;
	cout << "예약번호를 입력하세요 : ";
	cin >> movieNum >> raw >> col;

	intMovieNum = static_cast<int>(movieNum - '0');
	intRaw = static_cast<int>(raw - 'A');
	intCol = static_cast<int>(col - '1');

	if (movie[intMovieNum]->isEmpty(intRaw, intCol)) cout << "예약번호가 존재하지 않습니다." << endl;
	else {
		cout << "예매하신 영화 : " << movie[intMovieNum]->getName() << endl;;
		cout << "예매하신 좌석 : " << raw << col << endl;
		cout << "=================== 영화 예매 좌석 =====================" << endl;
		movie[intMovieNum]->printSeats(intRaw, intCol);
	}
}
