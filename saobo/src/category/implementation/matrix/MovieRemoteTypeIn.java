package category.implementation.matrix;

import org.junit.Assert;
import org.junit.Test;

/**
 * <pre>
 * 像apple tv，chrome tv之类的，用户可以搜索电影名字，但只能用remote在一个虚拟的keyboard上搜索，只能在虚拟键盘上下左右移动。现在给定键盘如下：
 *  a  b  c  d  e
 *  f  g  h  i  j
 *  k  l  m  n  o
 *  p  q  r  s  t
 *  u  v  w  x  y
 *  z
 * 如果用户要搜索电影名字为 cars，那么需要先往右走两步到c，输入enter，再往左走两步，输入enter，再往下走3步往右走2步，输入enter，再往右走一步
 * ，输入enter。现在规定L，R，U，D分部代表左，右，上，下移动一步，！代表输入enter，那么用户动作可以表示成 RR!LL!DDDRR!R!
 * 要求写一个函数，输入为一个string代表电影名字，输出为一个string代表用户的动作。 Google phone screen.
 * </pre>
 *
 * @author boyi
 */
public class MovieRemoteTypeIn {

    public static final char DOWN_CHAR = 'D';

    public static final char END_CHAR = '!';

    public static final char LEFT_CHAR = 'L';

    public static final int NUM_OF_CHARS = 26;

    public static final char RIGHT_CHAR = 'R';

    public static final char START_CHAR = 'a';

    public static final char UP_CHAR = 'U';

    public static void main(String[] args) {
        System.out.println(new MovieRemoteTypeIn().getTypeInString("cars", 5));

    }

    @Test
    public void generateCorrectTypeInString() {
        MovieRemoteTypeIn remoteTypeIn = new MovieRemoteTypeIn();
        Assert.assertEquals("should generate correct string", "RR!LL!RRDDD!R!", remoteTypeIn.getTypeInString("cars", 5));
    }

    @Test
    public void generateCorrectTypeInStringWithBlankName() {
        MovieRemoteTypeIn remoteTypeIn = new MovieRemoteTypeIn();
        Assert.assertEquals("should generate blank path", "", remoteTypeIn.getTypeInString("", 4));
    }

    @Test
    public void generateCorrectTypeInStringWithDifferentWidth() {
        MovieRemoteTypeIn remoteTypeIn = new MovieRemoteTypeIn();
        Assert.assertEquals("should generate correct string", "RR!LL!RDDDD!R!", remoteTypeIn.getTypeInString("cars", 4));
    }

    @Test
    public void generateCorrectTypeWithNameA() {
        MovieRemoteTypeIn remoteTypeIn = new MovieRemoteTypeIn();
        Assert.assertEquals("should generate correct string", "!", remoteTypeIn.getTypeInString("a", 4));
    }

    @Test
    public void generateCorrectTypeWithNameEF() {
        MovieRemoteTypeIn remoteTypeIn = new MovieRemoteTypeIn();
        Assert.assertEquals("should generate correct string", "RRRR!LLLLD!", remoteTypeIn.getTypeInString("ef", 5));
    }

    @Test
    public void generateCorrectTypeWithNameZA() {
        MovieRemoteTypeIn remoteTypeIn = new MovieRemoteTypeIn();
        Assert.assertEquals("should generate correct string", "DDDDD!UUUUU!", remoteTypeIn.getTypeInString("za", 5));
    }

    /**
     * Generate the path for one char.
     *
     * @param xStartIndex
     * @param yStartIndex
     * @param xEndIndex
     * @param yEndIndex
     * @return The path string for one char.
     */
    private String generateOneCharPath(int xStartIndex, int yStartIndex, int xEndIndex, int yEndIndex) {
        StringBuilder pathBuilder = new StringBuilder();

        if (xEndIndex != xStartIndex) {
            while (xEndIndex > xStartIndex) {
                pathBuilder.append(RIGHT_CHAR);
                xStartIndex++;
            }

            while (xEndIndex < xStartIndex) {
                pathBuilder.append(LEFT_CHAR);
                xStartIndex--;
            }

        }

        if (yEndIndex != yStartIndex) {
            while (yEndIndex > yStartIndex) {
                pathBuilder.append(DOWN_CHAR);
                yStartIndex++;
            }

            while (yEndIndex < yStartIndex) {
                pathBuilder.append(UP_CHAR);
                yStartIndex--;
            }
        }

        pathBuilder.append(END_CHAR);

        return pathBuilder.toString();
    }

    /**
     * Generate the board. (Not used currently)
     *
     * @param width
     *            The width of the board.
     * @return a 2D board.
     */
    public char[][] generateTypeInBoard(int width) {
        int length = (int) Math.ceil(NUM_OF_CHARS * 1.0 / width);

        char[][] board = new char[length][width];

        for (char i = 0; i < NUM_OF_CHARS; i++) {
            char current = (char) (START_CHAR + i);
            board[i / width][i % width] = current;
        }

        return board;
    }

    public String getTypeInString(String name, int boardWidth) {
        String typeInString = "";

        if (name == null || name.isEmpty() || boardWidth <= 0) {
            return typeInString;
        }

        int xStartIndex = 0;
        int yStartIndex = 0;

        for (int i = 0; i < name.length(); i++) {
            char current = name.charAt(i);
            int xEndIndex = (current - START_CHAR) % boardWidth;
            int yEndIndex = (current - START_CHAR) / boardWidth;

            String currentPath = generateOneCharPath(xStartIndex, yStartIndex, xEndIndex, yEndIndex);
            typeInString += currentPath;
            xStartIndex = xEndIndex;
            yStartIndex = yEndIndex;
        }

        return typeInString;
    }

}
