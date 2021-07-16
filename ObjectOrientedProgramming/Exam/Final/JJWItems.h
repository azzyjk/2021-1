#pragma once
#include<iostream>
#include<vector>

using namespace std;

class JJWItems
{
protected:
	string registNumber;
	string category;
	int num;
	int price;
	vector<string> buyer;
public:
	JJWItems() = delete;
	JJWItems(string _registNumber, string _category, int _num, int _price);
	void buy(string buyerName);
	virtual int getPrice() = 0;
	virtual void show() const;
	virtual string getType() = 0;
	virtual string getCategory() = 0;
	bool operator < (const JJWItems& items2);
};

ostream& operator<<(ostream& out, const JJWItems& _items);