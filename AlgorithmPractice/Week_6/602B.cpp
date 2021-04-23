#include<iostream>
#include<vector>

using namespace std;

int main(){
    int n, tmp, nowMax, nowMin, cnt=0, ans=-1;
    vector<int> v;
    cin >> n;

    for(int i=0; i<n; i++){
        cin >> tmp;
        v.push_back(tmp);
    }

    nowMax = v[0];
    nowMin = v[0];

    for(int i=0; i<n; i++){
        if(abs(nowMax - v[i]) <= 1 && abs(nowMin - v[i])<=1){
            cnt += 1;
            nowMax = max(nowMax, v[i]);
            nowMin = min(nowMin, v[i]);
        }
        else{
            ans = max(ans, cnt);
            cnt = 0;
            nowMax = v[i];
            nowMin = v[i];
            for(int j=i; j>=0; j--){
                if(abs(nowMax - v[j]) <= 1 && abs(nowMin - v[j]) <= 1){
                    cnt += 1;
                    nowMax = max(nowMax, v[j]);
                    nowMin = min(nowMin, v[j]);
                }
                else break;
            }
        }
    }
    ans = max(ans, cnt);
    cout << ans << endl;
    return 0;
}