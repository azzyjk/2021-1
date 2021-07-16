#pragma once
#include"JjwStudent.h"

using namespace std;

class JjwScholarship
{
private:
	string scholarName;
	JjwStudent** students;
	int capacity;
	int count;
public:
	JjwScholarship() = delete;
	JjwScholarship(string _name, int _capacity);
	JjwScholarship(const JjwScholarship& jjwScholar);
	JjwScholarship(JjwScholarship&& jjwScholar) noexcept;
	~JjwScholarship();
	void apply(JjwStudent*&& jjw);
	friend ostream& operator<<(ostream& out, const JjwScholarship& jjwScholar);
};

