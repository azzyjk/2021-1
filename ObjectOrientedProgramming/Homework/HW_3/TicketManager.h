#pragma once
#include <iostream>
#include "AdvancedTicket.h"
#include "GeneralTicket.h"

using namespace std;

class TicketManager
{
private:
	string m_name; // 공연명
	int totalNumber; // 전체 판매해야하는 티켓수
	Ticket** ticket = nullptr; // 티켓이 어떤 타입으로 판매되는지
	int count = 0; // 현재 판매된 갯수
public:
	TicketManager() = delete;
	~TicketManager();
	TicketManager(const string& name, const int number);
	void buy(Ticket* t);
	void show() const;
	void showGeneralTicket(const bool& card); // true면 true인 값을 가진 애들만 출력
	void showAdvancedTicket();
};
ostream& operator<<(ostream& out, const TicketManager& manager);

