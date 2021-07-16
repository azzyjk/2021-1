#pragma once
#include<iostream>

using namespace std;
class Ticket
{
protected:
	int number;
	double price;
	double cost;
public :
	Ticket() = delete;
	Ticket(double _price);
	virtual ~Ticket();
	int getNumber() const {
		return this->number;
	}
	virtual double getPrice() const = 0;
	double getCost() const {
		return this->cost;
	}
	virtual void setPrice(const double& _price) = 0;
	virtual void show() const;
};

