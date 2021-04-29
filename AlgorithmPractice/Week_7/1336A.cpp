#include<iostream>
#include<vector>
#include<algorithm>
 
using namespace std;
 
class Node{
public :
    int depth = 0;
    int childNum = 0;
};
 
vector<int> v[200005];
Node nodes[200005];
int score[200005];
 
int initDfs(int now, int parent, int depth){
    nodes[now].depth = depth + 1;
 
    for(int i=0; i<v[now].size(); i++){
       if(v[now][i] != parent){
           nodes[now].childNum += initDfs(v[now][i], now, nodes[now].depth);
       }   
    }
    return nodes[now].childNum +1;
}
 
int main(){
    int n, k, a, b;
    long long ans=0;
    cin >> n >> k;
 
    for(int i=0; i<n-1; i++){
        cin >> a >> b;
        v[a].push_back(b);
        v[b].push_back(a);
    }
 
    initDfs(1, 0, -1);
    for(int i=1; i<=n; i++) {
        score[i] = nodes[i].depth - nodes[i].childNum;
    }
    sort(score + 1, score + n + 1, greater<int>());
    for(int i=1; i<=k; i++)ans += score[i];
    cout << ans << endl;
    return 0;
}