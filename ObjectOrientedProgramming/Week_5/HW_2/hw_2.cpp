#include<iostream>
#include<ctime>
#include"Dice.h"
#include"Player.h"

using namespace std;

int main() {
	cout << "201711425 정준원" << endl;

	srand((unsigned)time(NULL));

	Dice dice1, dice2;
	Player player1("김길동"), player2("홍길동");

	player1.roll(dice1, dice2);
	player2.roll(dice1, dice2);

	if (player1.getTotal() > player2.getTotal()) {
		cout << player1.getName() << " is win!! " << player1.getTotal() << " : " << player2.getTotal() << endl;
	}
	else if (player1.getTotal() < player2.getTotal()) {
		cout << player2.getName() << " is win!! " << player1.getTotal() << " : " << player2.getTotal() << endl;
	}
	else if (player1.getTotal() == player2.getTotal()) {
		cout << "Draw!! " << player1.getTotal() << " : " << player2.getTotal() << endl;
	}
	return 0;
}