#include<iostream>

using namespace std;

int ar[101];
int dp[101];

int main(){
    int n, cnt=0, maxRes=0;
    bool allOne = true;
    cin >> n;

    for(int i=1; i<=n; i++) cin >> ar[i];
    
    for(int i=1; i<=n; i++){
        if(ar[i] == 1){
            cnt += 1;
            if(dp[i-1] > 0) dp[i] = dp[i-1] - 1;
        }
        else{
            dp[i] = dp[i-1] + 1;
        }
    }

    for(int i=1; i<=n; i++){
        maxRes = max(maxRes, dp[i]);       
    }

    for(int i=1; i<=n; i++) if(ar[i] == 0) allOne = false;

    if(allOne == true) maxRes = -1;

    cout << cnt + maxRes << endl;
    
    return 0;
}