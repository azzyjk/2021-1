#include<iostream>
#include<string>
#include<fstream>
#include<iomanip>

#ifdef _DEBUG
	#ifndef DBG_NEW
		#define DBG_NEW new (_NORMAL_BLOCK, __FILE__, __LINE__)
		#define new DBG_NEW
	#endif
#endif

using namespace std;

typedef struct movie {
	string name;
	int** seats, raw, col;
} Movie;

int printMenu();
Movie* loadMovies(int& num);
int printMovies(Movie* movies, int num);
void resetArr(Movie* movies, int num);
int countArr(Movie* movies, int select);
void printArr(Movie* movies, int select);
void ticketing(Movie* (&movies), int select);
void cancelTicketing(Movie* (&movies));
void checkTicketing(Movie* movies);
void printSeat(Movie* movies, int movieNum, int raw, int col);

int printMenu() {
	int select;
	cout << "=================== 해태 시네마 ======================" << endl;
	cout << "1) 영화 예매 2) 예매 취소 3) 예매 확인 4) 종료" << endl;
	cout << "메뉴를 선택하세요 : ";
	cin >> select;
	return select;
}

Movie* loadMovies(int& num) {
	string tmp;
	Movie* movies = nullptr;

	ifstream fin("movie.txt");

	if (!fin.is_open()) {
		cerr << "File is not open!" << endl;
		exit(0);
	}

	fin >> num;
	movies = new Movie[num];
	// raw col
	for (int i = 0; i < num; i++) {
		getline(fin, tmp); // '\n' 제거
		getline(fin, movies[i].name); // 영화 이름 읽기
		fin >> movies[i].raw >> movies[i].col; // raw col 읽기
		
		movies[i].seats = new int* [movies[i].raw];
		for (int j = 0; j < movies[i].raw; j++) movies[i].seats[j] = new int[movies[i].col];
	}
	return movies;

}

int printMovies(Movie* movies, int num) {
	int select;
	cout << left;
	for (int i = 0; i < num; i++) {
		cout << i + 1 << ") " << setw(20) << movies[i].name;
		if (countArr(movies, i) == 0) cout << "매진" << endl;
		else cout << countArr(movies, i) << "/" << movies[i].raw * movies[i].col << endl;
	}
	cout << "예매할 영화를 선택하세요 : ";
	cin >> select;
	return select;
}

void resetArr(Movie* movies, int num) {
	for (int i = 0; i < num; i++) {
		for (int j = 0; j < movies[i].raw; j++) {
			for (int k = 0; k < movies[i].col; k++) {
				movies[i].seats[j][k] = 0;
			}
		}
	}
}

int countArr(Movie* movies, int select) {
	int sum = 0;
	for (int i = 0; i < movies[select].raw; i++) {
		for (int j = 0; j < movies[select].col; j++) if (movies[select].seats[i][j] == 0) sum += 1;
		
	}
	return sum;
}

void printArr(Movie* movies, int select) {
	cout << "=================== 영화  예매 ======================" << endl;
	for (int i = 0; i < movies[select].col; i++) cout << "\t" << i + 1;
	cout << endl;
	for (int i = 0; i < movies[select].raw; i++) {
		cout << static_cast<char>('A' + i) << "\t";
		for (int j = 0; j < movies[select].col; j++) {
			cout << (movies[select].seats[i][j] ? "●" : "○") << "\t";
		}
		cout << endl;
	}
}

void ticketing(Movie* (&movies), int select) {
	char raw, col;
	int intRaw, intCol;
	printArr(movies, select);
	cout << "예매할 좌석을 선택하세요 ex) A 1, B 2) : ";
	cin >> raw >> col;
	intRaw = static_cast<int>(raw - 'A');
	intCol = static_cast<int>(col - '1');
	if (movies[select].seats[intRaw][intCol] == 1) cout << "이미 예약된 자리입니다!" << endl;
	else if (movies[select].seats[intRaw][intCol] == 0) {
		movies[select].seats[intRaw][intCol] = 1;
		cout << "예약번호 : " << select << raw << col << endl;
	}
}

void cancelTicketing(Movie* (&movies)) {
	char movieNum, raw, col;
	int intMovieNum, intRaw, intCol;
	cout << "=================== 예약  취소 ======================" << endl;
	cout << "예약번호를 입력하세요 : ";
	cin >> movieNum >> raw >> col;

	intMovieNum = static_cast<int>(movieNum-'0');
	intRaw = static_cast<int>(raw - 'A');
	intCol = static_cast<int>(col - '1');
	
	if (movies[intMovieNum].seats[intRaw][intCol] == 0) cout << "예약번호가 존재하지 않습니다." << endl;
	else {
		movies[intMovieNum].seats[intRaw][intCol] = 0;
		cout << "예약이 취소되었습니다." << endl;
	}
}

void checkTicketing(Movie* movies) {
	char movieNum, raw, col;
	int intMovieNum, intRaw, intCol;
	cout << "=================== 예약  확인 ======================" << endl;
	cout << "예약번호를 입력하세요 : ";
	cin >> movieNum >> raw >> col;

	intMovieNum = static_cast<int>(movieNum - '0');
	intRaw = static_cast<int>(raw - 'A');
	intCol = static_cast<int>(col - '1');

	if (movies[intMovieNum].seats[intRaw][intCol] == 0) cout << "예약번호가 존재하지 않습니다." << endl;
	else {
		cout << "예매하신 영화 : " << movies[intMovieNum].name << endl;;
		cout << "예매하신 좌석 : " << raw << col << endl;
		cout << "=================== 영화 예매 좌석 =====================" << endl;
		printSeat(movies, intMovieNum, intRaw, intCol);
	}
}

void printSeat(Movie* movies, int movieNum, int raw, int col) {
	for (int i = 0; i < movies[movieNum].col; i++) cout << "\t" << i + 1;
	cout << endl;
	for (int i = 0; i < movies[movieNum].raw; i++) {
		cout << static_cast<char>('A' + i) << "\t";
		for (int j = 0; j < movies[movieNum].col; j++) {
			if (i == raw && j == col) cout << "★" << '\t';
			else cout << "○" << '\t';
		}
		cout << endl;
	}
}

void deleteMovies(Movie* (&movies), int num) {
	for (int i = 0; i < num; i++) {
		for (int j = 0; j < movies[i].raw; j++) {
			delete[] movies[i].seats[j];
		}
		delete[] movies[i].seats;
		movies[i].seats = nullptr;
	}
	delete[] movies;
	movies = nullptr;
}

int main() {
	_CrtSetDbgFlag(_CRTDBG_ALLOC_MEM_DF | _CRTDBG_LEAK_CHECK_DF);
	
	int num;
	Movie* movies = nullptr;

	movies = loadMovies(num);
	resetArr(movies, num);

	int select=0, selectMovie;

	while (select != 4) {
		cout << "201711425 정준원" << endl;
		select = printMenu();
		if (select == 1) {
			selectMovie = printMovies(movies, num);
			ticketing(movies, selectMovie-1);
		}
		else if (select == 2) {
			cancelTicketing(movies);
		}
		else if (select == 3) {
			checkTicketing(movies);
		}
		else if (select == 4) {
			deleteMovies(movies, num);
		}
	}

	return 0;
}