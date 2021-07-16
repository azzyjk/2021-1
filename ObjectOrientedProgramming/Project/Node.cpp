#include "Node.h"

Node::Node()
	:visit(false), mine(false), mineCount(0)
{
}

bool Node::_isVisited()
{
	return this->visit;
}

bool Node::_isMine()
{
	return mine;
}

bool Node::_visit()
{
	this->visit = true;
	if (this->mine == true) return false;
	return true;
}
