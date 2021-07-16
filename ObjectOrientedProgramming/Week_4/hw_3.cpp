#include<iostream>
#include "Lamp.h"
#include "User.h"

using namespace std;

int main() {
	User user;
	Lamp lamp;

	user.registName("201711425 정준원");
	user.turnOnOff(lamp);
	user.changeLight(lamp);

	user.turnOnOff(lamp);
	user.changeLight(lamp);
	user.changeLight(lamp);
	user.changeLight(lamp);
	return 0;
}