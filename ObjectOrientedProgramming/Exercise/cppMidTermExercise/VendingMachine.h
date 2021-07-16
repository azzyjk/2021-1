#pragma once
#include<iostream>
#include"Product.h"

using namespace std;

class VendingMachine
{
private:
	Product** products;
	int capacity;
	int count;
	int manageNumber;
public:
	VendingMachine(int _num);
	VendingMachine(int _num, int _manageNumber);
	~VendingMachine();
	void showVending();
	void input();
	void output();
};

