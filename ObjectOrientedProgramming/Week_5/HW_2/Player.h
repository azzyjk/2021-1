#pragma once
#include<iostream>
#include"Dice.h"

using namespace std;

class Player
{
private:
	string name;
	int total;
public:
	Player() = delete; // private에 놓는걸로 제한도 가능
	Player(const string& _name);
	void setName(const string& _name);
	string getName();
	void roll(Dice& dice1, Dice& dice2);
	int getTotal();
};

