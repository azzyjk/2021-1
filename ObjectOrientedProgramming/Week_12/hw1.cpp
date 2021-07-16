#include <iostream>
#include <list>
#include <algorithm>

using namespace std;

template<typename T>
list<T> getStudent(list<list<T>> std, list<T> dropList) {
	list<T> tmp;
	for (auto row : std) {
		for (auto item : row) {
			tmp.push_back(item);
		}
	}

	tmp.sort();
	tmp.unique();

	for (auto removeItem : dropList) tmp.remove(removeItem);
	return tmp;
}

template<typename T>
void printArr(list<T> std) {
	for (T item : std) cout << item << " ";
}

int main() {
	cout << "201711425 정준원" << endl;

	list<list<string>> std;

	std.push_back({ "greenjoa1", "bluejoa1", "greenjoa3" });
	std.push_back({ "greenjoa1", "greenjoa3" });
	std.push_back({ "redjoa1", "greenjoa2", "bluejoa2" });

	list<string> droplist({ "greenjoa2", "bluejoa1" });

	list<string> student = getStudent(std, droplist);

	printArr(student);
	cout << endl;
	return 0;
}