#include<iostream>
#include<fstream>
#include<memory>

using namespace std;

int main() {
    int raw, col;
    cout << "201711425 정준원" << endl;
    ifstream fin("Week_4/Homework/map.txt");

    if (!fin.is_open()) {
        cerr << "File is not open!" << endl;
        return 1;
    }

    fin >> raw >> col;

    unique_ptr<unique_ptr<int[]>[]> map = make_unique< unique_ptr<int[]>[]>(raw);

    for (int i = 0; i < raw; i++) {
        map[i] = make_unique<int[]>(col);
    }

    for (int i = 0; i < raw; i++) {
        for (int j = 0; j < col; j++) {
            fin >> map[i][j];
        }
    }
    
    for (int i = 0; i < raw; i++) {
        for (int j = 0; j < col; j++) {
            cout << (map[i][j]? "●" : "○") << " ";
        }
        cout << endl;
    }

    return 0;
}