/*Once we encounter those bigger interval, we exit 1st for loop and cp remaining 
* For Smaller interval, simply cp over to final res.
* Keep updating mergable interval by taking the min of start, max of end!
*/

class Solution {
public:
    vector<Interval> insert(vector<Interval>& intervals, Interval newInterval) {
        vector<Interval> res;
        auto it = intervals.begin();
        for( ;it!=intervals.end(); it++){
            int s = newInterval.start;
            int e = newInterval.end;
            if(e<(*it).start){
                break;
            }else if(s>(*it).end) {
                res.push_back(*it);
            }else {
                newInterval.start = min((*it).start,s);
                newInterval.end = max((*it).end,e);
            }
        }
        res.push_back(newInterval);     // key! what is intervals is NULL and we need to insert newInterval

        for( ;it!=intervals.end(); it++){
            res.push_back(*it);
        }
        return res;
    }
};
