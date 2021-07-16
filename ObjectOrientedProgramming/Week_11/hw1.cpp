#include "CMyPoint.h"

template<typename T, size_t N>
void showArr(T(&arr)[N]) {
	cout << "Template showArr 함수" << endl;
	for (auto i : arr) cout << i << endl;
	cout << endl;
}

template<typename T, size_t N>
void sortAsc(T(&arr)[N]) {
	for (size_t i = 0; i < N - 1; i++) {
		for (size_t j = i + 1; j < N; j++) {
			if (arr[i] > arr[j]) {
				T temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
	}
}

int main() {
	cout << "201711425 정준원" << endl;
	CMyPoint arr[]{ CMyPoint(20, 30), CMyPoint(10, 20), CMyPoint(40, 50)};
	sortAsc(arr);
	showArr(arr);
	return 0;
}