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
	cout << "������ȣ ";
	cout << this->manageNumber;
	cout << " ���Ǳ� �Ǹ� ��ǰ" << endl;
	cout << "======================" << endl;
	for (int i = 0; i < this->count; i++) {
		cout << i + 1 << "��) " << products[i]->getProductInfo() << endl;;
	}
	cout << "======================" << endl;
}

void VendingMachine::input() {
	string productName;
	int productPrice;
	int productCount;
	bool isExist = false;
	int index = 0;

	cout << "�԰��� ��ǰ�� : ";
	cin >> productName;

	for (int i = 0; i < count; i++) {
		if (products[i]->getName() == productName) {
			isExist = true;
			index = i;
		}
	}

	if (isExist) {
		cout << "��ǰ ���� : ";
		cin >> productCount;
		products[index]->addCount(productCount);
		cout << "�԰� �Ϸ� �Ǿ����ϴ�." << endl;
	}
	else {
		cout << "��ǰ ���ݰ� ���� : ";
		cin >> productPrice >> productCount;
		products[this->count] = new Product(productName, productPrice, productCount);
		this->count += 1;
		cout << "�԰� �Ϸ� �Ǿ����ϴ�." << endl;
	}

}

void VendingMachine::output()
{
	int select, money;

	this->showVending();
	cout << "������ ��ǰ�� ���� �Է��ϼ��� : ";
	cin >> select >> money;
	products[select - 1]->buyThis(money);

}
