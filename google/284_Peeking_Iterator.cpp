// Below is the interface for Iterator, which is already defined for you.
// **DO NOT** modify the interface for Iterator.
class Iterator {
    struct Data;
	Data* data;
public:
	Iterator(const vector<int>& nums);
	Iterator(const Iterator& iter);
	virtual ~Iterator();
	// Returns the next element in the iteration.
	int next();
	// Returns true if the iteration has more elements.
	bool hasNext() const;
};


class PeekingIterator : public Iterator {
public:
    stack<int>pk;
    Iterator *it;
	PeekingIterator(const vector<int>& nums) : Iterator(nums) {
	    it = this;
	}
    // Returns the next element in the iteration without advancing the iterator.
	int peek() {
        if (pk.empty()){
            pk.push(it->next());
        }
        return pk.top();
	}
	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	int next() {
	    int val ;
	    if(!pk.empty()){
	        val = pk.top();
	        pk.pop();
	    }else {
	        val = it->next();
	    }
	    return val;
	}
	
	bool hasNext() const {
	    return (!pk.empty()||it->hasNext());
	}
};
