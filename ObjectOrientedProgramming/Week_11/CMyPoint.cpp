#include "CMyPoint.h"

CMyPoint::CMyPoint(const int& _x, const int& _y)
	:x(_x), y(_y)
{
}

bool CMyPoint::operator<(const CMyPoint& pt)
{
	return this->x < pt.x;
}

bool CMyPoint::operator>(const CMyPoint& pt)
{
	return this->x > pt.x;
}

ostream& operator<<(ostream& out, const CMyPoint& pt)
{
	out << pt.x << ", " << pt.y << endl;
	return out;
}
