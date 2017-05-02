class LFUCache {
public:
    LFUCache(int capacity) {
        cap = capacity;
        minFreq = 0;
        map.clear();
        freq.clear();
        match.clear();
    }
    
    int get(int key) {
        if (map.find(key) == map.end()) return -1;
        int value = map[key].second;
        (map[key].first)++;
        int f = map[key].first;   !!! keep in mind
        freq[f].erase(match[key]);
        freq[f+1].push_back(key);
        match[key] = --freq[f+1].end();
        //update minfreq 
        while(freq[minFreq].empty()){minFreq++;}
        return value;
    }
    
    void put(int key, int value) {
        if(cap < 1) return;
        /*No size changing*/
        if (get(key)!=-1) {
            map[key].second = value;
            return;
        }
        /*Eviction*/
        if(map.size() >= cap){
            int evict = freq[minFreq].front();
            map.erase(evict);
            match.erase(evict);
            freq[minFreq].pop_front();
        }
        /*New element*/
        map[key] = make_pair(1,value);
        freq[1].push_back(key);
        minFreq =1;
        match[key] = --freq[1].end();
        return;
    }
    /*<key, freq, value>*/
    int cap;
    int minFreq;
    unordered_map<int, pair<int, int>> map;
    //freq -> list of key
    unordered_map<int, list<int>> freq;
    // key ->location on freq
    unordered_map<int, list<int>::iterator> match;
};
