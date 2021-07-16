#include "ArrayData.h"
#include<iostream>

using namespace std;

ArrayData::ArrayData():ArrayData(3, 0) {
	
}
ArrayData::ArrayData(int capacity):ArrayData(capacity, 0) {

}
ArrayData::ArrayData(int capacity, int used):capacity(capacity), used(used){
	cout << "Constructor" << endl;
	data = new double[this->capacity];
}
ArrayData::ArrayData(ArrayData& arr):ArrayData(arr.capacity, arr.used){
	cout << "Copy Constructor" << endl;
	for (int i = 0; i < used; i++) {
		this->data[i] = arr.data[i];
	}
}
ArrayData::ArrayData(ArrayData&& arr) noexcept:capacity(arr.capacity), used(arr.used), data(arr.data){
	cout << "Move Constructor" << endl;
	arr.data = nullptr;
}
ArrayData::~ArrayData() {
	if(data != nullptr)	delete[] data;
	data = nullptr;
}
void ArrayData::addElement(double num) {
	if (!full()) {
		data[this->used] = num;
		this->used += 1;
	}
	else cout << "FULL!!" << endl;
	
}
bool ArrayData::full() const{
	return this->used == this->capacity;
}

void ArrayData::emptyArray() {
	this->used = 0;
}
void ArrayData::showData() const{
	for (int i = 0; i < this->used; i++) {
		cout << data[i] << " ";
	}
	cout << endl;
}