/*Methods I:  Keep<msg, timestamp>*/
/*Methods II: keep only 10s msg in the queue<time, msg> and unordered_set<msg> quicky check if a msg existed or not*/

/*Follow up: what if timestamp comes ooo
  std::map<char,int>::iterator itlow,itup;
  keep map<int, unordered_set<msg>>
*/
class Logger {
public:
    /** Initialize your data structure here. */
    Logger() {
        map.clear();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    bool shouldPrintMessage(int timestamp, string message) {
        bool res = false;
        if(!map.count(message)) {
            res = true;
        }else {
            if (map[message] <= timestamp){
                res = true;
            }
        }
        if(res == true) map[message] =  timestamp+10;
        //printf("%s time=%d res=%d\n", message.c_str(),map[message], res);
        return res;
    }
    
    private:
    unordered_map<string,int> map;
};



//timestamp based optimization

Class Logger {
public:
    Logger() {
        
    }
    
    bool shouldPrintMessage(int timestamp, string message) {
        while( !pq.empty() ){
            auto p = pq.front();
            if( timestamp - p.first >= 10 ){  // pop off old message
                pq.pop();
                map.erase(p.second);
            }
            else break;
        }
        if( map.insert(message).second ){  // if insert() success, it will return true
            pq.emplace(timestamp, message);
            return true;
        }
        return false;
    }
    
private:
    queue<pair<int, string>> pq; // since timestamp is in non-descending order, we can use queue
    unordered_set<string> map;
};
