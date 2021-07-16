#include "Dice.h"
#include<iostream>

Dice::Dice():faceValue(0) {

}
void Dice::roll() {
	faceValue = (rand() % 6) + 1;
}

int Dice::getFaceValue() {
	return faceValue;
}