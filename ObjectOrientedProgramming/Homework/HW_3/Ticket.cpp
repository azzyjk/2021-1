#include "Ticket.h"

int now = 1;

Ticket::Ticket(double _price)
{
	this->price = _price;
	this->number = now++;
	this->cost = this->price;
}

Ticket::~Ticket()
{
	cout << "Ticket 소멸자" << endl;
	now -= 1; // 취소는 티켓번호에 영향을 안미친다고 가정

}

void Ticket::show() const
{
	cout << "티켓번호 : " << this->number << endl;
	cout << "가격정보 : " << this->price << endl;
}