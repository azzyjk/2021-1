#pragma once
#include "Movie.h"
#include<fstream>
#include<string>
#include<iomanip>

using namespace std;

class MovieManager
{
private:
	Movie** movie;
	int num;
	int cnt;
public:
	MovieManager() = delete;
	MovieManager(string fileName);
	MovieManager(MovieManager& manager);
	MovieManager(MovieManager&& manager) noexcept;
	~MovieManager();
	void startMenu();
	int printMovie();
	void ticketing();
	void cancelTicketing();
	void checkTicketing();
};

