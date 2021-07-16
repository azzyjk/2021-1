#include "GeneralTicket.h"

GeneralTicket::GeneralTicket(double _price, bool _payByCredit):Ticket(_price), payByCredit(_payByCredit)
{
	if (this->payByCredit == true) this->cost = this->price * 1.1;
	else this->cost = this->price;
}

GeneralTicket::~GeneralTicket()
{
	cout << "GeneralTicket 소멸자" << endl;
}

void GeneralTicket::setPrice(const double& _price)
{
	this->price = _price;
	if (this->payByCredit == true) this->cost = this->price * 1.1;
	else this->cost = this->price;
}

void GeneralTicket::show() const
{
	Ticket::show();
	cout << "카드결재여부 : " << (this->payByCredit? "true" : "false") << endl;
	cout << "지불금액 : " << this->cost << endl;
	
}
