class Solution {
public:
    vector<int> findOrder(int numCourses, vector<pair<int, int>>& prerequisites) {
        vector<unordered_set<int>> dag(numCourses);
        vector<int>in (numCourses,0);
        queue<int>q;
        vector<int>res;
        res.clear();
        /*[Y,X] pair , generate X->Y and in[Y]++*/
        for (auto it: prerequisites) {
            dag[it.second].insert(it.first);
            in[it.first]++;
        }
        /*Find 1st or several indegress = 0*/
        for (int i=0; i < numCourses; i++) {
            if(!in[i]) {
                q.push(i);
                res.push_back(i);
            }
        }
        
        while (!q.empty()) {
            int cur = q.front();
            q.pop();
            for (auto it: dag[cur]){
                if((--in[it]) == 0) {
                    q.push(it);
                    res.push_back(it);
                }
            }
        }
        
        if(res.size()!= numCourses) res.clear();  // extra compared to I if res didn't cover all nodes, return NULL
        return res;
        }
};
