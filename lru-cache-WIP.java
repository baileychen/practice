// WIP!
// mights as well rewrite oop

class LRUCache {
    /*
    Start 11:13pm
    got the write approach (#2: hashmap + doubly linked list around 11:45pm. Coding until 12:41 but buggy *cries*
    
    Use HashMap because of O(1) retrieval
    recency -> key -> value ? 
    Represent recency as integers. Bigger = more recent
    int minRecency; int MaxRecency // keep track
    
    each get and put resets recency
    problem with gaps/O(1) ordering of recency. Can't just reorder items or else O(capacity)
    RecencyMap:
    recency -> key
    ValueMap:
    key -> value
    
    min=1, nexMin=6, max=9
    recency: 1 7 6 8 9 put --> O(c - 1O) -> O(c)
    key:     1 2 3 4 5
    
    recency: 1 7 6 8 9
    key:     1 2 3 4 5
    
    Store pointers for each key
    key -> pointer
    
    keyPointersMap:
    1 -> header1
    2 -> header2
    
    Linked list instead of map to represent recency -> key?
    Linked List append to end is constant time
    
    1 -> 2 -> 3 -> 4 -> 5
    get(3)
    1 -> 2 -> 4 -> 5 -> 3 // move 3 (move pointers) // PROBLEM: don't know where 3 is!!! --> map key to pointer in custom linked list
    get(1)
    2 -> 4 -> 5 -> 3 -> 1
    put(6)
    4 -> 5 -> 3 -> 1 -> 6 // pop off head!
    
    */
    private Map<Integer, Node> keyToNode = new HashMap<Integer, Node>();
    private Node head;
    public Node tail;
    public LRUCache(int capacity) {
        head = new Node(-1);
        tail = head;
        Node pointer = head;
        for (int i = 0; i < capacity; i++) {
            Node next = new Node(-1);
            pointer.setNext(next);
            pointer = pointer.next;
        }
        head = head.next;
    }
    
    public int get(int key) {
        // get Node from key
        // if not exist: return -1
        // move Node to the tail
        // return val from key
        if (!keyToNode.containsKey(key)) {
            return -1;
        }
        
        Node node = keyToNode.get(key);
        node.moveToTail(tail);
        return node.value;
    }
    
    public void put(int key, int value) {
        /*
        Node node;
        if key already exists:
            set new value
        else if capacity not reached 
            create new Node
        else reached capacity
            create new Node
            remove head node, update head, remove key from key->node map
            
        move node to the end;
        add entry to key->node map, add Node to tail, update tail
        */
        if (keyToNode.containsKey(key)) {
            Node node = keyToNode.get(key);
            node.value = value;
            node.moveToTail(tail);
        } else if (tail.next != null) { // available capacity
            Node node = tail.next;
            node.key = key;
            node.value = value;
            tail = tail.next;
            keyToNode.put(key, node);
        } else {
            Node node = new Node(key, value);
            tail.next = node;
            node.prev = tail;
            
            tail = tail.next;
            // node.moveToTail(tail);
            keyToNode.put(key, node);
            
            keyToNode.remove(head.key);
            head = head.next;
        }
        
    }
    
    private class Node {
        public int key;
        public int value;
        public Node prev;
        public Node next;
        
        public Node(int num) {
            this.key = num;
            this.value = num;
        }
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
        
        public void setNext(Node next) {
            this.next = next;
            next.prev = this;
        }
        
        public void moveToTail(Node prevLast) {
            // detach from prev and next
            Node oldPrev = this.prev;
            Node oldNext = this.next;
            if (oldPrev != null) {
                oldPrev.next = oldNext;
            }
            if (oldNext != null) {
                oldNext.prev = oldPrev;
            }
            this.prev = prevLast;
            this.next = null;
            prevLast.next = this;
            tail = this;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */