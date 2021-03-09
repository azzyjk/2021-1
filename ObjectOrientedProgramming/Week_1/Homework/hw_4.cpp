#include<iostream>
#include<iomanip>
#include<sstream>

using std::cout;
using std::cin;
using std::endl;
using std::setw;
using std::stringstream;

namespace jjw {
	void printName() {
		cout << "학번 : 201711425 이름 : 정준원" << endl;
	}
}

int main() {
	jjw::printName();
	int start, end;
	stringstream ss;

	cout << "구구단의 출력을 원하는 범위를 입력하세요(시작 , 끝) : ";
	cin >> start >> end;
	if (start > end) cout << "두 번째 입력 숫자가 더 커야 합니다." << endl;
	else {
		for (int j = 1; j <= 9; j++) {
			for (int i = start; i <= end; i++) {
				if (i != start) cout << setw(14);
				else cout << setw(9);
				// stringstream 사용
				ss << i << " * " << j << " =";
				cout << ss.str() << setw(3) << i * j;
				ss.str(""); // 비워주지 않으면 뒤에 추가됨
			}
			cout << endl;
		}
	}
	return 0;
}