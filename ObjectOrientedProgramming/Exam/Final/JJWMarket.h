#pragma once
#include<memory>

#include"JJWItems.h"

class JJWMarket
{
private:
	string marketName;
	vector<JJWItems*> items;
public:
	JJWMarket() = delete;
	JJWMarket(string _name);
	~JJWMarket();
	void addProduct(JJWItems* product);
	string getName() {
		return this->marketName;
	}
	vector<JJWItems*> getProduct();
};

