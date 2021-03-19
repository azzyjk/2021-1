#include<iostream>
#include<vector>

using namespace std;

int main(){
    long long n, k, tmp;
    double max=0.0;
    vector<long long> v;

    cin >> n >> k;
    
    v.push_back(0);

    for(int i=1; i<=n; i++){
        cin >> tmp;
        v.push_back(v[i-1]+tmp);
    }

    for(int j=k; j<=n; j++){
        for(int i=j; i<=n; i++){
            if(((double(v[i])-double(v[i-j]))/double(j)) > max) max = ((double(v[i])-double(v[i-j]))/double(j));
        }
    }
    cout.precision(10);
    cout << max << endl;
    return 0;
}