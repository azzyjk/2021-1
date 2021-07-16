#include<iostream>
#include"GeneralTicket.h"
#include"AdvancedTicket.h"
#include "TicketManager.h"

#ifdef _DEBUG
#ifndef DBG_NEW
#define DBG_NEW new (_NORMAL_BLOCK, __FILE__, __LINE__)
#define new DBG_NEW
#endif
#endif

using namespace std;

int main() {
	cout << "201711425 정준원" << endl;
	_CrtSetDbgFlag(_CRTDBG_ALLOC_MEM_DF | _CRTDBG_LEAK_CHECK_DF);

	TicketManager manager("아이유콘서트", 10);

	manager.buy(new AdvancedTicket(1000, 40));
	manager.buy(new AdvancedTicket(1500, 30));
	manager.buy(new AdvancedTicket(2000, 25));
	manager.buy(new AdvancedTicket(1000, 5));
	manager.buy(new GeneralTicket(2000, true));
	manager.buy(new GeneralTicket(1500, false));

	cout << manager << endl;

	manager.showGeneralTicket(true);
	manager.showGeneralTicket(false);
	manager.showAdvancedTicket();

	return 0;
}