/*DFS*/
class Solution {
public:
    void helper (vector<string>& res, long curval, long preval, string express, string num,int target, int index){
        //printf("check %s\n", express.c_str());
        if (curval == target && index == num.size() ) {
            //printf("Binge val = %d and express=%s\n", curval, express.c_str());
            res.push_back(express);
            return;
        }
        for(int i = 1; i<=num.size()-index; i++){  // don't forget the "="
            string nextnum = num.substr(index,i);
            long nextval = stol(nextnum);
            if (i > 1 && nextnum[0] == '0') continue; // invalidate numbers with preceeding 0 e.g05 
            helper(res, curval+nextval,nextval,express+"+"+nextnum,num, target, i+index);
            helper(res, curval-nextval,-nextval,express+"-"+nextnum,num, target, i+index);
            helper(res, curval-preval+preval*nextval,preval*nextval,express+"*"+nextnum,num, target, i+index);   // multiplier need to dec and recal
        }
    }
    
    vector<string> addOperators(string num, int target) {
        vector<string> res; 
        res.clear();
        if (!num.size()) return res;
        for (int i = 1; i<=num.size(); i++) {     // don't forget the "="
            string express = num.substr(0,i);
            long curval = stol(express);
            if (i > 1 && express[0] == '0') continue; // invalidate numbers with preceeding 0 e.g05 
            helper(res,curval,curval,express,num,target,i);
        }

        return res;
    }
};
