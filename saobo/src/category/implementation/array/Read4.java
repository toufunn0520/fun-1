package category.implementation.array;

import java.util.ArrayList;
import java.util.List;

public class Read4 {

    public static void main(String[] args) {
        char[] fileContent = new String("abcdefghi").toCharArray();
        char[] buf = new char[8];

        Read4 r = new Read4(fileContent);
        r.readMultipleTimes(buf, 2);
        for (int i = 0; i < buf.length; i++) {
            System.out.println(buf[i]);
        }
        System.out.println("~~~~~~");
        r.readMultipleTimes(buf, 3);

        for (int i = 0; i < buf.length; i++) {
            System.out.println(buf[i]);
        }
    }

    private char[] fileContent;

    private int index;

    private List<Character> leftFromLastRead;

    public Read4(char[] fileContent) {
        this.fileContent = fileContent;
        this.index = 0;
    }

    /**
     * [Leetcode 157] https://leetcode.com/problems/read-n-characters-given-read4/
     *
     * <pre>
     * The API: int read4(char *buf) reads 4 characters at a time from a file. The return value is the actual number of
     * characters read. For example, it returns 3 if there is only 3 characters left in the file. By using the read4
     * API, implement the function int read(char *buf, int n) that reads n characters from the file.
     * </pre>
     *
     * @param buf
     * @param n
     * @return
     */
    public int read(char[] buf, int n) {
        if (n <= 0) {
            return 0;
        }

        int count = 0;
        int currentCount = 4;
        char[] currrentBuf = new char[4];
        while (count < n && currentCount == 4) {
            currentCount = read4(currrentBuf);
            int length = Math.min(currentCount, n - count);

            for (int i = 0; i < length; i++) {
                buf[count + i] = currrentBuf[i];
            }
            count += length;
        }

        return count;
    }

    private int read4(char[] buf) {
        int i = 0;
        for (; i < 4 && index + i < fileContent.length; i++) {
            buf[i] = fileContent[index + i];
        }

        index += i;
        return i;
    }

    /**
     * [Leetcode 158] https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/
     * 
     * <pre>
     * The API: int read4(char *buf) reads 4 characters at a time from a file. The return value is the actual number of
     * characters read. For example, it returns 3 if there is only 3 characters left in the file. By using the read4
     * API, implement the function int read(char *buf, int n) that reads n characters from the file. Note: The read
     * function may be called multiple times.
     * </pre>
     * 
     * @param buf
     * @param n
     * @return
     */
    public int readMultipleTimes(char[] buf, int n) {
        if (n <= 0) {
            return 0;
        }

        if (leftFromLastRead == null) {
            leftFromLastRead = new ArrayList<Character>();
        }

        int count = 0;
        int currentCount = 4;
        char[] currrentBuf = new char[4];

        while (count < n && !leftFromLastRead.isEmpty()) {
            buf[count++] = leftFromLastRead.get(0);
            leftFromLastRead.remove(0);
        }

        while (count < n && currentCount == 4) {
            currentCount = read4(currrentBuf);
            int length = Math.min(currentCount, n - count);

            for (int i = 0; i < currentCount; i++) {
                if (count + i < n) {
                    buf[count + i] = currrentBuf[i];
                } else {
                    leftFromLastRead.add(currrentBuf[i]);
                }
            }
            count += length;
        }

        return count;
    }
}
