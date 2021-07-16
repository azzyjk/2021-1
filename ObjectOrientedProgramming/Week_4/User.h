#pragma once

#include<iostream>
#include "Lamp.h"

using std::string;
using std::endl;
using std::cout;

class User
{
private:
	string name="";

public:
	void turnOnOff(Lamp& lamp);
	void changeLight(Lamp& lamp);
	void registName(const string str);
};