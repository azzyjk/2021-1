#include<iostream>

using namespace std;

int main(){
    int k2, k3, k5, k6;
    long long sum=0;
    cin >> k2 >> k3 >> k5 >> k6;
    while(k2>0){
        if(k2 > 0 && k5 > 0 && k6 > 0){
            k2 -= 1;
            k5 -= 1;
            k6 -= 1;
            sum += 256;
        }
        else if(k2 > 0 && k3 >0){
            k2 -= 1;
            k3 -= 1;
            sum += 32;
        }
        else k2 = 0;
    }
    cout << sum << endl;
    return 0;
}