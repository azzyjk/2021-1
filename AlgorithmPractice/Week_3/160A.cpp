#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

int main(){
    int n, tmp, sum=0, resSum=0;
    double sum2 = 0;
    vector<int> v;

    cin >> n;

    for(int i=0; i<n; i++){
        cin >> tmp;
        sum += tmp;
        v.push_back(tmp);
    }

    sort(v.begin(), v.end(), greater<int>());

    sum2 = sum/double(2);

    for(int i=0; i<v.size(); i++){
        resSum += v[i];
        if(resSum > sum2){
            cout << i+1 << endl;
            break;
        } 
    }

    return 0;
}