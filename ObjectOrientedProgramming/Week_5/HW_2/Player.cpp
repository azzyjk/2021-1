#include "Player.h"

Player::Player(const string& _name):name(_name), total(0){
}
void Player::setName(const string& _name) {
	name = _name;
}
string Player::getName() {
	return name;
}
void Player::roll(Dice& dice1, Dice& dice2) {
	cout << name << " is roll dice" << endl;
	dice1.roll();
	dice2.roll();
	total = dice1.getFaceValue() + dice2.getFaceValue();
}
int Player::getTotal() {
	return total;
}