#include<iostream>
#include<iomanip>
#include<sstream> // to_string function

using std::cout;
using std::cin;
using std::endl;
using std::setw;
using std::to_string;

namespace jjw {
	void printName() {
		cout << "학번 : 201711425 이름: 정준원" << endl;
	}
}

void printTable(int start, int end) {
	for (int j = 1; j <= 9; j++) {
		for (int i = start; i <= end; i++) {
			if (i != start) cout << setw(14);
			else cout << setw(9);
			// to_string 사용
			cout << to_string(i) + " * " + to_string(j) + " =" << setw(3) << i * j;
		}
		cout << endl;
	}
}

int main() {
	jjw::printName();
	int start, end;
	char select;

	cout << "구구단의 출력을 원하는 범위를 입력하세요(시작, 끝) : ";
	cin >> start >> end;
	if (start > end) {
		cout << "두 번째 입력 숫자가 더 크므로, 시작과 끝의 값을 바꿔서 출력 할까요(y/n)? ";
		cin >> select;
		if (select == 'y') printTable(end, start);
		else if (select == 'n') return 0;
	}
	else printTable(start, end);
	return 0;
}