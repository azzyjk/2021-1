#include "AdvancedTicket.h"

AdvancedTicket::AdvancedTicket(double _price, int _advanceDays):Ticket(_price), advanceDays(_advanceDays)
{
}

AdvancedTicket::~AdvancedTicket()
{
}

void AdvancedTicket::show() const
{
	int cost;
	if (this->advanceDays <= 10 && this->advanceDays > 0) cost = this->price * 0.9;
	else if (this->advanceDays <= 20) cost = this->price * 0.7;
	else cost = this->price * 0.5;

	Ticket::show();
	cout << "사전예약일 : " << this->advanceDays << endl;
	cout << "지불금액 : " << cost << endl;
}
