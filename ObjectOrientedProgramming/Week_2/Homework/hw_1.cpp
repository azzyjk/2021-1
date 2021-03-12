#include<iostream>
#include<string>
#include<fstream>

using namespace std;

int main() {
	cout << "201711425 정준원" << endl;
	
	ifstream file("./Week_2/Homework/hw_1.txt");

	// file open error
	if (!file.is_open()) {
		cerr << "failed to read file\n";
		return 1;
	}
	
	string fileString;
	while (!file.eof()) {
		string str;
		getline(file, str);
		fileString += str + '\n';
	}

	cout << "--------변경전 문자열--------\n" << fileString << endl;
	
	string findStr;
	cout << "찾을 문자열 : ";
	getline(cin, findStr);

	string changeStr;
	cout << "변경할 문자열 : ";
	getline(cin, changeStr);

	
	size_t found = 0;
	if (string::npos == fileString.find(findStr, found)) {
		cout << "찾는 문자열이 존재하지 않습니다." << endl;
		return 0;
	}
	else {
		// std::string::npos 는 위치하고 있지 않을때이다.
		while(string::npos != (found = fileString.find(findStr, found))) {
			// replace(시작위치, 찾은 문자열 길이, 변경할 문자열)
			fileString.replace(found, findStr.length(), changeStr); 
		}
		cout << "--------변경후 문자열--------\n" << fileString << endl;
	}

	return 0;
}