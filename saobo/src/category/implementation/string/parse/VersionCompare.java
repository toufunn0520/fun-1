package category.implementation.string.parse;

public class VersionCompare {

    /**
     * [Leetcode 165] https://leetcode.com/problems/compare-version-numbers/
     * 
     * <pre>
     * Compare two version numbers version1 and version2.
     * If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.
     * 
     * You may assume that the version strings are non-empty and contain only digits and the . character.
     * The . character does not represent a decimal point and is used to separate number sequences.
     * For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the
     * second first-level revision.
     * 
     * Here is an example of version numbers ordering:
     * 
     * 0.1 < 1.1 < 1.2 < 13.37
     * </pre>
     *
     * @param version1
     * @param version2
     * @return
     */
    public static int compareVersion(String version1, String version2) {
        String[] versions1 = version1.split("\\.");
        String[] versions2 = version2.split("\\.");

        int major1 = Integer.parseInt(versions1[0]);
        int major2 = Integer.parseInt(versions2[0]);

        if (major1 < major2) {
            return -1;
        } else if (major1 > major2) {
            return 1;
        } else {

            int minor1 = 0, minor2 = 0;
            if (versions1.length == 2) {
                minor1 = Integer.parseInt(versions1[1]);
            }
            if (version2.length() == 2) {
                minor2 = Integer.parseInt(versions2[1]);
            }
            if (minor1 < minor2) {
                return -1;
            } else if (minor1 > minor2) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public static void main(String[] args) {

        String a = "132.232";
        String[] test = a.split("\\.");

        System.out.println(compareVersion("232.123", "232.2"));
    }
}
