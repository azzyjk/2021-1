#pragma once
#include<iostream>

using namespace std;

class Node
{
private:
	bool visit, mine;
	int mineCount;
public:
	Node();
	bool _isVisited();
	bool _isMine();
	bool _visit();
	void _setMine() {
		this->mine = true;
	}
	int _getMineCount() {
		return this->mineCount;
	}
	void _addMineCount() {
		mineCount += 1;
	}
};

