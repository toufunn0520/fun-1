
/*
 Topology sort using BFS  
      0) [X,Y] Y->X translation (pre-gen this mapping [one to manny])
      1) indegree arrary (pre-gen 'X')
      2) any indegree=0 need to push into queue 
      3) Update their direct next hop by decre 1. If those next hop indegree becomes o, push them into the queue.[till queue.size=0]
      4) Finally check if any indegree is still bigger than 0. Which indicate there is a loop!!!! not a DAG! return false
*/

class Solution {
public:
    bool canFinish(int numCourses, vector<pair<int, int>>& prerequisites) {
            vector<unordered_set<int>> dag (numCourses);
            queue<int> q; 
            vector<int> in(numCourses,0);
            
            for (auto it: prerequisites) {
                dag[it.second].insert(it.first);  // iterate
                in[it.first]++;
            }
            
            for (int i = 0; i<numCourses; i++) {
                if(in[i] == 0) q.push(i);
            }
            
            while(!q.empty()){
                int cur = q.front();
                q.pop();
                for(auto it: dag[cur]){
                    if ((--in[it]) == 0) q.push(it);  // iterate and stupid --
                }
            }
            for (int i = 0; i<numCourses; i++) {                
                if (in[i]) return false;
            }
            
            return true;
        }

};
