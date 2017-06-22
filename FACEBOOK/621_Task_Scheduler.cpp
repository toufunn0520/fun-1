class Solution {
public:
    int leastInterval(vector<char>& tasks, int n) {
        unordered_map<char,int> m;
        m.clear();
        priority_queue<pair<int,char>> q;

        for (auto it: tasks){
            m[it]++;
        }
        for (auto it: m){
            q.push(make_pair(it.second,it.first));
        }
        int res = 0;
        int circle = n+1;
        
        while (!q.empty()) {
            /*RRB*/
            int curnum = 0;
            vector<pair<int, char>> curround;
            for (int i = 0; i < circle; i++) {
                if(q.empty()) break;
                curround.push_back(q.top());   // no front
                q.pop();
                curnum ++;  // only the last round 
            }
            for(auto it: curround){
                if(it.first>=2){
                    it.first--;
                    q.push(it);    // put it back if bigger than 0
                }
            }
            res += q.empty()?curnum: circle;
        }
        return res;
    }
};
