#include<iostream>
#include<vector>
#include<algorithm>
#include<climits>

using namespace std;

long long dp1[100001], dp2[100002];

int main(){
    long long n, tmp;
    string tmp2;
    vector<long long> c;
    vector<string> strV, rStrV;
    bool isChange=true;

    fill_n(dp1, 100001, -1);
    fill_n(dp2, 100001, -1);

    cin >> n;

    c.push_back(0);
    strV.push_back(" ");
    rStrV.push_back(" ");
    dp1[0] = 0;
    dp2[0] = 0;
    for(int i=0; i<n; i++){
        cin >> tmp;
        c.push_back(tmp);
    }

    for(int i=0; i<n; i++){
        cin >> tmp2;
        strV.push_back(tmp2);
        reverse(tmp2.begin(), tmp2.end());
        rStrV.push_back(tmp2);
    }

    int i =1;
    while(i <= n && isChange){
        isChange = false;
        if(dp1[i-1] != -1 && strV[i-1] <= strV[i]) {
            dp1[i] = dp1[i-1];
            isChange = true;
        }
        if(dp1[i-1] != -1 && strV[i-1] <= rStrV[i]) {
            dp2[i] = dp1[i-1] + c[i];
            isChange = true;
        }
        if(dp2[i-1] != -1 && rStrV[i-1] <= strV[i]) {
            if(dp1[i]!= -1) dp1[i] = min(dp1[i], dp2[i-1]);
            else dp1[i] = dp2[i-1];
            isChange = true;
        }
        if(dp2[i-1] != -1 && rStrV[i-1] <= rStrV[i]){
            if(dp2[i] != -1) dp2[i] = min(dp2[i], dp2[i-1] + c[i]);
            else dp2[i] = dp2[i-1] + c[i];
            isChange = true;
        } 
      
        if(isChange == true) i += 1;
    }

    if(i-1!=n) cout << -1 << endl;
    else if(dp1[n] != -1 && dp2[n] != -1) cout << min(dp1[n], dp2[n]) << endl;
    else if(dp1[n] == -1) cout << dp2[n];
    else if(dp2[n] == -1) cout << dp1[n];
    // for(int i=1; i<=n; i++){
    //     if(strV[i-1] <= strV[i]) dp1[i] = dp1[i-1];
    //     if(rStrV[i-1] <= strV[i]) dp1[i] = min(dp2[i-1], dp1[i]);
    //     if(strV[i-1] <= rStrV[i]) dp2[i] = dp1[i-1] + c[i];
    //     if(rStrV[i-1] <= rStrV[i]) dp2[i] = min(dp2[i-1] + c[i], dp2[i]);
    // }

    // if(dp1[n] == LONG_MAX && dp2[n] == LONG_MAX) cout << -1 << endl;
    // else cout << min(dp1[n], dp2[n]) << endl;
    

    return 0;
}