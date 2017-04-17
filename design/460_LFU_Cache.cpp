class LFUCache {
public:
    LFUCache(int capacity) {
        cap = capacity;
        minFreq = 0;
    }
    
    int get(int key) {
        if (keymap.find(key) == keymap.end()) {
            return -1;
        }
        /*update*/
        freqmap[keymap[key].second].erase(match[key]);
        (keymap[key].second)++;
        freqmap[keymap[key].second].push_back(key);  // no need to init list
        match[key] = --freqmap[keymap[key].second].end();
        /*Once removed from old freq bucket, need to update minFreq into valid value*/
        while(freqmap[minFreq].empty()) minFreq++;
        return keymap[key].first;
    }
    
    void put(int key, int value) {
         /*if updates, no change of capacity*/
         if(cap <=0) return;
         if(get(key)!=-1) {
             keymap[key].first = value;
             return;
         }
         if (keymap.size()>=cap){
             /*evict minFreq head element*/
             keymap.erase(freqmap[minFreq].front());
             match.erase(freqmap[minFreq].front());
             freqmap[minFreq].pop_front();
         }
         keymap[key] = {value, 1};
         freqmap[1].push_back(key);
         match[key] = --freqmap[1].end();
         minFreq = 1;
    }
private:
    /*key -> (value, freq)*/
    int minFreq;
    int cap;
    unordered_map<int, pair<int, int>>keymap;
    /*freq -> listof <key>*/
    unordered_map<int, list<int>>freqmap;
    /*find <key, position of key of freqmap>*/
    unordered_map<int, list<int>::iterator>match;
};

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
