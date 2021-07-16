#pragma once
class ArrayData
{
private:
	double* data;
	int capacity;
	int used;
public:
	ArrayData();
	ArrayData(int capacity);
	ArrayData(int capacity, int used);
	ArrayData(ArrayData& arr);
	ArrayData(ArrayData&& arr) noexcept;
	~ArrayData();
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
};

