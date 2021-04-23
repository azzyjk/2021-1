#include<iostream>
#include<vector>
#include<utility>
#include<algorithm>

using namespace std;

int dp[1000001];

bool cmp(pair<int, int> a, pair<int, int> b){
    return a.first < b.first;
}

int main(){
    int n, a, b, res=0;
    vector< pair<int, int> > v;
    cin >> n;
    for(int i=0; i<n; i++){
        cin >> a >> b;
        v.push_back(make_pair(a,b));
    }

    sort(v.begin(), v.end());

    int nowPos = v[0].first;
    int nowPow = v[0].second;
    int num = nowPos - nowPow;

    for(int i=0; i<n; i++){
        int nowPos = v[i].first;
        int nowPow = v[i].second;
        int num = nowPos - nowPow;

        auto iter = lower_bound(v.begin(), v.end(), pair<int, int>(num, 0)) - v.begin() -1 ;
        if(iter < 0) dp[nowPos] = 1;
        else dp[nowPos] = dp[v[iter].first] + 1;
    }

    for(int i=0; i<n; i++) res = max(res, dp[v[i].first]);

    cout << n - res << endl;

    return 0;
}