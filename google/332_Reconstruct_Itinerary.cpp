/* 返回的答案必为n+1，n为tickets的个数 use map ->ordered   
 * 一张飞机票只能用一次，所以要计数。（可能重复）count to mark if this path is been visited。
 * 当 res 长度 = n+1 马上return brute force 
 */
/*图论： Eulerian path Greedy DFS*/
class Solution {
public:
    bool dfs (string start, int n) {
        if (res.size() == n+1) return true;
        
        for (auto &next: mmap[start]){   // key pass by reference
            if (next.second) {
                next.second--;
                res.push_back(next.first);
                if(dfs(next.first, n)){ return true; }
                res.pop_back();
                next.second++;
            }
        }
        
        return false;
    }
    
    vector<string> findItinerary(vector<pair<string, string>> tickets) {
        for(auto it: tickets){
            mmap[it.first][it.second]++;
        }
        res.push_back("JFK");
        dfs("JFK", tickets.size());
        return res;
    }
    
    unordered_map<string, map<string, int>> mmap;
    vector<string>res;
};


vector<string> findItinerary(vector<pair<string, string>> tickets) {
    for (auto ticket : tickets)
        targets[ticket.first].insert(ticket.second);
    visit("JFK");
    return vector<string>(route.rbegin(), route.rend());
}

map<string, multiset<string>> targets;
vector<string> route;

void visit(string airport) {
    while (targets[airport].size()) {
        string next = *targets[airport].begin();
        targets[airport].erase(targets[airport].begin());
        visit(next);
    }
    route.push_back(airport);
}
