#include<iostream>

using namespace std;

int main(){
    int w;
    cin >> w;
    if(w < 4 || w%2 != 0) cout << "NO" << endl;
    else cout << "YES" << endl;
    return 0;
}