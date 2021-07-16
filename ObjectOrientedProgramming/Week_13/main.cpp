#include<iostream>
#include<stack>
#include<cstring>
#include<cstdlib>

using namespace std;

stack<int> s;

bool isOperator(char* word) {
	if (!strcmp(word, "+") || !strcmp(word, "*") || !strcmp(word, "-") || !strcmp(word, "/")) {
		return true;
	}
	return false;
}

void calculate(char* str) {
	int operand1, operand2;
	char* context;
	char* p = strtok_s(str, " ", &context);
	while (p != NULL) {
		if (isOperator(p)) {
			operand1 = s.top();
			s.pop();
			operand2 = s.top();
			s.pop();

			if (!strcmp(p, "+")) s.push(operand1 + operand2);
			else if (!strcmp(p, "-")) s.push(operand1 - operand2);
			else if (!strcmp(p, "*")) s.push(operand1 * operand2);
			else if (!strcmp(p, "/")) s.push(operand1 / operand2);
		}
		else s.push(atoi(p));
		
		p = strtok_s(NULL, " ", &context);
	}
	cout << "res : " << s.top() << endl;
	s.pop();
}

int main() {
	cout << "201711425 정준원" << endl;
	char str[] = "3 20 6 * 2 * + 20 +";
	char str2[] = "3 20 6 * +";
	calculate(str2);
	calculate(str);
	return 0;
}