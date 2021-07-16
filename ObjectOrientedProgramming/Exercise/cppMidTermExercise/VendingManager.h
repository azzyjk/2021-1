#pragma once
#include<iostream>
#include<fstream>
#include"VendingMachine.h"

using namespace std;

class VendingManager
{
private:
	int buildingSize;
	int manageNum;
	int* buildingVending;
	int ** vendingArr;
	VendingMachine*** vendingList;
	
public:
	VendingManager(string filename);
	~VendingManager();
	void showVendingMachine();
	void install();
	void input();
	void output();
};

