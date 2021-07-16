#pragma once
#include<iostream>

using namespace std;
template<typename T>
class ArrayData3
{
protected:
	T* data;
	int capacity;
	int used;
public:
	ArrayData3();
	ArrayData3(int capacity);
	ArrayData3(int capacity, int used);
	ArrayData3(ArrayData3<T>& arr);
	ArrayData3(ArrayData3<T>&& arr) noexcept;
	~ArrayData3();
	void addElement(T num);
	bool full() const;
	int getCapacity() const {
		return this->capacity;
	};
	int getUsed() const {
		return this->used;
	};
	void emptyArray();
	void showData() const;
	ArrayData3& operator=(const ArrayData3& rhs);
	ArrayData3& operator=(ArrayData3&& rhs) noexcept;

	template<typename T1>
	friend ostream& operator<<(ostream& out, const ArrayData3<T1>& ad);
};


#include "ArrayData3Definition.h"