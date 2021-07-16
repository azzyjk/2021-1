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
	return "��ǰ�̸� : " + this->name + ", ��ǰ���� : " + to_string(this->price) + ", ������ : " + to_string(this->count);
}

void Product::addCount(int _number)
{
	this->count += _number;
}

void Product::buyThis(int money)
{
	if (money >= this->price) {
		cout << "��ǰ�� �ܾ�" << money - this->price << "���� ��������" << endl;
		this->count -= 1;
	}
	else {
		cout << "�ܾ��� �����մϴ�" << endl;
	}
	
}

