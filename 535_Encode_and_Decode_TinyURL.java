public class Codec {

    // Encodes a URL to a shortened URL.
    
    Map<String, String>map = new HashMap<>();
    Map<String, String>reversemap = new HashMap<>();
    String code="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    static String BASE_HOST = "http://tinyurl.com/";
    
    public String encode(String longUrl) {
        if(reversemap.containsKey(longUrl)) return BASE_HOST+reversemap.get(longUrl);
        String key = null;
        do{
            StringBuilder sb = new StringBuilder();
            for(int i =0; i<6; i++){
                int r = (int) (Math.random() * code.length());
                sb.append(code.charAt(r));
            }
            key = sb.toString();
        }while(map.containsKey(key));
        map.put(key, longUrl);
        reversemap.put(longUrl, key);
        return BASE_HOST+key;
        
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        String key = shortUrl.replace(BASE_HOST, "");
        return map.get(key);
    }
}
