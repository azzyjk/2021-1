#include<iostream>
#include<vector>
#include<cmath>

using namespace std;

int ar[5];

int main(){
    int n, tmp, res=0;
    cin >> n;
    for(int i=0; i<n; i++){
        cin >> tmp;
        ar[tmp] += 1;
    }
    
    res += ar[4] + ar[3] + (ar[2]/2);

    ar[1] -= ar[3];
    ar[2] -= (ar[2]/2) * 2;

    if(ar[2] > 0){
        res+=1;
        ar[1] -= 2;
    }
    if(ar[1] > 0){
        res += ceil(ar[1] / double(4));
    }

    cout << res << endl;
    
    return 0;
}