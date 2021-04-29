#include<iostream>
#include<vector>

using namespace std;

class Node{
public:
    int parentN=0;
    int chdNum=0;
    int height=0;
};

int n;
double res;
vector<int> v[100005];
Node nodes[100005];


void _initDfs(int now, int parent){
    nodes[now].parentN = parent;
    nodes[now].height = nodes[parent].height + 1;

    for(int i=0; i<v[now].size(); i++){
        if(v[now][i] != parent){
            nodes[now].chdNum += 1;
            _initDfs(v[now][i], now);
        }
    }
    
}

void _dfs(int now, double probability){
    if(nodes[now].chdNum == 0){
        res += probability * double(nodes[now].height-1);
    }
    else{
        for(int i=0; i<v[now].size(); i++){
            if(nodes[now].parentN != v[now][i]) _dfs(v[now][i], probability*1/double(nodes[now].chdNum));
        }
    }
}

int main(){
    cin >> n;
    int a, b;

    for(int i=0; i<n-1; i++){
        cin >> a >> b;
        v[a].push_back(b);
        v[b].push_back(a);
    }

    _initDfs(1, 0);
    _dfs(1, 1);
    cout << fixed;
    cout.precision(10);
    cout << res << endl;
    return 0;
}