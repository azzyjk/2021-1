#include "VendingManager.h"

VendingManager::VendingManager(string filename)
{
	this->manageNum = 1;

	int vendingCount;

	ifstream fin(filename);
	if (!fin.is_open()) {
		exit(0);
	}

	fin >> this->buildingSize;

	buildingVending = new int[buildingSize];

	vendingArr = new int* [buildingSize];
	for (int i = 0; i < buildingSize; i++) {
		fin >> buildingVending[i];
		vendingArr[i] = new int [buildingVending[i]]();

	}

	vendingList = new VendingMachine ** [buildingSize];
	for (int i = 0; i < buildingSize; i++) {
		vendingList[i] = new VendingMachine * [buildingVending[i]];
	}

}

VendingManager::~VendingManager()
{
	for (int i = 0; i < buildingSize; i++) {
		for (int j = 0; j < buildingVending[i]; j++) {
			if (vendingArr[i][j] == 1) delete vendingList[i][j];
		}
		delete[] vendingList[i];
	}
	delete[] vendingList;

	for (int i = 0; i < buildingSize; i++) {
		delete[] vendingArr[i];
	}
	delete[] vendingArr;

	delete[] buildingVending;
}

void VendingManager::showVendingMachine()
{
	cout << "�ǹ��� ���Ǳ� ��Ȳ" << endl;
	cout << "====================" << endl;
	for (int i = 0; i < buildingSize; i++) {
		cout << i + 1 << "��\t";
		for (int j = 0; j < buildingVending[i]; j++) {
			cout << (vendingArr[i][j] == 1 ? "��" : "��") << '\t';
		}
		cout << endl;
		 
	}
	cout << "====================" << endl;
}

void VendingManager::install()
{
	int row, col;
	this->showVendingMachine();
	cout << "���Ǳ⸦ ��ġ�� ���� ��ġ�� �Է��Ͻÿ� (ex, 1 1) ==> ";
	cin >> row >> col;
	row -= 1;
	col -= 1;
	if (vendingArr[row][col] == 0) {
		vendingList[row][col] = new VendingMachine(3, this->manageNum);
		this->manageNum += 1;
		vendingArr[row][col] = 1;
	}
}

void VendingManager::input()
{
	int row, col;
	this->showVendingMachine();
	cout << "��ǰ�� �԰��� ���Ǳ⸦ �����ϼ��� (ex, 1 1) ==> ";
	cin >> row >> col;
	row -= 1;
	col -= 1;
	vendingList[row][col]->input();
}

void VendingManager::output()
{
	int row, col;
	this->showVendingMachine();
	cout << "��ǰ�� ����� ���Ǳ⸦ �����ϼ��� (ex, 1 1) ==> ";
	cin >> row >> col;
	row -= 1;
	col -= 1;
	vendingList[row][col]->output();
}
