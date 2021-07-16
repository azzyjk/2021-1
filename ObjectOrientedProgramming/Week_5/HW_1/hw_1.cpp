#include<iostream>
#include"Rectangle.h"

using namespace std;

int main() {
	cout << "201711425 정준원" << endl;
	Rectangle rect1;
	Rectangle rect2(3,5);
	Rectangle rect3(3);

	if (rect1.isSquare()) cout << "rect1 is square." << endl;
	if (rect2.isSquare()) cout << "rect2 is square." << endl;
	if (rect3.isSquare()) cout << "rect3 is square." << endl;
	return 0;
}