#include "MineSweeper.h"

MineSweeper::MineSweeper()
	:key(0), menu(0)
{
	
	
}

void MineSweeper::start()
{
	bool isLoadComplete = true;
	cout << "201711425 정준원" << endl;
	while (this->menu != 1 && this->menu != 2) {
		this->showMenu();
		if (this->menu == 1) {
			int _maxRow, _maxCol;
			cout << "원하는 지뢰찾기 맵의 행과 열을 입력하세요" << endl;
			cin >> _maxRow >> _maxCol;

			cursor._setMax(_maxRow, (_maxCol - 1) * 2);
			manager.createMap(_maxRow, _maxCol);

		}
		if (this->menu == 2) {
			isLoadComplete = manager.loadMap();
			if (isLoadComplete) cursor._setMax(manager.getMaxRow(), (manager.getMaxCol() - 1) * 2);
			else cout << "저장된 파일이 없습니다!" << endl;
		}
		if ((this->menu == 1 || this->menu == 2) && isLoadComplete) {

			this->printMap();
			this->keyInput();
			if (this->key != 'q') {
				this->showResult();
				this->showReplay();
			}
		}
		if (this->menu == 0) break;
	}
}

void MineSweeper::keyInput()
{
	int nowRow, nowCol;
	bool flag;
	while (1) {
		if (_kbhit()) {
			this->key = _getch();
			if (this->key == 224) {
				this->key = _getch();
				switch (this->key) {
				case UP:
					cursor._up();
					break;
				case DOWN:
					cursor._down();
					break;
				case LEFT:
					cursor._left();
					break;
				case RIGHT:
					cursor._right();
					break;
				}
			}
			else if (this->key == ENTER) {
				nowRow = cursor._getRow()-1;
				nowCol = cursor._getCol();
				flag = manager.visitNode(nowRow, nowCol); // 여기 수정해서 종료조건?
				this->printMap();
				if (!flag || manager.getOpenCount() == 0) { // 지뢰를 눌렀을 때
					Sleep(2000);
					return;
				}
			}
			else if (this->key == 'q') {
				manager.saveMap();
				break; // 여기에 백업 설정하기
			}
		}
	}
}

void MineSweeper::printMap()
{
	system("cls");
	cout << "201711425 정준원" << endl;
	manager.printMap();
	cursor._gotoHave();
}

void MineSweeper::showResult()
{
	system("cls");
	cursor._gotoxy(0, 0);
	cout << "201711425 정준원" << endl;
	cout << "######" << (manager.getOpenCount()!=0? "LOSE" : "VICTORY") <<  " : 이번 게임 지도#######" << endl;
	manager.printResult();
	Sleep(5000);
}

void MineSweeper::showMenu()
{
	system("cls");
	cout << "201711425 정준원" << endl;
	cout << "###################지뢰 찾기###################" << endl;
	cout << "1. 게임 시작" << endl;
	cout << "2. 이어 하기" << endl; // 이어 하기 기능 추가후 변경
	cout << "0. 나가기" << endl;
	cin >> this->menu;
}

void MineSweeper::showReplay()
{
	
	cursor._gotoxy(0, 0);
	
	manager.showReplay();
}


