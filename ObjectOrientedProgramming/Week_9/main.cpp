#include<iostream>
#include"GeneralTicket.h"
#include"AdvancedTicket.h"

using namespace std;

int main() {
	cout << "201711425 정준원" << endl;
	Ticket ticket(1000.0);
	ticket.show();
	cout << "------------------------------" << endl;

	AdvancedTicket aticket(1000.0, 20);
	aticket.show();
	cout << "------------------------------" << endl;

	GeneralTicket gticket(2000.0, true);
	gticket.show();
	cout << gticket.getPrice() << endl;
	cout << "------------------------------" << endl;
	return 0;
}