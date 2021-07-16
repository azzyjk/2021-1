#include "JJWMarket.h"

JJWMarket::JJWMarket(string _name)
	:marketName(_name)
{

}

JJWMarket::~JJWMarket()
{
	for (auto i : items) { // 메모리 누수 아쉽습니다.
		free(i);
	}
}

void JJWMarket::addProduct(JJWItems* product)
{
	items.push_back(product);
}

vector<JJWItems*> JJWMarket::getProduct()
{
	return this->items;
	//for (auto i : this->items) {
		//i->show();
	//}
}
