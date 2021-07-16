#include "ArrayDataSmart.h"

ArrayDataSmart::ArrayDataSmart()
	:ArrayDataSmart(3, 0)
{
}

ArrayDataSmart::ArrayDataSmart(ArrayDataSmart& arr)
	:ArrayData2(arr), usedB(arr.usedB)
{
	cout << "ArrayDataSmart copy constructor" << endl;
	backdata = new double[arr.capacity];
	for (int i = 0; i < this->usedB; i++) {
		backdata[i] = arr.backdata[i];
	}
}

ArrayDataSmart::ArrayDataSmart(ArrayDataSmart&& arr) noexcept
	:ArrayData2(move(arr)), usedB(arr.usedB), backdata(move(arr.backdata))
{
	cout << "ArrayDataSmart move constructor" << endl;
}

ArrayDataSmart::ArrayDataSmart(const int& capacity, const int& used)
	:ArrayData2(capacity, used), usedB(0)
{
	cout << "ArrayDataSmart Constructor" << endl;
	backdata = new double[capacity];
}

ArrayDataSmart::~ArrayDataSmart()
{
	cout << "ArrayDataSmart delete" << endl;
	if (this->backdata != nullptr) {
		delete[] this->backdata;
	}
	this->backdata = nullptr;
}

void ArrayDataSmart::backup()
{
	this->usedB = this->used;

	if (this->backdata != nullptr) {
		for (int i = 0; i < this->usedB; i++) {
			this->backdata[i] = this->data[i];
		}
	}
}

void ArrayDataSmart::restore()
{
	this->used = this->usedB;

	if (this->backdata != nullptr) {
		for (int i = 0; i < this->used; i++) {
			this->data[i] = this->backdata[i];
		}
	}
}

ArrayDataSmart& ArrayDataSmart::operator=(const ArrayDataSmart& rhs)
{
	cout << "ArrayDataSmart 대입 연산자" << endl;
	if (&rhs == this) return *this;
	ArrayData2::operator=(rhs);
	this->usedB = rhs.usedB;
	
	delete[] this->backdata;
	this->backdata = new double[rhs.capacity];
	for (int i = 0; i < this->usedB; i++) {
		this->backdata[i] = rhs.backdata[i];
	}
}

ArrayDataSmart& ArrayDataSmart::operator=(ArrayDataSmart&& rhs) noexcept
{
	cout << "ArrayDataSmart 이동 대입 연산자" << endl;
	if (this == &rhs) return *this;
	ArrayData2::operator=(move(rhs));

	this->usedB = rhs.usedB;
	delete[] this->backdata;
	this->backdata = rhs.backdata;
	rhs.backdata = nullptr;
}

