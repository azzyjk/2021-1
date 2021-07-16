﻿#include "ArrayDataSmart.h"

#ifdef _DEBUG
#ifndef DBG_NEW
#define DBG_NEW new (_NORMAL_BLOCK, __FILE__, __LINE__)
#define new DBG_NEW
#endif
#endif

int main() {
	cout << "201711425 정준원" << endl;
	_CrtSetDbgFlag(_CRTDBG_ALLOC_MEM_DF | _CRTDBG_LEAK_CHECK_DF);

	ArrayDataSmart arr1(5);
	arr1.addElement(10.1);
	arr1.addElement(10.2);
	arr1.addElement(10.3);
	arr1.addElement(10.4);
	arr1.backup();
	arr1.addElement(10.5);
	arr1.addElement(10.6);

	cout << "arr1 : " << arr1 << endl;
	cout << "---------restore---------" << endl;
	arr1.restore();
	cout << "arr1 : " << arr1 << endl;

	cout << "---------대입---------" << endl;
	ArrayDataSmart arr2(20);
	arr2 = arr1;
	cout << "arr2 : " << arr2 << endl;

	cout << "---------이동 대입---------" << endl;
	ArrayDataSmart arr3(3);
	arr3 = move(arr1);
	cout << "arr3 : " << arr3 << endl;

	return 0;
}