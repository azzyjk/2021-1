#include<iostream>

using namespace std;

long long ar[100005];
long long dp[100005];

int main(){
    long long n, tmp, maxRes = 0 ;
    cin >> n;

    for(long long i=0; i<n; i++){
        cin >> tmp;
        ar[tmp] += 1;
    }

    dp[1] = ar[1] * 1;
    dp[2] = ar[2] * 2;

    for(long long i=3; i<=100002; i++){
        dp[i] = max(dp[i-2], dp[i-3]) + (ar[i]*i);
    }

    cout << dp[100002] << endl;

    return 0;
}