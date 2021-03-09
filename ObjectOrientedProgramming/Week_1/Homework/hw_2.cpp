#include<iostream>
#include<iomanip>

using std::cout;
using std::endl;
using std::setw;

namespace jjw {
	void printName() {
		cout << "학번 : 201711425 이름: 정준원" << endl;
	}
}

int main() {
	jjw::printName();
	for (int j = 1; j <= 9; j++) {
		for (int i = 2; i <= 9; i++) {
			if (i != 2) cout << setw(5);
			else cout << setw(2);
			cout << i << setw(2) << "*" << setw(2) << j << setw(2) << "=" << setw(3) << i * j;
		}
		cout << endl;
	}
	return 0;
}