#pragma once
#include<iostream>

using namespace std;

class Movie
{
private:
	string name;
	int **seats, raw, col;
public:
	Movie() = delete;
	Movie(string _name, int _raw, int _col);
	Movie(Movie& movie);
	Movie(Movie&& movie) noexcept;
	~Movie();
	string getName() {
		return this->name;
	};
	int getRaw() {
		return this->raw;
	}
	int getCol() {
		return this->col;
	}
	void setName(string _name);
	int remainSeats();
	void printSeats();
	void printSeats(int selRaw, int selCol);
	bool reserve(int selRaw, int selCol);
	bool isEmpty(int selRaw, int selCol);
	void cancelReserve(int selRaw, int selCol);
};

