class LRUCache {
    /*
    Start 11:13pm
    got the write approach (#2: hashmap + doubly linked list around 11:45pm. Coding until 12:41 but buggy *cries*
    Start2 11:46pm End2 12:47pm ONE HOUR of figuring out edge cases!!
    
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
    int capacity;
    DoublyLinkedList lst = new DoublyLinkedList();
    Map<Integer, Node> keyToNode = new HashMap<Integer, Node>(); 
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if (!keyToNode.containsKey(key)) {
            return -1;
        }
        // has key
        // remove node from list
        // insert at tail
        Node node = keyToNode.get(key);
        lst.remove(node);
        lst.insertAtTail(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if (keyToNode.containsKey(key)) {
            // update value
            Node node = keyToNode.get(key);
            node.value = value;
            // move node to the end
            lst.remove(node);
            lst.insertAtTail(node);
        } else if (keyToNode.size() < capacity) {
            Node node = new Node(key, value);
            lst.insertAtTail(node);
        } else { // at capacity
            Node node = new Node(key, value);
            lst.insertAtTail(node);
            lst.remove(lst.head);
        }
        
    }
    
    class DoublyLinkedList {
        Node head;
        Node tail;
        
        void insertAtTail(Node node) {
            // what if node = tail?
            // what if node = head?
            // what if node = head = tail?
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = tail.next;
            }
            keyToNode.put(node.key, node);
        }
        void remove(Node node) {
            if (node.next != null) {
                node.next.prev = node.prev;
            }
            if (node.prev != null) {
                node.prev.next = node.next;
            }
            
            if (node == head) {
                head = head.next;
            }
            if (node == tail) {
                tail = tail.prev;   
            }
            
            node.next=null;
            node.prev=null;
            keyToNode.remove(node.key);
        }
    }
    
    class Node {
        int key;
        int value;
        Node prev;
        Node next;
        
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */