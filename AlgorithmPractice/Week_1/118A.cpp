#include<iostream>

using namespace std;

int main(){
    string str;
    cin >> str;

    for(int i=0; i<str.length(); i++){
        if(char(tolower(str[i])) == 'a' ||
        char(tolower(str[i])) == 'o' ||
        char(tolower(str[i])) == 'y' ||
        char(tolower(str[i])) == 'e' ||
        char(tolower(str[i])) == 'u' ||
        char(tolower(str[i])) == 'i'
        ) continue;
        else cout << "." << char(tolower(str[i]));
    }
    cout << endl;
    
    return 0;
}