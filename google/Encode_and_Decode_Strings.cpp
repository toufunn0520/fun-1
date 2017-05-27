class Codec {
public:
    // Encodes a list of strings to a single string.
    string encode(vector<string>& strs) {
        string res = "";
        for(string &s: strs){   // key
            res += to_string(s.size())+'@'+ s;
        }
        return res;
    }
    // Decodes a single string to a list of strings.
    vector<string> decode(string s) {
        vector<string> res;
        int len = s.size();
        int start = 0;
        for (int i = 0; i<len; i++){
            if (s[i] =='@') {
                int cur = stoi(s.substr(start,i-start));
                res.push_back(s.substr(i+1,cur));
                i=i+cur+1;
                start = i;
            }
        }
        return res;
    }
};
