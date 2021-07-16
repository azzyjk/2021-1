#include "JJWItems.h"

JJWItems::JJWItems(string _registNumber, string _category, int _num, int _price)
	:registNumber(_registNumber), category(_category), num(_num), price(_price)
{
}

void JJWItems::buy(string buyerName)
{
	this->buyer.push_back(buyerName);
}

void JJWItems::show() const
{
	cout << "--------------------------" << endl;
	cout << "등록번호 : " << this->registNumber << endl;
	cout << "제품유형 : " << this->category << endl;
	cout << "제품개수 : " << this->num << endl;
	cout << "가격 : " << this->price << endl;
}

bool JJWItems::operator<(const JJWItems& items2)
{
	return this->price < items2.price;
}

ostream& operator<<(ostream& out, const JJWItems& _items)
{
	_items.show();
	return out;
}
