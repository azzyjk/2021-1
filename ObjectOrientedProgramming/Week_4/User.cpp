#include "User.h"

void User::turnOnOff(Lamp& lamp)
{
	lamp.powerOnOff();
}

void User::changeLight(Lamp& lamp)
{
	lamp.changeLight();
}

void User::registName(const string str)
{
	name = str;
	cout << "Welcom " << name << endl;
}
