#include<iostream>
#include "ArrayData.h"

#ifdef _DEBUG
#ifndef DBG_NEW
#define DBG_NEW new (_NORMAL_BLOCK, __FILE__, __LINE__)
#define new DBG_NEW
#endif
#endif

using namespace std;

ArrayData getArr(int num) {
	ArrayData A(num);
	return A;
		
}

int main() {
	cout << "201711425 정준원" << endl;
	_CrtSetDbgFlag(_CRTDBG_ALLOC_MEM_DF | _CRTDBG_LEAK_CHECK_DF);
	ArrayData arr1 = getArr(10);
	
	arr1.addElement(5.5);
	arr1.addElement(2.5);
	
	arr1.showData();
	return 0;
}