#include<iostream>
#include<fstream>

#ifdef _DEBUG
#ifndef DBG_NEW
#define DBG_NEW new (_NORMAL_BLOCK, __FILE__, __LINE__)
#define new DBG_NEW
#endif
#endif

using namespace std;

int** loadMap(int& raw, int& col) {
	ifstream fin("./Week_3/Homework/map.txt");
	if (!fin.is_open()) {
		cerr << "File is not open!!" << endl;
		exit(0);
	}

	fin >> raw >> col;

	int* (*arr) = new int* [raw];

	for (int i = 0; i < raw; i++) {
		arr[i] = new int[col];
	}

	for (int i = 0; i < raw; i++) {
		for (int j = 0; j < col; j++) {
			fin >> arr[i][j];
		}
	}
	fin.close();
	return arr;
}

void printMap(int** arr, int raw, int col) {
	for (int i = 0; i < raw; i++) {
		for (int j = 0; j < col; j++) {
			cout << (arr[i][j] ? "●" : "○") << " ";
		}
		cout << endl;
	}
}

void removeMap(int** (&arr), int raw, int col) {
	for (int i = 0; i < raw; i++) {
		delete[] arr[i];
	}
	delete[] arr;
	arr = nullptr;
}

int main() {
	_CrtSetDbgFlag(_CRTDBG_ALLOC_MEM_DF | _CRTDBG_LEAK_CHECK_DF);
	cout << "201711425 정준원" << endl;
	
	int raw, col;
	int** arr = nullptr;

	arr = loadMap(raw, col);

	if (arr != nullptr) {
		printMap(arr, raw, col);
		removeMap(arr, raw, col);
	}

	return 0;
}