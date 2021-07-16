#include "AdvancedTicket.h"

AdvancedTicket::AdvancedTicket(double _price, int _advanceDays):Ticket(_price), advanceDays(_advanceDays)
{
	if (this->advanceDays < 20 && this->advanceDays >= 10) this->cost = this->price * 0.9;
	else if (this->advanceDays < 30 && this->advanceDays >= 20) this->cost = this->price * 0.7;
	else if (this->advanceDays >= 30) this->cost = this->price * 0.5;
	else this->cost = this->price;
}

AdvancedTicket::~AdvancedTicket()
{
	cout << "AdvancedTicket 소멸자" << endl;
}

void AdvancedTicket::setPrice(const double& _price)
{
	this->price = _price;
	if (this->advanceDays < 20 && this->advanceDays >= 10) this->cost = this->price * 0.9;
	else if (this->advanceDays < 30 && this->advanceDays >= 20) this->cost = this->price * 0.7;
	else if (this->advanceDays >= 30) this->cost = this->price * 0.5;
	else this->cost = this->price;
}

void AdvancedTicket::show() const
{
	

	Ticket::show();
	cout << "사전예약일 : " << this->advanceDays << endl;
	cout << "지불금액 : " << this->cost << endl;
}
