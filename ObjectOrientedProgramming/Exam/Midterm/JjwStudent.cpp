#include "JjwStudent.h"

JjwStudent::JjwStudent(string _name, double _grade)
{
	this->name = _name;
	this->grade = _grade;
}

JjwStudent::JjwStudent(const JjwStudent& jjw)
{
	this->name = jjw.name;
	this->grade = jjw.grade;
}

ostream& operator<<(ostream& out, const JjwStudent& jjw)
{
	out << "--------------------------------" << endl;
	out << "이름 : " << jjw.name << "\n학점 : " << jjw.grade;
	return out;
}
