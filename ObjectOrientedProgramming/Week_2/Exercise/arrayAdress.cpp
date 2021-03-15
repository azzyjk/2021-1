#include<iostream>

using namespace std;

int main(){
    int test[10]{};
    char str[10];

    // str = 'a'; error

    for(char i : str) cout << i << endl;
    for(int i : test) cout << i << endl;

    return 0;
}