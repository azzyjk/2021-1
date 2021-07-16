#include "Student.h"

template<typename T, size_t N, typename T1>
int search(T(&arr)[N], int size, T1 find) {
	for (int i = 0; i < size; i++) {
		if (arr[i] == find) return i;
	}
	return -1;
}

int main() {
	int arr[5] = { 5, 4, 3, 2, 1 };
	int idx1 = search(arr, 5, 3);
	if (idx1 != -1) cout << arr[idx1] << endl;

	string id[3] = { "greenjoa1", "greenjoa2", "greenjoa3" };
	int idx2 = search(id, 3, "greenjoa2");
	if (idx2 != -1) cout << id[idx2] << endl;

	Student std[3] = { Student("greenjoa1", 70), Student("greenjoa2", 50), Student("greenjoa3", 80) };

	int idx3 = search(std, 3, "greenjoa2");
	if (idx3 != -1) cout << std[idx3] << endl;

	int idx4 = search(std, 3, Student("greenjoa3", 80));
	if (idx4 != -1) cout << std[idx4] << endl;
	return 0;
}