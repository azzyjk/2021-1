#include "ArrayData3.h"
#include "CMyPoint.h"

int main() {
	cout << "201711425 정준원" << endl;
	ArrayData3<CMyPoint> arr;
	
	arr.addElement(CMyPoint(10, 50));
	arr.addElement(CMyPoint(20, 60));

	cout << arr << endl;
	return 0;
}