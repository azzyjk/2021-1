#include "JjwScholarship.h"

JjwScholarship::JjwScholarship(string _name, int _capacity)
{
	this->scholarName = _name;
	this->capacity = _capacity;
	this->count = 0;
	this->students = new JjwStudent * [this->capacity];
}

JjwScholarship::JjwScholarship(const JjwScholarship& jjwScholar)
	:JjwScholarship(jjwScholar.scholarName, jjwScholar.capacity)
{
	this->count = jjwScholar.count;
	for (int i = 0; i < this->count; i++) {
		this->students[i] = new JjwStudent(*jjwScholar.students[i]);
	}
}

JjwScholarship::JjwScholarship(JjwScholarship&& jjwScholar) noexcept :
	scholarName(jjwScholar.scholarName), capacity(jjwScholar.capacity), count(jjwScholar.count),
	students(jjwScholar.students)
{
	jjwScholar.students = nullptr;
}

JjwScholarship::~JjwScholarship()
{
	if (this->students != nullptr) {
		for (int i = 0; i < this->count; i++) {
			delete this->students[i];
		}
		delete[] students;
		this->students = nullptr;
	}

}

void JjwScholarship::apply(JjwStudent*&&jjw)
{
	double nowGrade = jjw->getGrade();
	double minGrade = 100.0;
	int index = 0;
	bool isChange = false;

	if (this->count < this->capacity) {
		students[count] = new JjwStudent(jjw->getName(), jjw->getGrade());
		count += 1;
		// bubble sorting
		for (int i = 0; i < this->count-1; i++) {
			for (int j = 0; j < this->count-i-1; j ++) {
				if (this->students[j]->getGrade() < this->students[j + 1]->getGrade()) {
					JjwStudent* temp = this->students[j];
					this->students[j] = this->students[j + 1];
					this->students[j + 1] = temp;
				}
			}
		}
		cout << "신청완료" << endl;
	}
	else {
		for (int i = 0; i < this->count; i++) {
			if (students[i]->getGrade() < nowGrade) {
				if (minGrade > students[i]->getGrade()) {
					minGrade = students[i]->getGrade();
					index = i;
					isChange = true;
				}
			}
		}
		if (isChange) {
			delete this->students[index];
			students[index] = new JjwStudent(jjw->getName(), jjw->getGrade());
			//bubble sorting
			for (int i = 0; i < this->count - 1; i++) {
				for (int j = 0; j < this->count - i - 1; j++) {
					if (this->students[j]->getGrade() < this->students[j + 1]->getGrade()) {
						JjwStudent* temp = this->students[j];
						this->students[j] = this->students[j + 1];
						this->students[j + 1] = temp;
					}
				}
			}
			cout << "신청 완료" << endl;
		}
		else {
			cout << "신청하지 못함" << endl;
		}
	}
	delete jjw;
	jjw = nullptr;
}



ostream& operator<<(ostream& out, const JjwScholarship& jjwScholar)
{
	double average = 0;

	for (int i = 0; i < jjwScholar.count; i++) {
		average += jjwScholar.students[i]->getGrade();
	}


	out << "장학금명 : " << jjwScholar.scholarName << endl;
	out << "가능인원 : " << jjwScholar.capacity << endl;
	out << "평균학점 : " << average / jjwScholar.count << endl;
	out << "신청자수 : " << jjwScholar.count << endl;
	for (int i = 0; i < jjwScholar.count; i++) {
		out << *jjwScholar.students[i] << endl;
	}
	return out;
}
