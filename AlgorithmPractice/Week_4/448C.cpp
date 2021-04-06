#include<iostream>
#include<vector>

using namespace std;

vector<int> v;

int divideQ(int start, int end){
    int verticalAns = end - start + 1, horizonAns=0, minVal = 1000000001;
    int newStart = start, newEnd;

    for(int i=start; i<=end; i++){
        minVal = min(minVal, v[i]);
    }

    horizonAns += minVal;

    for(int i=start; i<=end; i++){
        v[i] -= minVal;
    }

    while(newStart <= end){
        while(newStart <= end && v[newStart] == 0) newStart ++;
        newEnd = newStart;
        while(newEnd <= end && v[newEnd] !=0) newEnd ++;
        horizonAns += divideQ(newStart, newEnd-1);
        newStart = newEnd;
    }
    return min(verticalAns, horizonAns);
}

int main(){
    int n, tmp, res=0;

    cin >> n;

    for(int i=0; i<n; i++){
        cin >> tmp;
        v.push_back(tmp);
    }
    
    res = divideQ(0, v.size()-1);
    cout << res << endl;

    return 0;
}