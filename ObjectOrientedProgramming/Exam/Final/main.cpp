#include"JJWVehicle.h"
#include"JJWShoes.h"
#include"JJWMarket.h"
#include<algorithm>

#ifdef _DEBUG
#ifndef DBG_NEW
#define DBG_NEW new (_NORMAL_BLOCK, __FILE__, __LINE__)
#define new DBG_NEW
#endif
#endif

bool checkKonkukShoes(JJWItems* item) {
	if (item->getType() == "건국운동화") return true;
	else return false;
}

bool checkShoes(JJWItems* item) {
	if (item->getCategory() == "신발") return true;
	else return false;
}

bool compare(JJWItems* item1, JJWItems* item2) {
	if (*item1 < *item2 == true) return false;
	return true;
}

int main() {
	cout << "201711425 정준원" << endl;
	_CrtSetDbgFlag(_CRTDBG_ALLOC_MEM_DF | _CRTDBG_LEAK_CHECK_DF);
	
	cout << "2) JJWItems 추상 클래스 만들기(생성자는 존재하되, 명시적으로 객체 생성 불가)" << endl;
	////JJWItems p("A0001", "자동차", 2, 10000);

	cout << "3) JJWVehicle 객체 생성" << endl;
	JJWItems* car = new JJWVehicle("A0001", "자동차", 2, 10000, "건국차", "Konkuk9", 2500);

	cout << "4) JJWShoes 객체 생성" << endl;
	JJWItems* shoes = new JJWShoes("S0001", "신발류", 3, 100, "여성용", "건국샌들", 240);
	
	cout << "5) 구매 참여하기" << endl;
	car->buy("홍길동");
	shoes->buy("김길동");
	shoes->buy("이길동");

	cout << "6) 객체 정보 출력하기" << endl;
	cout << *car << endl;
	cout << *shoes << endl;

	cout << "7) JJWMarket 객체 생성하기" << endl;
	JJWMarket market("건국Market");

	cout << "8) JJWMarket에 판매 물품 추가하기" << endl;
	market.addProduct(new JJWVehicle("A0001", "자동차", 2, 10000, "건국차", "Konkuk9", 2500));
	market.addProduct(new JJWShoes("S0001", "신발", 3, 100, "여성용", "건국샌들", 240));
	market.addProduct(new JJWVehicle("A0002", "자동차", 1, 20000, "대학차", "Konkuk", 3000));
	market.addProduct(new JJWShoes("S0002", "신발", 2, 200, "남성용", "건국운동화", 270));
	market.addProduct(new JJWShoes("S0003", "신발", 4, 300, "여성용", "건국구두", 235));

	cout << "9) 판매되는 제품 전체 저장 정보 가져오기" << endl;
	auto items = market.getProduct();

	cout << "10) 건국운동화 제품 찾기" << endl;
	auto pos = find_if(items.begin(), items.end(), checkKonkukShoes);

	int idx = distance(items.begin(), pos);
	items[idx]->show();
	
	cout << "11) 판매제품 중 신발만 모두 출력하기" << endl;
	vector<JJWItems*>::iterator iter = items.begin();
	while (iter != items.end()) {
		auto findShoes = find_if(iter, items.end(), checkShoes);
		int findIdx = distance(items.begin(), findShoes);
		items[findIdx]->show();
		iter = findShoes;
		iter++;
	}

	cout << "12) 높은 가격순으로 정렬해서 출력하기" << endl;
	sort(items.begin(), items.end(), compare);
	for (auto i : items) {
		i->show();
	}
	return 0;
}