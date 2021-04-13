#include<iostream>

using namespace std;

int dp[4001];

int main(){
    int n, a, b, c;
    cin >> n >> a >> b >> c;

    for(int i=a; i<=n; i+=a){
        dp[i] = dp[i-a]+1;
    }
  
    for(int i=1; i<=n; i++){
        if(i%b == 0) dp[i] = max(dp[i], dp[i-b]+1);
        else if(dp[i] != 0 && i+b <= n) dp[i+b] = max(dp[i+b], dp[i]+1);
    }
    
    for(int i=1; i<=n; i++){
        if(i%c == 0) dp[i] = max(dp[i], dp[i-c]+1);
        else if(dp[i] != 0 && i+c <= n) dp[i+c] = max(dp[i+c], dp[i]+1);
    }
    cout << dp[n] << endl;
    return 0;
}