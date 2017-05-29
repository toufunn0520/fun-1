/*
hashmap+ memeroization
 Keep <start, the ith word as starter, cnt, num of following word could be added into the same row>
 keep cal total words from sentence could be added into the grid, final result = total/n
 each row, get the 1st words using total%n, keep try to squezee the new couples of word into the same row. curcount. 
 update <start, curcount>
 */

class Solution {
public:
    int wordsTyping(vector<string>& sentence, int rows, int cols) {
        unordered_map<int, int> map; // <startfrom i, num of words> as a memeory
        int n = sentence.size();
        int totalcount = 0;
        // as the #words
        for (int i = 0; i<rows; i++){
            int start = totalcount % n;
            if (map.count(start) == 0){
                int curcount = 0;
                int curstart = start;
                for (int len = 0; len < cols; curcount++){
                    if ( (len + sentence[(curstart)%n].size() ) > cols){  // careful. 
                       break;
                    }
                    len += sentence[(curstart)%n].size()+1;
                    curstart ++;
                }
                // finish calculate the #of words
                totalcount += curcount;
                map[start] = curcount;
            }else{
                totalcount += map[start];
            }
        }
        return (totalcount/n);
    }
};
