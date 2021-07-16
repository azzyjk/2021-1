#include "Lamp.h"
#include<iostream>

using std::cout;
using std::endl;

void Lamp::powerOnOff()
{
	isOn = !isOn;
	if (isOn == true) cout << "Lamp is turn on!" << endl;
	else cout << "Lamp is turn off!" << endl;
}

void Lamp::changeLight()
{
	if (isOn == true) {
		illiminance += 1;
		if (illiminance > 3) illiminance = 1;
		cout << "Current illiminance : " << illiminance << endl;
	}
	else {
		cout << "Lamp is turn off...." << endl;
	}
}
