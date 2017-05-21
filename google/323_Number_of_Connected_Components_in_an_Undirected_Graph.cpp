class Solution {
public:
    int countComponents(int n, vector<pair<int, int>>& edges) {
        vector<int> res(n);
        int count = n;
        for (int i = 0; i< n; i++) {
            res[i] = i;
        }
        for (auto& it: edges) {
            int left = it.first;
            int right = it.second;
            while(res[left]!=left) left = res[left] = res[res[left]];
            while(res[right]!=right) right = res[right] = res[res[right]];
            res[left] = right;
            if (right != left) {
                count --;
            }
        }
        return count;
    }
};

/*Better Union FInd*/
vector<int> id;
public:
int find(int i) {
    while (i != id[i]) {
        id[i] = id[id[i]]; // compression
        i = id[i];
    }
    return i;
}
void unions(int p, int q) {
    int x = find(p);
    int y = find(q);
    id[x] = y;
}
int countComponents(int n, vector<pair<int, int>>& edges) {
    id.resize(n);
    for (int i = 0; i < n; i++) {
        id[i] = i;
    }
    int count = 0;
    for (const auto& edge: edges) {
        int x = find(edge.first);
        int y = find(edge.second);
        if (x != y) {
            unions(edge.first, edge.second);
            count++;
        }
    }
    return n-count;
}
