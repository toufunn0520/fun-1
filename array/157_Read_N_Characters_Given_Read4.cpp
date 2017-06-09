// Forward declaration of the read4 API.
int read4(char *buf);

class Solution {
public:
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    int read(char *buf, int n) {
        int count = 0;
        // get the divider of 4
        for (int i = 0; i<n/4;i++) {
            count+=read4(&buf[count]);
        }
        // count will be smaller than both len and n.
        //remaining  which will endup with either taking from len-count or n-count which ever is smaller.
        int remain = n-count;
        if (remain) {
            int last = read4(&buf[count]);
            count += min(remain, last);
        }
        return count;
    }
};
