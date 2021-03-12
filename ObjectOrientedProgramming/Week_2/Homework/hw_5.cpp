#include<iostream>

using namespace std;

constexpr int raw = 6;
constexpr int col = 4;

bool seats[raw][col] = {};

void printSeats() {
	for (int i = 1; i <=col; i++) {
		cout << "\t" << i;
	}
	cout << endl;
	for (int i = 0; i < raw; i++) {
		cout << static_cast<char>(i + 'A');
		for (int j = 0; j < col; j++) {
			cout << "\t" << (seats[i][j]? "●" : "○");
		}
		cout << endl;
	}
	cout << endl;
	cout << "좌석을 선택하세요 (예, A1), 종료 (qq)" << endl;
}

int main() {
	cout << "201711425 정준원\n";
	char x = '\0', y{};
	int intx, inty;

	while (x != 'q' && y != 'q') {
		printSeats();
		cin >> x >> y;
		if (x == 'q' && y == 'q') continue;

		intx = static_cast<int>(x - 'A');
		inty = static_cast<int>(y - '1');

		if (intx < 0 || intx > raw-1 || inty < 0 || inty > col-1) cout << "좌석이 아닙니다." << endl;
		else if (seats[intx][inty] == false) {
			seats[intx][inty] = true;
			cout << "예약완료" << endl;
		}
		else if (seats[intx][inty] == true) cout << "예약 불가" << endl;
	}

	cout << "프로그램을 종료합니다." << endl;
	return 0;
}