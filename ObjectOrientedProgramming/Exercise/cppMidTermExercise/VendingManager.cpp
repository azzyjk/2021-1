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
	cout << "건물의 자판기 현황" << endl;
	cout << "====================" << endl;
	for (int i = 0; i < buildingSize; i++) {
		cout << i + 1 << "층\t";
		for (int j = 0; j < buildingVending[i]; j++) {
			cout << (vendingArr[i][j] == 1 ? "●" : "○") << '\t';
		}
		cout << endl;
		 
	}
	cout << "====================" << endl;
}

void VendingManager::install()
{
	int row, col;
	this->showVendingMachine();
	cout << "자판기를 설치할 층과 위치를 입력하시오 (ex, 1 1) ==> ";
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
	cout << "제품을 입고할 자판기를 선택하세요 (ex, 1 1) ==> ";
	cin >> row >> col;
	row -= 1;
	col -= 1;
	vendingList[row][col]->input();
}

void VendingManager::output()
{
	int row, col;
	this->showVendingMachine();
	cout << "제품을 출고할 자판기를 선택하세요 (ex, 1 1) ==> ";
	cin >> row >> col;
	row -= 1;
	col -= 1;
	vendingList[row][col]->output();
}
