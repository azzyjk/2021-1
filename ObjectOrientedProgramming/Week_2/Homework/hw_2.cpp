#include<iostream>

using namespace std;

void printMenu() {
	cout << "1) 아메리카노(100원) 2) 카페라떼(200원) 3) 결제 4) 종료\n 선택하세요 : ";
}

void resetArr(int *arr, int size) {
	for (int i = 0; i < size; i++) {
		arr[i] = 0;
	}
}

int main() {
	cout << "201711425 정준원\n";

	int select = { 0 }; // 유니폼 초기화 : 축소 변환을 방지 (에러 발생) 3.14 를 int에 넣을 때
	int buyArr[2] = {};

	while (select != 4) {
		printMenu();
		cin >> select;
		if (select == 1) {
			buyArr[0] += 1;
			cout << "아메리카노를 주문하셨습니다.\n\n";
		}
		else if (select == 2) {
			buyArr[1] += 1;
			cout << "카페라떼를 주문하셨습니다.\n\n";
		}
		else if (select == 3) {
			cout << "==================\n \t결제 금액\n==================\n";
			cout << "아메리카노 " << buyArr[0] << "잔 " << buyArr[0] * 100 << "원\n";
			cout << "카페라떼 " << buyArr[1] << "잔 " << buyArr[1] * 200 << "원\n";
			cout << "==================\n 총 " << (buyArr[0] * 100) + (buyArr[1] * 200) << "원\n==================\n";
			cout << "결제가 완료 되었습니다.\n";
			resetArr(buyArr, size(buyArr));
		}
		else if (select == 4) break;
	}
	return 0;
}