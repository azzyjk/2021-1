#include<iostream>
#include<sstream>

using std::cout;
using std::cin;
using std::endl;
using std::stringstream;
using std::string;

namespace jjw {
	void printName() {
		cout << "학번 : 201711425 이름: 정준원" << endl;
	}
}

int main() {
	jjw::printName();
	string str, name;
	stringstream ss;
	int sum = 0, now;

	cout << "Enter your data(name kor eng math) : ";
	getline(cin, str);

	ss.str(str);
	ss >> name;
	// stringstream(mystr) >> (int형 변수) 로도 숫자로 변환 가능
	while (ss >> now) sum += now; // 같은 자료형일때 까지 실행
	cout << name + " : " << sum << endl;
	return 0;
}