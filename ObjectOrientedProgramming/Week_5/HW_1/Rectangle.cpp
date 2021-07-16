#include "Rectangle.h"

Rectangle::Rectangle():Rectangle(1,1) {

}
Rectangle::Rectangle(int num):Rectangle(num, num) {

}
Rectangle::Rectangle(int height, int width):height(height), width(width) {

}
bool Rectangle::isSquare() {
	if (height == width) return true;
	return false;
}