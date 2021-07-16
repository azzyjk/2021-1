#include "Salesman.h"
#include<string>

using namespace std;

Salesman::Salesman():Salesman("noData", 0.0) {

}
Salesman::Salesman(const string& _name) : Salesman(_name, 0.0)
{
}
Salesman::Salesman(const string& _name, double _sales)
{
	name = _name;
	sales = _sales;
}
void Salesman::setName(string _name) {
	name = _name;
}
void Salesman::readInput() {
	cout << "name : ";
	cin >> name;
	cout << "sales : ";
	cin >> sales;
}
string Salesman::getSalesmanInfo() {
	return name + "'s sales : " + to_string(sales);
}

double Salesman::getSales()
{
	return sales;
}
