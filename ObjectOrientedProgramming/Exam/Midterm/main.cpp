#include<iostream>
#include"JjwStudent.h"
#include"JjwScholarship.h"

#ifdef _DEBUG
#ifndef DBG_NEW
#define DBG_NEW new (_NORMAL_BLOCK, __FILE__, __LINE__)
#define new DBG_NEW
#endif
#endif

using namespace std;

int main() {
	cout << "201711425 정준원" << endl;
	_CrtSetDbgFlag(_CRTDBG_ALLOC_MEM_DF | _CRTDBG_LEAK_CHECK_DF);
	JjwStudent greenjoa("정준원", 5.5);
	cout << greenjoa << endl;
	cout << endl;
	JjwScholarship scholarship1("건국장학금", 3);

	scholarship1.apply(new JjwStudent("정준원", 4.5));
	scholarship1.apply(new JjwStudent("김준원", 2.5));
	scholarship1.apply(new JjwStudent("고준원", 3.5));
	scholarship1.apply(new JjwStudent("이준원", 1.5));
	scholarship1.apply(new JjwStudent("최준원", 5.5));
	cout << endl;

	cout << scholarship1 << endl;
	cout << endl;

	JjwScholarship scholarship2(scholarship1);
	cout << scholarship2 << endl;
	cout << endl;

	JjwScholarship scholarship3(move(scholarship2));
	cout << scholarship3 << endl;
	cout << endl;
	return 0;
}