class MedianFinder {
public:
    /** Initialize your data structure here. */
    priority_queue<long> small, large;   
    MedianFinder() {
    }
    
    void addNum(int num) {
        small.push(num);
        large.push(-small.top());
        small.pop();
        if (small.size()<large.size()) {
            small.push(-large.top());    
            large.pop();
        }
    }
    
    double findMedian() {
        if(small.size()>large.size()) return small.top();
        return (small.top()-large.top())/2.0;
    }
};
