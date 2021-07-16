#pragma once
#include<iostream>
#include<string>

using namespace std;

class Salesman
{
private:
	string name;
	double sales;
public:
	Salesman();
	Salesman(const string& _name);
	Salesman(const string& _name, double _sales);
	void setName(string _name);
	void readInput();
	string getSalesmanInfo();
	double getSales();
	
};

