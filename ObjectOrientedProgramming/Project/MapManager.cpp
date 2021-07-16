#include "MapManager.h"

MapManager::MapManager()
	:maxRow(0), maxCol(0), difficult(0), total(0), totalMines(0)
{
}

MapManager::~MapManager()
{
	if (map != nullptr) {
		for (int i = 0; i < this->maxRow; i++) delete[] map[i];
		delete map;
		map = nullptr;
	}
	if (backup != nullptr) {
		for (int i = 0; i < this->maxRow; i++) delete[] backup[i];
		delete backup;
		backup = nullptr;
	}
}

void MapManager::createMap(int _maxRow, int _maxCol)
{
	this->maxRow = _maxRow;
	this->maxCol = _maxCol;

	while (this->difficult > 3 || this->difficult < 1) {
		cout << "원하는 난이도를 선택해주세요(1: 하, 2: 중, 3: 상)" << endl;
		cin >> this->difficult;
	}

	map = new Node * [this->maxRow];
	for (int i = 0; i < this->maxRow; i++) map[i] = new Node[this->maxCol];

	this->total = this->maxRow * this->maxCol;
	this->totalMines = (this->total / 10) * difficult;
	this->openCount = this->total - this->totalMines;
	this->setMine();
	this->backupMap();
	system("cls");
}

void MapManager::setMine()
{
	srand(time(NULL));
	int setMineCount = this->totalMines;
	int ranRow, ranCol;

	while (setMineCount) {
		ranRow = rand() % this->maxRow;
		ranCol = rand() % this->maxCol;

		if (!map[ranRow][ranCol]._isMine()) {
			vector<int> rowChange;
			vector<int> colChange;
			
			rowChange.push_back(0);
			colChange.push_back(0);
			if (ranRow - 1 >= 0) rowChange.push_back(-1);
			if (ranRow + 1 < this->maxRow) rowChange.push_back(+1);
			if (ranCol - 1 >= 0) colChange.push_back(-1);
			if (ranCol + 1 < this->maxCol) colChange.push_back(+1);

			map[ranRow][ranCol]._setMine();
			minePos.push(make_pair(ranRow, ranCol));
			
			for (int i = 0; i < rowChange.size(); i++) {
				for (int j = 0; j < colChange.size(); j++) {
					map[ranRow + rowChange[i]][ranCol + colChange[j]]._addMineCount();
				}
			}
			
			setMineCount -= 1;
		}
	}
	
}

void MapManager::printMap()
{
	for (int i = 0; i < this->maxRow; i++) {
		for (int j = 0; j < this->maxCol; j++) {
			if (map[i][j]._isVisited()) { // 이미 방문한 곳일 시
				cout << (map[i][j]._isMine() ? "*" : to_string(map[i][j]._getMineCount()));
			}
			else cout << "?";
			cout << " ";
		}
		cout << endl;
	}
}

void MapManager::printBackup()
{
	system("cls");
	cout << "201711425 정준원" << endl;
	for (int i = 0; i < this->maxRow; i++) {
		for (int j = 0; j < this->maxCol; j++) {
			if (backup[i][j]._isVisited()) { // 이미 방문한 곳일 시
				cout << (backup[i][j]._isMine() ? "*" : to_string(backup[i][j]._getMineCount()));
			}
			else cout << "?";
			cout << " ";
		}
		cout << endl;
	}
}

void MapManager::printResult()
{
	for (int i = 0; i < this->maxRow; i++) {
		for (int j = 0; j < this->maxCol; j++) {
			cout << (map[i][j]._isMine() ? "*" : to_string(map[i][j]._getMineCount())) << " ";
		}
		cout << endl;
	}
}

void MapManager::showReplay()
{
	this->printBackup();
	Sleep(1000);
	int saveRow, saveCol;
	while (!this->record.empty()) {
		saveRow = record.front().first;
		saveCol = record.front().second;
		record.pop();

		bool flag = false;
		queue< pair<int, int> > q;
		q.push(make_pair(saveRow, saveCol));

		while (!q.empty()) {
			int nowRow = q.front().first;
			int nowCol = q.front().second;
			q.pop();

			// 방문 안한 지역이면 방문 후 지뢰면 false 지뢰가 아니면 true
			if (!backup[nowRow][nowCol]._isVisited()) {
				flag = backup[nowRow][nowCol]._visit();
			}
			 else if (backup[nowRow][nowCol]._isVisited() && q.empty()) break;

			if (flag && backup[nowRow][nowCol]._getMineCount() == 0) {
				vector<int> rowChange;
				vector<int> colChange;
				rowChange.push_back(0);
				colChange.push_back(0);
				if (nowRow - 1 >= 0) rowChange.push_back(-1);
				if (nowRow + 1 < this->maxRow) rowChange.push_back(+1);
				if (nowCol - 1 >= 0) colChange.push_back(-1);
				if (nowCol + 1 < this->maxCol) colChange.push_back(+1);

				for (int i = 0; i < rowChange.size(); i++) {
					for (int j = 0; j < colChange.size(); j++) {
						if (backup[nowRow + rowChange[i]][nowCol + colChange[j]]._isVisited() == false) {
							q.push(make_pair(nowRow + rowChange[i], nowCol + colChange[j]));
						}
					}
				}
			}
		}
		this->printBackup();
		Sleep(1000);
	}
}

