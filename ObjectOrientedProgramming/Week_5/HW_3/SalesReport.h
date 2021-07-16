#pragma once
#include"Salesman.h"

class SalesReport
{
private:
	Salesman** team;
	double highestSales;
	double averageSales;
	int number;
	int cnt = 0;
public:
	SalesReport();
	SalesReport(const int& num);
	~SalesReport();
	void computeStats();
	void addMember();
	int getMember();
	Salesman* getBestClerk();
	string getTeamInfo();
};

