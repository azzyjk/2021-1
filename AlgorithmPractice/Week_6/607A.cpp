#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

int  dp[1000001];
vector< pair<int, int> > v;

int lowerBound(int start, int end, int find){
    int mid;
    while(start < end){
        mid = (start + end) / 2;
        if(v[mid].first == find) return mid;
        if(v[mid].first > find) end = mid;
        else start = mid + 1;
    }
    return start;
}

bool sorted(pair<int, int> a, pair<int, int> b){
    return a.first < b.first;
}

int main(){
    int n, a, b, num, res=0, firstPos=0;
    
    cin >> n;
    for(int i=0; i<n; i++){
        cin >> a >> b;
        v.push_back(make_pair(a, b));
        
    }
    sort(v.begin(), v.end(), sorted);
    firstPos = v[0].first;
    
    for(int i=0; i<n; i++){
        int nowPos = v[i].first;
        int nowPow = v[i].second;
        num = nowPos - nowPow;
        int iter = lowerBound(0, n-1, num)-1;
        if(iter < 0) dp[nowPos] = 1;
        else dp[nowPos] = dp[v[iter].first] + 1;
    }

    for(int i=0; i<n; i++) res = max(res, dp[v[i].first]);
    cout << n - res  << endl;
    return 0;
}