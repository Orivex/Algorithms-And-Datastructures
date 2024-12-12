#include <bits/stdc++.h>

using namespace std;

int get_lca(int a, int b);
int getKthAncestor(int node, int k);

int LOG = 0;
vector<int> parent;
vector<vector<int>> up;
vector<int> depth;

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int n;
    cin >> n;

    parent.resize(n);
    depth.resize(n);

    for (int i = 0; i < n-1; i++)
    {
        int a, b;
        cin >> a >> b;
        parent[b] = a;
    }
    


    while((1 << LOG) <= n)
        LOG++;

    up.resize(n, vector<int>(LOG));

    parent[0] = 0;
    for (int i = 0; i < n; i++)
    {
        up[i][0] = parent[i];
        if(i != 0)
            depth[i] = depth[parent[i]] + 1;
        
        for (int j = 1; j < LOG; j++)
        {
            up[i][j] = up[up[i][j-1]][j-1];
        }
    }

    int a, b;
    cin >> a >> b;

    cout << get_lca(a, b) << endl;

}

int get_lca(int a, int b) {
    if(depth[a] < depth[b])
        swap(a, b);

    int k = depth[a] - depth[b];
    a = getKthAncestor(a, k); // Make a and b on the same level (same depth)

    if(a == b) //already found lca
        return a;

    for (int j = LOG-1; j >= 0; j--)
    {
        if(up[a][j] != up[b][j]) {
            a = up[a][j];
            b = up[b][j];
        }
    }

    return parent[a]; //or parent[b] or up[a][0] or up[b][0]
}

int getKthAncestor(int node, int k) {
    for (int j = 0; j < LOG; j++)
    {
        if(k & (1 << j))
            node = up[node][j];
    }
    return node;
}