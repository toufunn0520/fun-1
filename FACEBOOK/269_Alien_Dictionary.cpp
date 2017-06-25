/*Topology sort*/

class Solution {
public:
    string alienOrder(vector<string>& words) {
        
        unordered_map<char, set<char>> in, reverse;  // records -> as well as revese relations
        set<char> total;  // mark each character once finish res need to check if all char is used
        string res ;
        string pre ;  // previous from list
        for (int i = 0 ; i < words.size() ; i++) {
            
            total.insert(words[i].begin(), words[i].end());
            int cur = words[i].size();
            int presize = pre.size();
            
            for (int j =0; j<cur && j< presize; j++) {
                char a = words[i][j], b = pre[j];
                if (a!=b) {   // can't go beyond e.g za zb ca  the 2nd a is OK to appear after 1st b not invalid
                    in[a].insert(b);   //b->a 
                    reverse[b].insert(a);  //revese map to decrement a's indegree once b needs to be removed
                    break;
                }
            }
            pre = words[i];
        }

        set<char> start = total;  
        for(auto it: in){
            start.erase(it.first);
        }  // remaining are those indegree==0
            
        while(!start.empty()){
            char cur = *start.begin();  // get the 1st from set
            start.erase(cur);
            res += cur;
            for(char next: reverse[cur]){
                in[next].erase(cur);
                if(in[next].empty()) start.insert(next);
            }
        }
        return res.size()==total.size()?res:"";
    }
};
