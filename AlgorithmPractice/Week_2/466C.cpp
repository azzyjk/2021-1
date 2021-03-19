#include<iostream>
#include<vector>

using namespace std;

int main(){
    long long n, tmp, res=0, res3=0;
    vector<long long> v;
    cin >> n;
    v.push_back(0);
    for(int i=1; i<=n; i++){
        cin >> tmp;
        v.push_back(tmp+v[i-1]);
    }
    long long total = v[n];

    if((total % 3) != 0) cout << 0 << endl;
    else{
        long long total3 = total / 3;
        if(v[1]==total3) res3 += 1;
        for(int i=2; i<=n-1; i++){
            if(v[i] == (total3*2)) res += res3;
            if(v[i] == total3) res3 += 1;
        }
        cout << res << endl;
    }


    return 0;
}