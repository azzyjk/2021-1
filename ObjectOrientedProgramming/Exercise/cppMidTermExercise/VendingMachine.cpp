#include "VendingMachine.h"

VendingMachine::VendingMachine(int _num)
{
	this->products = new Product * [_num];
	this->capacity = _num;
	this->count = 0;
}

VendingMachine::VendingMachine(int _num, int _manageNumber):VendingMachine(_num)
{
	this->manageNumber = _manageNumber;
}

VendingMachine::~VendingMachine()
{
	for (int i = 0; i < count; i++) {
		delete products[i];
	}
	delete[] products;
}

void VendingMachine::showVending()
{
	cout << "관리번호 ";
	cout << this->manageNumber;
	cout << " 자판기 판매 제품" << endl;
	cout << "======================" << endl;
	for (int i = 0; i < this->count; i++) {
		cout << i + 1 << "번) " << products[i]->getProductInfo() << endl;;
	}
	cout << "======================" << endl;
}

void VendingMachine::input() {
	string productName;
	int productPrice;
	int productCount;
	bool isExist = false;
	int index = 0;

	cout << "입고할 제품명 : ";
	cin >> productName;

	for (int i = 0; i < count; i++) {
		if (products[i]->getName() == productName) {
			isExist = true;
			index = i;
		}
	}

	if (isExist) {
		cout << "제품 개수 : ";
		cin >> productCount;
		products[index]->addCount(productCount);
		cout << "입고가 완료 되었습니다." << endl;
	}
	else {
		cout << "제품 가격과 개수 : ";
		cin >> productPrice >> productCount;
		products[this->count] = new Product(productName, productPrice, productCount);
		this->count += 1;
		cout << "입고가 완료 되었습니다." << endl;
	}

}

void VendingMachine::output()
{
	int select, money;

	this->showVending();
	cout << "구매할 제품과 돈을 입력하세요 : ";
	cin >> select >> money;
	products[select - 1]->buyThis(money);

}
