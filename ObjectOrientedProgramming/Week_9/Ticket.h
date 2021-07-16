#pragma once
#include<iostream>

using namespace std;
class Ticket
{
protected:
	int number;
	double price;
public :
	Ticket() = delete;
	Ticket(double _price);
	~Ticket();
	int getNumber() const {
		return this->number;
	}
	double getPrice() const {
		return this->price;
	}
	void setPrice(const double& _price) {
		this->price = _price;
	}
	void show() const;
};

