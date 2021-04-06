#include<iostream>

using namespace std;

long long ans=0;

void divideQ(long long start, long long end, long long l, long long r, long long num){
    if(start > end || l > r) return;
    long long mid = (start + end) / 2;

    if(mid > r) divideQ(start, mid-1, l, r, num/2);
    else if(mid < l) divideQ(mid+1, end, l, r, num/2);
    else{
        ans += num%2;
        divideQ(start, mid-1, l, mid-1, num/2);
        divideQ(mid+1, end, mid+1, r, num/2);
    }

}

int main(){
    long long n, l, r, length=1;

    cin >> n >> l >> r;

    long long nCopy = n;

    while(nCopy > 1){
        nCopy = nCopy/2;
        length = (length*2) + 1;
    }

    divideQ(1, length, l, r, n);
    cout << ans << endl;
    return 0;
}