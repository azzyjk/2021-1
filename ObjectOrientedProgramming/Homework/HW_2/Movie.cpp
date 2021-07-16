#include "Movie.h"

Movie::Movie(string _name, int _raw, int _col)
{
	cout << "Movie 생성자" << endl;
	this->name = _name;
	this->raw = _raw;
	this->col = _col;
	this->seats = new int* [this->raw];
	for (int i = 0; i < this->raw; i++) {
		this->seats[i] = new int [this->col](); // 0으로 초기화
	}
}

Movie::Movie(Movie& movie):Movie(movie.name, movie.raw, movie.col)
{
	cout << "Movie 복사 생성자" << endl;
	for (int i = 0; i < this->raw; i++) {
		for (int j = 0; j < this->col; j++) {
			this->seats[i][j] = movie.seats[i][j];
		}
	}
}

Movie::Movie(Movie&& movie) noexcept :name(movie.name), seats(movie.seats), raw(movie.raw), col(movie.col)
{
	cout << "Movie 이동 생성자" << endl;
	movie.seats = nullptr;

}

Movie::~Movie()
{
	cout << "Movie 소멸자" << endl;
	if (seats != nullptr) {
		for (int i = 0; i < this->raw; i++) delete[] this->seats[i];
		delete[] this->seats;
	}
}

void Movie::setName(string _name)
{
	this->name = _name;
}

int Movie::remainSeats()
{
	int count = 0;
	for (int i = 0; i < raw; i++) {
		for (int j = 0; j < col; j++) {
			if (seats[i][j] == 0) count += 1;
		}
	}
	return count;
}

void Movie::printSeats()
{
	cout << "=================== 영화  예매 ======================" << endl;
	for (int i = 0; i < col; i++) cout << "\t" << i + 1;
	cout << endl;
	for (int i = 0; i < raw; i++) {
		cout << static_cast<char>('A' + i) << "\t";
		for (int j = 0; j < col; j++) {
			cout << (seats[i][j] ? "●" : "○") << "\t";
		}
		cout << endl;
	}
}

void Movie::printSeats(int selRaw, int selCol)
{
	for (int i = 0; i < col; i++) cout << "\t" << i + 1;
	cout << endl;
	for (int i = 0; i < raw; i++) {
		cout << static_cast<char>('A' + i) << "\t";
		for (int j = 0; j < col; j++) {
			if (i == selRaw && j == selCol) cout << "★" << '\t';
			else cout << "○" << '\t';
		}
		cout << endl;
	}
}

bool Movie::reserve(int selRaw, int selCol)
{
	if (seats[selRaw][selCol] == 1) return false;
	else {
		seats[selRaw][selCol] = 1;
	}
	return true;
}

bool Movie::isEmpty(int selRaw, int selCol)
{
	if (seats[selRaw][selCol] == 1) return false;
	return true;
}

void Movie::cancelReserve(int selRaw, int selCol)
{
	seats[selRaw][selCol] = 0;
}

