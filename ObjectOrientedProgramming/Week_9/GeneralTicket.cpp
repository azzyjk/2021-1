#include "GeneralTicket.h"

GeneralTicket::GeneralTicket(double _price, bool _payByCredit):Ticket(_price), payByCredit(_payByCredit)
{
}

GeneralTicket::~GeneralTicket()
{
}

void GeneralTicket::show() const
{
	Ticket::show();
	cout << "카드결재여부 : " << (this->payByCredit? "true" : "false") << endl;
	cout << "지불금액 : " << (this->payByCredit? this->price * 1.1 : this->price) << endl;
	
}
