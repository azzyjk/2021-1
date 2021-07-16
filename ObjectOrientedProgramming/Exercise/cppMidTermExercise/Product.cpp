#include "Product.h"

Product::Product(string _name, int _price):Product(_name, _price, 0)
{

}

Product::Product(string _name, int _price, int _count)
{
	this->name = _name;
	this->price = _price;
	this->count = _count;
}

string Product::getProductInfo()
{
	return "제품이름 : " + this->name + ", 제품가격 : " + to_string(this->price) + ", 재고수량 : " + to_string(this->count);
}

void Product::addCount(int _number)
{
	this->count += _number;
}

void Product::buyThis(int money)
{
	if (money >= this->price) {
		cout << "제품과 잔액" << money - this->price << "원을 받으세요" << endl;
		this->count -= 1;
	}
	else {
		cout << "잔액이 부족합니다" << endl;
	}
	
}

