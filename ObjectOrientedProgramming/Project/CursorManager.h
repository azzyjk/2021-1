#pragma once
#include <windows.h>

class CursorManager
{
private:
	int row, col;
	int maxRow, maxCol;

public:
	CursorManager();
	void _up();
	void _down();
	void _left();
	void _right();
	void _setMax(int _maxRow, int _maxCol);
	int _getRow() {
		return this->row;
	}
	int _getCol() {
		return this->col/2;
	}
	void _gotoxy(int _row, int _col);
	void _gotoHave();

};

