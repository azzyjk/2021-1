#pragma once
#include "ArrayData2.h"
class ArrayDataSmart :
    public ArrayData2
{
private:
    double* backdata = nullptr;
    int usedB;
public:
    ArrayDataSmart();
    ArrayDataSmart(ArrayDataSmart& arr);
    ArrayDataSmart(ArrayDataSmart&& arr) noexcept;
    ArrayDataSmart(const int& capacity, const int& used = 0);
    ~ArrayDataSmart();
    void backup();
    void restore();
    ArrayDataSmart& operator=(const ArrayDataSmart& rhs);
    ArrayDataSmart& operator=(ArrayDataSmart&& rhs) noexcept;
};

