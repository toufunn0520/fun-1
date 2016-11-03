/*Double linkedlist + hashmap (key, Nodes)*/

public class LRUCache {
    private class Node {
        int value;
        int key;
        Node next;
        Node prev;
        public Node(int key, int value){
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }
    private int cap;
    private HashMap<Integer, Node> hs = new HashMap<Integer, Node>();
    private Node head = new Node(-1, -1);
    private Node tail = new Node(-1, -1);

    public LRUCache(int capacity) {
        this.cap = capacity;
        tail.prev =head;
        head.next = tail;
        
    }
    public int get(int key) {
        if(!hs.containsKey(key)){
            return -1;
        }
        // 1st remove it from the list
        Node cur = hs.get(key);
        cur.next.prev = cur.prev;
        cur.prev.next = cur.next;
        
        // 2nd move node to the tail 
        move_to_tail(cur);
        
        return cur.value;
    }
    
    public void set(int key, int value) {
        // if found
        if(get(key) != -1){
            hs.get(key).value= value;
       //     System.out.println(value);
            return ;
        }
        //not found but cach is full, need to kick the oldest one
        if(hs.size() == cap){
       //    System.out.println(head.next.key);
            hs.remove(head.next.key);
            head.next = head.next.next;
            head.next.prev = head;
        }
        
        //common insert into the end and hashmap;
        Node cur = new Node(key, value);
        hs.put(key, cur);
        move_to_tail(cur);
    }
    
    private void move_to_tail(Node cur){
        Node tmp = tail.prev;
        cur.prev = tmp;
        tmp.next = cur;
        tail.prev = cur;
        cur.next = tail;
    }
}
