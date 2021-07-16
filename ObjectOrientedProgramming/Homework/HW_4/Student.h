#pragma once
#include <iostream>

using namespace std;

class Student
{
private:
	string name;
	int score;
public:
	Student();
	~Student() = default;
	Student(const string& _name, const int& _score);
	string getName() const {
		return this->name;
	}
	int getScore() const {
		return this->score;
	}
	
	bool operator==(const string& _name);
	bool operator==(const Student& std);
};

ostream& operator<<(ostream& out, const Student& _std);
