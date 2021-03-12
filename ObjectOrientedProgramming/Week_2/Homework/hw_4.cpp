#include<iostream>

using namespace std;

const int diceCount = 10000;

int main() {
	cout << "201711425 정준원\n";

	srand((unsigned int)time(NULL));
	
	int dice[6] = {};
	int diceRes;
	for (int i = 0; i < diceCount; i++) {
		diceRes = (rand() % 6) + 1;
		dice[diceRes - 1] += 1;
	}

	for (int i = 0; i < 6; i++) {
		cout << "숫자 " << i + 1 << " : " << dice[i] << endl;
	}
	return 0;
}