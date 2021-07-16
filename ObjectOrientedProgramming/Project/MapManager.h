#pragma once

#include <string>
#include <queue>
#include <vector>
#include <utility>
#include <windows.h>
#include <fstream>
#include "Node.h"
#include<ctime>


class MapManager
{
private:
	int maxRow, maxCol, difficult, total, totalMines;
	int openCount;
	Node** map;
	Node** backup;
	queue< pair<int, int> > record;
	queue< pair<int, int> > minePos;
public:
	MapManager();
	~MapManager();
	void createMap(int _maxRow, int _maxCol);
	void setMine();
	void printMap();
	void printBackup();
	void printResult();
	void showReplay();
	int getMaxRow() {
		return this->maxRow;
	}
	int getMaxCol() {
		return this->maxCol;
	}
	int getOpenCount() {
		return this->openCount;
	}
	bool visitNode(int _row, int _col);
	void backupMap();
	void saveMap();
	bool loadMap();
};

