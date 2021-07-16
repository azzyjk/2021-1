#include "SalesReport.h"

using namespace std;

SalesReport::SalesReport() : SalesReport(0) {

}
SalesReport::SalesReport(const int& num) 
	: number(num), highestSales(0.0), averageSales(0.0){
	if (number < 1) team = nullptr;
	else {
		team = new Salesman * [num];
		cout << "Make team Complete." << endl;
	}
}
SalesReport::~SalesReport(){
	if (team != nullptr) {
		for (int i = 0; i < cnt; i++) {
			delete team[i];
		}
		delete[] team;
	}
}

void SalesReport::computeStats() {
	highestSales = 0.0;
	averageSales = 0.0;
	for (int i = 0; i < cnt; i++) {
		highestSales = max(highestSales, team[i]->getSales());
		averageSales += team[i]->getSales();
	}
	averageSales /= number;
}
void SalesReport::addMember()
{
	string name;
	double sales;

	if (cnt < number) {
		cout << "Name : ";
		cin >> name;
		cout << "Sales : ";
		cin >> sales;
		team[cnt] = new Salesman(name, sales);
		cnt += 1;
	}
	else {
		cout << "Member is full!" << endl;
	}
	
	
}
int SalesReport::getMember()
{
	return number;
}
Salesman* SalesReport::getBestClerk() {
	size_t bestIndex = 0;
	for (int i = 1; i < cnt; i++) {
		if (team[bestIndex]->getSales() < team[i]->getSales()) bestIndex = i;
	}
	return team[bestIndex];
}
string SalesReport::getTeamInfo() {
	string info = "Member number : " + to_string(number) +
		"\nAverage Sales : " + to_string(averageSales) +
		"\nHighest Sales : " + to_string(highestSales);
	return info;
}