#include "Student.h"

Student::Student()
	:Student("NULL", 0)
{
}

Student::Student(const string& _name, const int& _score)
	:name(_name), score(_score)
{
}

bool Student::operator==(const string& _name)
{
	return this->name == _name;
}

bool Student::operator==(const Student& std)
{
	return ((this->name == std.name) && (this->score == std.score));
}

ostream& operator<<(ostream& out, const Student& _std)
{
	out << _std.getName() << " " << _std.getScore();
	return out;
}
