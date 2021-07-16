#include "ArrayData2.h"

ArrayData2::ArrayData2(int capacity):ArrayData2(capacity, 0) {

}
ArrayData2::ArrayData2(int capacity, int used):capacity(capacity), used(used){
	cout << "ArrayData Constructor" << endl;
	data = new double[this->capacity];
}
ArrayData2::ArrayData2(ArrayData2& arr): ArrayData2(arr.capacity, arr.used){
	cout << "ArrayData Copy Constructor" << endl;
	for (int i = 0; i < used; i++) {
		this->data[i] = arr.data[i];
	}
}
ArrayData2::ArrayData2(ArrayData2&& arr) noexcept:capacity(arr.capacity), used(arr.used), data(arr.data){
	cout << "ArrayData Move Constructor" << endl;
	arr.data = nullptr;
}
ArrayData2::~ArrayData2() {
	cout << "ArrayData delete" << endl;
	if(data != nullptr)	delete[] data;
	data = nullptr;
}
void ArrayData2::addElement(double num) {
	if (!full()) {
		data[this->used] = num;
		this->used += 1;
	}
	else cout << "FULL!!" << endl;
	
}
bool ArrayData2::full() const{
	return this->used == this->capacity;
}

void ArrayData2::emptyArray() {
	this->used = 0;
}
void ArrayData2::showData() const{
	for (int i = 0; i < this->used; i++) {
		cout << data[i] << " ";
	}
	cout << endl;
}


ArrayData2& ArrayData2::operator=(const ArrayData2& rhs)
{
	printf("ArrayData 대입연산자\n");
	if (this == &rhs) return *this;

	delete[] this->data;
	this->capacity = rhs.capacity;
	this->used = rhs.used;
	this->data = new double[this->capacity];
	for (int i = 0; i < this->used; i++) {
		this->data[i] = rhs.data[i];
	}
	return *this;
}

ArrayData2& ArrayData2::operator=(ArrayData2&& rhs) noexcept
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

ostream& operator<<(ostream& out, const ArrayData2& ad)
{
	ad.showData();
	return out;
}
