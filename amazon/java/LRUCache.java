/*
* O(1) get/put double linked list + HashMap
*/
class LRUCache {

    int capacity;
    HashMap<Integer, Node> map;
    //Head of the Node is the last acccess
    Node head = null;
    Node tail = null;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
    }
    
   public int get(int key) {
        if(map.containsKey(key)) {
            // update node to top of the list
            Node node = map.get(key);
            remove(node);
            updateHead(node);
            return node.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
    
        if (get(key) == -1) {
            // not found
            if (map.size() == capacity) {
                int keyToRemove = tail.key;
                Node toRemove = map.get(keyToRemove);
                remove(toRemove);
                map.remove(keyToRemove);
            }
            //insert key to head
            Node node = new Node(key, value);
            updateHead(node);
            map.put(key, node);
        } else {
            // update value
            map.get(key).value = value;
        }
       
    }
    
    //helper function
    private void remove(Node node) {
        // remove the node and update previous and next pointers + head and tail
        if (node.previous != null) {
            node.previous.next = node.next;
        } else {
            head = node.next;
        }
        
        if (node.next != null) {
            node.next.previous = node.previous;
        } else {
            tail = node.previous;
        }
    }
    
    // if accessed, move it to head
    private void updateHead(Node node) {
        node.previous = null;
        node.next = head;
        if (head != null) {
            head.previous = node;   
        }
        head = node;

        if (tail == null) {
            tail = node;
        }
    }
}

class Node {
    public int key;
    public int value;
    public Node previous;
    public Node next;
    
    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        previous = next = null;
    }
    
}
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
