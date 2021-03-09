#include<iostream>

using std::cout;
using std::cin;
using std::endl;

namespace jjw {
	void printName() {
		cout << "학번 : 201711425 이름 : 정준원" << endl;
	}
}

int main() {
	jjw::printName();
	return 0;
}