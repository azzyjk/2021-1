#include"MovieManager.h"

#ifdef _DEBUG
#ifndef DBG_NEW
#define DBG_NEW new (_NORMAL_BLOCK, __FILE__, __LINE__)
#define new DBG_NEW
#endif
#endif

MovieManager makeManager(string fileName) {
	//MovieManager m(fileName);
	//return m;
	return MovieManager(fileName);
}

Movie makeMovie(string name, int raw, int col) {
	Movie a(name, raw, col);
	return a;
}

int main() {
	cout << "201711425  정준원" << endl;
	_CrtSetDbgFlag(_CRTDBG_ALLOC_MEM_DF | _CRTDBG_LEAK_CHECK_DF);
	//Movie movie("그린조아", 3, 5);
	//Movie movie2(movie);
	//Movie movie = makeMovie("그린조아", 3, 5);
	MovieManager manage("./movie.txt");
	//MovieManager manage2(manage);
	//MovieManager manage = makeManager("./movie.txt");
	manage.startMenu();
	return 0;
}