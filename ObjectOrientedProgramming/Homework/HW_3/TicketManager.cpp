#include "TicketManager.h"

TicketManager::~TicketManager()
{
	if (this->ticket != nullptr) {
		for (int i = 0; i < count; i++) {
			delete this->ticket[i];
		}
		delete[] this->ticket;
	}
	this->ticket = nullptr;
}

TicketManager::TicketManager(const string& _name, const int _number)
	:m_name(_name), totalNumber(_number), count(0)
{
	this->ticket = new Ticket * [this->totalNumber];
}

void TicketManager::buy(Ticket* t)
{
	if (count < totalNumber) ticket[count++] = t;
	else {
		delete t;
		cout << "Tickets are sold out!!" << endl;
	}
}

void TicketManager::show() const
{
	double totalCost = 0.0;
	cout << "---------- 티켓 예약 현황 ----------\n" << endl;
	cout << "총 예약 매수 : " << this->count << endl;
	cout << "------------------------------------" << endl;
	for (int i = 0; i < count; i++) {
		ticket[i]->show();
		totalCost += ticket[i]->getCost();
		cout << endl;
	}
	cout << "------------------------------------" << endl;
	cout << "총 예약 금액 : " << totalCost << endl;
	cout << "------------------------------------" << endl;
}

void TicketManager::showGeneralTicket(const bool& _card)
{
	cout << "201711425 정준원" << endl;
	cout << "---------- 일반 예약 현황 ----------\n" << endl;
	cout << "카드결제 여부 : " << (_card ? "true" : "false") << endl;
	cout << "------------------------------------" << endl;
	GeneralTicket* derived;
	double totalGeneralCost = 0.0;
	for (int i = 0; i < count; i++) {
		derived = dynamic_cast<GeneralTicket*>(ticket[i]);
		if (derived != nullptr) {
			if (derived->getPayByCredit() == _card) {
				derived->show();
				totalGeneralCost += derived->getCost();
			}
		}
	}
	cout << "------------------------------------" << endl;
	cout << "총 예약 금액 : " << totalGeneralCost << endl;
	cout << "------------------------------------" << endl;
}

void TicketManager::showAdvancedTicket()
{
	cout << "201711425 정준원" << endl;
	cout << "---------- 사전 예약 현황 ----------\n" << endl;
	AdvancedTicket* derived;
	double totalAdvancedCost = 0.0;
	for (int i = 0; i < this->count; i++) {
		derived = dynamic_cast<AdvancedTicket*>(ticket[i]);
		if (derived != nullptr) {
			derived->show();
			totalAdvancedCost += derived->getCost();
			cout << endl;
		}
	}
	cout << "------------------------------------" << endl;
	cout << "총 예약 금액 : " << totalAdvancedCost << endl;
	cout << "------------------------------------" << endl;
}

ostream& operator<<(ostream& out, const TicketManager& manager)
{
	manager.show();
	return out;
}
