#include <bits/stdc++.h>

using namespace std;

int getKthAncestor(int node, int k, int LOG);

vector<int> parent;
vector<vector<int>> up;

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int n;
    cin >> n;

    parent.resize(n);

    for (int i = 0; i < n-1; i++)
    {
        int a, b;
        cin >> a >> b; // a ist parent of b
        parent[b] = a;
    }
    

    int LOG = 0;

    while((1 << LOG) <= n) {
        LOG++;
    }

    up.resize(n, vector<int>(LOG));

    parent[0] = 0;
    for (int i = 0; i < n; i++)
    {
        up[i][0] = parent[i];

        for (int j = 1; j < LOG; j++)
        {
            up[i][j] = up[up[i][j-1]][j-1];
        }

    }

    int node, k;
    cin >> node >> k;

    cout << getKthAncestor(node, k, LOG) << endl;
}

int getKthAncestor(int node, int k, int LOG) {
    for (int j = 0; j < LOG; j++)
    {
        if(k & (1 << j)) {
            node = up[node][j];
        }
    }
    return node;
}