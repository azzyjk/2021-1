#include "Product.h"
#include "VendingMachine.h"
#include "VendingManager.h"
#ifdef _DEBUG
#ifndef DBG_NEW
#define DBG_NEW new (_NORMAL_BLOCK, __FILE__, __LINE__)
#define new DBG_NEW
#endif
#endif
int main() {
	_CrtSetDbgFlag(_CRTDBG_ALLOC_MEM_DF | _CRTDBG_LEAK_CHECK_DF);
	cout << "201711425 정준원" << endl;
	/*Product item1("생수", 100);
	Product item2("커피", 200, 10);
	cout << item1.getProductInfo() << endl;
	cout << item2.getProductInfo() << endl;

	VendingMachine machine(3);
	machine.showVending();
	machine.input();
	machine.input();
	machine.input();
	machine.output();*/

	VendingManager manager("building.txt");
	manager.showVendingMachine();
	manager.install();
	manager.input();
	manager.output();
	return 0;
}