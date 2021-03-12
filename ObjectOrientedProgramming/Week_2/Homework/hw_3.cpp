#include<iostream>

using namespace std;

int main() {
	cout << "201711425 정준원\n";
	int score[] = {10, 5, 8, 20, 12};

	// for (auto i : score) 로 각 배열의 요소에 접근하는 방식도 가능
	for (int i = 0; i < size(score); i++) {
		cout << "번호 : " << i << " : ";
		for (int j = 0; j < score[i]; j++) {
			cout << '*';
		}
		cout << endl;
	}
	return 0;
}