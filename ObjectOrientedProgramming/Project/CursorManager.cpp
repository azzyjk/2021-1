#include "CursorManager.h"

CursorManager::CursorManager()
	:row(1), col(0), maxRow(0), maxCol(0)
{
}

void CursorManager::_up()
{
	if (this->row - 1 >= 1) this->row -= 1;
	_gotoxy(this->row, this->col);
}

void CursorManager::_down()
{
	if (this->row + 1 <= this->maxRow) this->row += 1;
	_gotoxy(this->row, this->col);
}

void CursorManager::_left()
{
	if (this->col - 1 >= 0) this->col -= 2;
	_gotoxy(this->row, this->col);
}

void CursorManager::_right()
{
	if (this->col + 1 <= this->maxCol) this->col += 2;
	_gotoxy(this->row, this->col);
}

void CursorManager::_setMax(int _maxRow, int _maxCol)
{
	this->maxRow = _maxRow;
	this->maxCol = _maxCol;
}

void CursorManager::_gotoxy(int _row, int _col)
{
	COORD Pos = { _col, _row };
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), Pos);
}

void CursorManager::_gotoHave()
{
	COORD Pos = { this->col, this->row };
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), Pos);
}

