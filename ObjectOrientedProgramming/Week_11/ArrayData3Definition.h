#pragma once
#include "ArrayData3.h"

template<typename T>
ArrayData3<T>::ArrayData3():ArrayData3(3, 0)
{

}

template<typename T>
ArrayData3<T>::ArrayData3(int capacity) :ArrayData3(capacity, 0) {

}

template<typename T>
ArrayData3<T>::ArrayData3(int capacity, int used) : capacity(capacity), used(used) {
	cout << "ArrayData Constructor" << endl;
	data = new T[this->capacity];
}

template<typename T>
ArrayData3<T>::ArrayData3(ArrayData3<T>& arr) : ArrayData3(arr.capacity, arr.used) {
	cout << "ArrayData Copy Constructor" << endl;
	for (int i = 0; i < used; i++) {
		this->data[i] = arr.data[i];
	}
}

template<typename T>
ArrayData3<T>::ArrayData3(ArrayData3<T>&& arr) noexcept :capacity(arr.capacity), used(arr.used), data(arr.data) {
	cout << "ArrayData Move Constructor" << endl;
	arr.data = nullptr;
}

template<typename T>
ArrayData3<T>::~ArrayData3() {
	cout << "ArrayData delete" << endl;
	if (data != nullptr)	delete[] data;
	data = nullptr;
}

template<typename T>
void ArrayData3<T>::addElement(T num) {
	if (!full()) {
		data[this->used] = num;
		this->used += 1;
	}
	else cout << "FULL!!" << endl;

}

template<typename T>
bool ArrayData3<T>::full() const {
	return this->used == this->capacity;
}

template<typename T>
void ArrayData3<T>::emptyArray() {
	this->used = 0;
}

template<typename T>
void ArrayData3<T>::showData() const {
	for (int i = 0; i < this->used; i++) {
		cout << data[i];
	}
	cout << endl;
}

template<typename T>
ArrayData3<T>& ArrayData3<T>::operator=(const ArrayData3<T>& rhs)
{
	printf("ArrayData 대입연산자\n");
	if (this == &rhs) return *this;

	delete[] this->data;
	this->capacity = rhs.capacity;
	this->used = rhs.used;
	this->data = new T[this->capacity];
	for (int i = 0; i < this->used; i++) {
		this->data[i] = rhs.data[i];
	}
	return *this;
}

template<typename T>
ArrayData3<T>& ArrayData3<T>::operator=(ArrayData3<T>&& rhs) noexcept
{
	printf("ArrayData 이동대입연산자\n");
	if (this == &rhs) return *this;
	delete[] this->data;
	this->used = rhs.used;
	this->capacity = rhs.capacity;
	this->data = rhs.data;
	rhs.data = nullptr;

	return *this;

}

template<typename T1>
ostream& operator<<(ostream& out, const ArrayData3<T1>& ad)
{
	ad.showData();
	return out;
}
