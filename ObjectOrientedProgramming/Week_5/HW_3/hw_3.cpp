#include<iostream>
#include"SalesReport.h"

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

	int num;

	cout << "Salesman Num : ";
	cin >> num;

	SalesReport team(num);

	for (int i = 0; i < num; i++) team.addMember();
	team.addMember();

	team.computeStats();
	if (team.getMember() > 0) {
		cout << team.getBestClerk()->getSalesmanInfo() << endl;
		cout << team.getTeamInfo() << endl;
	}
	else {
		cout << "There is not salesman.." << endl;
	}
	return 0;
}