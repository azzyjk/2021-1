#pragma once
#include<iostream>
#include<string>

using namespace std;
class Product
{
private:
	string name;
	int price;
	int count;
public:
	Product(string _name, int _price);
	Product(string _name, int _price, int _count);
	string getProductInfo();
	string getName() {
		return this->name;
	}
	void addCount(int _number);
	void buyThis(int money);
};

