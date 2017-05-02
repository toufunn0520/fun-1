class Solution {
public:
    bool isValid(string s) {
        stack<char> tmp;
        for (char c: s){   // remember
            if(c == '(' || c=='{' || c=='[') {
                tmp.push(c);
            } else if (c == ')' || c == '}' || c== ']'){
                if(tmp.empty()) return false;   //key
                if(tmp.top() != '(' && c ==')') return false;
                if(tmp.top() != '{' && c =='}') return false;
                if(tmp.top() != '[' && c ==']') return false;
                tmp.pop();
            }
        }
        return tmp.empty(); //key
    }
};
