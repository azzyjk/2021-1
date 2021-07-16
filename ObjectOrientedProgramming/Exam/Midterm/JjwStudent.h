#pragma once
#include<iostream>

using namespace std;

class JjwStudent
{
private:
	string name;
	double grade;
public:
	JjwStudent() = delete;
	JjwStudent(string _name, double _grade);
	JjwStudent(const JjwStudent& jjw);
	friend ostream& operator<<(ostream& out, const JjwStudent& jjw);
	string getName() {
		return this->name;
	}
	double getGrade() {
		return this->grade;
	}
};

