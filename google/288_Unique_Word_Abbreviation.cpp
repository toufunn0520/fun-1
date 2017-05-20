class ValidWordAbbr {
public:
    unordered_map<string, unordered_set<string>> map;
    ValidWordAbbr(vector<string> dictionary) {
        map.clear();
        auto it = dictionary.begin();
        for(; it<dictionary.end(); it++) {
            if((*it).length()<=2){
                map[*it].insert(*it);
            }else{
                string tmp = (*it)[0]+to_string((*it).length()-2) +(*it)[(*it).length()-1];
                //printf("About to insert %s, %s\n", tmp.c_str(), (*it).c_str());
                map[tmp].insert(*it);
            }
        }
    }
    
    bool isUnique(string word) {
        int n = word.length();
		string abbr = word[0] + to_string(n-2) + word[n - 1];
		//printf ("%s count =%d size=%d\n", abbr.c_str(), map[abbr].count(word), map[abbr].size());
		return map[abbr].count(word) == map[abbr].size(); 
    }
};
