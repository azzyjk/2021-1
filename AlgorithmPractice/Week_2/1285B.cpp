#include<iostream>
#include<vector>

using namespace std;

int main(){
    int t, n, res;
    long long tmp;

    cin >> t;
    for(int i=0; i<t; i++){
        res = 1;
        cin >> n;
        vector<long long> v, minV, minV_1;
        v.push_back(0);
        minV.push_back(0);
        minV_1.push_back(1000000000);
        for(int j=1; j<=n; j++){
            cin >> tmp;
            v.push_back(v[j-1]+tmp);
        }
        for(int j=1; j<=n; j++){
            minV.push_back(min(minV[j-1], v[j]));
            minV_1.push_back(min(minV_1[j-1], v[j]));
        }
            
        for(int j=1; j<n; j++) if((v[j] - minV[j-1] >= v[n])) res = 0; // 1 ~ n-1
        if((v[n] - minV_1[n-1]) >= v[n]) res = 0; // n at least except 1
        if(res == 0) cout << "NO" << endl;
        else cout << "YES" << endl;
    }
    return 0;
}