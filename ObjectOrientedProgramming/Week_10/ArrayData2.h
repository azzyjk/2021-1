#pragma once
#include<iostream>

using namespace std;

class ArrayData2
{
protected:
	double* data;
	int capacity;
	int used;
public:
	ArrayData2() = delete;
	ArrayData2(int capacity);
	ArrayData2(int capacity, int used);
	ArrayData2(ArrayData2& arr);
	ArrayData2(ArrayData2&& arr) noexcept;
	~ArrayData2();
	void addElement(double num);
	bool full() const;
	int getCapacity() const {
		return this->capacity;
	};
	int getUsed() const {
		return this->used;
	};
	void emptyArray();
	void showData() const;
	ArrayData2& operator=(const ArrayData2& rhs);
	ArrayData2& operator=(ArrayData2&& rhs) noexcept;
};

ostream& operator<<(ostream& out, const ArrayData2& ad);