bool MapManager::visitNode(int _row, int _col)
{
	int** visitArr = new int* [this->maxRow];
	for (int i = 0; i < this->maxRow; i++) {
		visitArr[i] = new int[this->maxCol]{ 0 };
	}

	this->record.push(make_pair(_row, _col));

	bool flag=false;
	queue< pair<int, int> > q;
	q.push(make_pair(_row, _col));
	
	while (!q.empty()) {
		int nowRow = q.front().first;
		int nowCol = q.front().second;
		q.pop();

		// 방문 안한 지역이면 방문 후 지뢰면 false 지뢰가 아니면 true
		if (!map[nowRow][nowCol]._isVisited()) {
			this->openCount -= 1;
			flag = map[nowRow][nowCol]._visit();
		}
		else if(map[nowRow][nowCol]._isVisited() && q.empty()) return true;

		if (flag && map[nowRow][nowCol]._getMineCount() == 0) {
			vector<int> rowChange;
			vector<int> colChange;
			rowChange.push_back(0);
			colChange.push_back(0);
			if (nowRow - 1 >= 0) rowChange.push_back(-1);
			if (nowRow + 1 < this->maxRow) rowChange.push_back(+1);
			if (nowCol - 1 >= 0) colChange.push_back(-1);
			if (nowCol + 1 < this->maxCol) colChange.push_back(+1);
			
			for (int i = 0; i < rowChange.size(); i++) {
				for (int j = 0; j < colChange.size(); j++) {
					int changeRow = nowRow + rowChange[i];
					int changeCol = nowCol + colChange[j];
					if (map[changeRow][changeCol]._isVisited() == false) {
						if (visitArr[changeRow][changeCol] == 0) {
							q.push(make_pair(changeRow, changeCol));
							visitArr[changeRow][changeCol] = 1;
						}
					}
				}
			}
		}
	}
	for (int i = 0; i < this->maxRow; i++) {
		delete[] visitArr[i];
	}
	delete[] visitArr;
	visitArr = nullptr;
	return flag;


}

void MapManager::backupMap()
{
	this->backup = new Node * [this->maxRow];
	for (int i = 0; i < this->maxRow; i++) this->backup[i] = new Node[this->maxCol];

	for (int i = 0; i < this->maxRow; i++) {
		for (int j = 0; j < this->maxCol; j++) {
			this->backup[i][j] = this->map[i][j];
		}
	}
}

void MapManager::saveMap()
{
	int row, col;
	ofstream fout("save.txt");

	fout << this->maxRow << " " << this->maxCol << endl;
	fout << this->difficult << endl;

	while (!minePos.empty()) {
		row = minePos.front().first;
		col = minePos.front().second;
		minePos.pop();
		fout << row << " " << col << endl;
	}

	fout << record.size() << endl;
	while (!record.empty()) {
		row = record.front().first;
		col = record.front().second;
		record.pop();
		fout << row << " " << col << endl;
	}
	// 행 열
	// difficult
	// 지뢰 위치
	// 방문 횟수
	// 방문한 것들
}

bool MapManager::loadMap()
{
	ifstream fin("save.txt");
	
	if (!fin.is_open()) return false;

	fin >> this->maxRow >> this->maxCol;
	this->total = this->maxRow * this->maxCol;
	map = new Node * [this->maxRow];
	for (int i = 0; i < this->maxRow; i++) map[i] = new Node[this->maxCol];

	fin >> this->difficult;
	this->totalMines = (this->total / 10) * difficult;
	this->openCount = this->total - this->totalMines;
	for (int i = 0; i < this->totalMines; i++) {
		int row, col;
		fin >> row >> col;
		map[row][col]._setMine();

		vector<int> rowChange;
		vector<int> colChange;

		rowChange.push_back(0);
		colChange.push_back(0);
		if (row - 1 >= 0) rowChange.push_back(-1);
		if (row + 1 < this->maxRow) rowChange.push_back(+1);
		if (col - 1 >= 0) colChange.push_back(-1);
		if (col + 1 < this->maxCol) colChange.push_back(+1);

		minePos.push(make_pair(row, col));

		for (int i = 0; i < rowChange.size(); i++) {
			for (int j = 0; j < colChange.size(); j++) {
				map[row + rowChange[i]][col + colChange[j]]._addMineCount();
			}
		}
	}
	this->backupMap();
	int visitCount;
	fin >> visitCount;
	for (int i = 0; i < visitCount; i++) {
		int row, col;
		fin >> row >> col;
		this->visitNode(row, col);
	}
	return true;
}
