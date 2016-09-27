package category.search.backtracking;

/**
 * <pre>
 * Often, we want to encode raw IDs in our database by hiding them behind some 
 * 2-way decodeable hash. So, a URL which would have at one time been:
 * 
 * https://www.airbnb.com/rooms/848662.
 * 
 * https://www.airbnb.com/rooms/kljJJ324hjkS_
 * 
 * We decode the ID kljJJ324hjkS_ to 848662 on our backend and serve the 
 * relevant content. at some point, we start getting 404 errors from clients 
 * requesting a certain URL of the form
 * 
 * https://www.airbnb.com/rooms/kljjj324hjks_
 * 
 * This can happen if certain clients, email services, or url shorteners "
 * sanitize" the url. Unfortunately, this change breaks decoding and the 
 * resource cannot be found.
 * To assess how big of a deal this is, we may want to recover the IDs of the 
 * targets that were 404ing.
 * 
 * Your task:
 * Given a method decode(testEncStr) which will return the decoded int id if
 * testEncStr is decodeable or will throw an exception or return null (
 * depending on the language) if not, implement a new method decodeFind(String 
 * badEncStr) which takes a string and returns the decoded int id.
 * </pre>
 */
public class DecodeURL {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    /**
     * Fake method.
     *
     * @param s
     * @return
     */
    private String decode(String s) {

        return null;
    }

    public String decodeFind(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }

        return decodeFind(str, 0);
    }

    private String decodeFind(String str, int index) {

        if (index == str.length()) {
            return null;
        }

        String decoded = decode(str);
        if (decoded != null) {
            return decoded;
        }

        if (str.charAt(index) < 'Z' && str.charAt(index) > 'A') {
            char[] chars = str.toCharArray();
            chars[index] -= 'A' + 'a';
            decoded = decodeFind(new String(chars), index + 1);

            if (decoded != null) {
                return decoded;
            }
        } else if (str.charAt(index) < 'z' && str.charAt(index) > 'a') {
            char[] chars = str.toCharArray();
            chars[index] -= 'a' + 'A';
            decoded = decodeFind(new String(chars), index + 1);
            if (decoded != null) {
                return decoded;
            }
        }

        return decodeFind(str, index + 1);
    }
}
