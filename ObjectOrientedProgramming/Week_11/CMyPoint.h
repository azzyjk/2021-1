#pragma once
#include<iostream>

using namespace std;

class CMyPoint
{
private:
	int x;
	int y;

public:
	CMyPoint() = default;
	~CMyPoint() = default;
	CMyPoint(const int& _x, const int& _y);
	bool operator<(const CMyPoint& pt);
	bool operator>(const CMyPoint& pt);
	friend ostream& operator<<(ostream& out, const CMyPoint& pt);
};


