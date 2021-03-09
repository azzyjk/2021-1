#include<iostream>
#include<iomanip>

using std::cout;
using std::cin;
using std::endl;
using std::setw;

namespace jjw {
	void printName() {
		cout << "학번 : 201711425 이름: 정준원" << endl;
	}
}

int main() {
	jjw::printName();
	int start, end;

	cout << "구구단의 출력을 원하는 범위를 입력하세요(시작, 끝) : ";
	cin >> start >> end;
	for (int j = 1; j <= 9; j++) {
		for (int i = start; i <= end; i++) {
			if (i != start) cout << setw(5);
			else cout << setw(2);
			cout << i << setw(2) << "*" << setw(2) << j << setw(2) << "=" << setw(3) << i * j;
		}
		cout << endl;
	}
	return 0;
}