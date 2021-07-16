#pragma once
#include <iostream>
#include <conio.h>

#include "CursorManager.h"
#include "MapManager.h"

#define LEFT 75 
#define RIGHT 77 
#define UP 72 
#define DOWN 80 
#define ENTER 13

using namespace std;

class MineSweeper
{
private:
	int key, menu;
	MapManager manager;
	CursorManager cursor;
public:
	MineSweeper();
	void start();
	void keyInput();
	void printMap();
	void showResult();
	void showMenu();
	void showReplay();
};

