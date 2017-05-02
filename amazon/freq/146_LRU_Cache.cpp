class LRUCache {
public:
    int size;
    unordered_map<int,list<pair<int,int>>::iterator> map; // no need double linked list, saved the iterator
    list<pair<int, int>> data;  // key and value
    
    LRUCache(int capacity) {
        size = capacity;
        map.clear();
        data.clear();
    }
    
    int get(int key) {
        if (map.find(key) != map.end()){
            auto it = map[key];
            data.erase(it);
            data.push_front(make_pair(key,it->second)); // list method: push_front/erase(iterator)/pop_back()
            map[key] = data.begin();
            return it->second;
        }else{
            return -1;
        }
    }
    
    void put(int key, int value) {
        /*1st time and size is about to exceed*/
        if(map.find(key) == map.end() && map.size() == size){
            int removekey = data.back().first;   // pair .first
            map.erase(removekey);
            data.pop_back();
        }
        /*if already exists*/
        if(map.find(key) != map.end()){
            auto it = map[key];
            data.erase(it);
        }
        data.push_front(make_pair(key, value));
        map[key] = data.begin();
    }
};
