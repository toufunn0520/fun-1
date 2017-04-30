class Solution {
public:
    vector<vector<string>> ans;
    unordered_set<string> dict;
    vector<vector<string>> findLadders(string beginWord, string endWord, vector<string>& wordList) {
        ans.clear();
        dict.clear();
        int len = beginWord.size();
        int lene = endWord.size();
        if (len != lene || len == 0 || !wordList.size() ) return ans;
        /*Convert vector into set*/
        vector<string>::iterator it;
        for ( it=wordList.begin(); it!=wordList.end(); it++) {
            dict.insert(*it);
        }
        /*Important!  */
        if(dict.find(endWord) == dict.end()) return ans;
        /*Next String list*/
        unordered_map<string, vector<string>> nexts;
        vector<string> next;
        unordered_map<string, int> depth;
        queue<string> bfs;
        bfs.push(beginWord);
        depth[beginWord] = 0;
        while(!bfs.empty()){
           string cur = bfs.front();
           //!!!important to end the bfs if find endWord
           if (cur == endWord) break;
           int step = depth[cur];
           bfs.pop();
           vector<string> nextlevel;
           /*26*len possibilities*/
           for (int i = 0; i<len; i++){
               string tryone = cur;
               for (char c = 'a'; c <='z'; c++) {
                   tryone[i] = c;
                   if(tryone[i] == cur[i] || dict.find(tryone) == dict.end()) continue;
                   auto it = depth.find(tryone);
                   /*The 1st time update into bfs and depth*/
                   if (it == depth.end()) {
                        depth[tryone] = step+1;
                        bfs.push(tryone);
                   }
                   /*THe next valid result for cur*/
                   nextlevel.push_back(tryone);
                }
           }
           nexts[cur] = nextlevel;
        }
        next.push_back(beginWord);
        dfs(next, beginWord, endWord, nexts, depth);
        return ans;
    }
    private:
        void dfs(vector<string>& curpath, string start, string end, unordered_map<string, vector<string>>& nexts, unordered_map<string, int>& depth) {
            if (start == end) {ans.push_back(curpath); return; }
            int d = depth[start];
            auto n = nexts[start];
            for (int i = 0; i<n.size(); i++) {
                if (depth[n[i]] != d + 1) continue;
                /*if deopth matches*/
                curpath.push_back(n[i]);
                dfs(curpath, n[i], end, nexts,depth);
                curpath.pop_back();
            }
        }
};
