/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 
 Start: 12:22am
 Start2: 7:15pm
 End2: 7:34pm
 
 LEARNED LESSON: handling 2 variables (1 ends earlier than the other): can handle if-empty conditions in main while loop!
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        /*
        [6 7 6] 
        [4 4]
        [0 2 1 1]
        carryOver = 1
        
        
        carryOver
        while both lists are not null:
            get sum = l1.val + l2.val + carryOver = 11
            get 1st digit (remainder) = sum % 10
            set carryOver to 2nd digit = sum >= 10 ? 1 : 0
            set newList.next
        
        // new ListNode?
        */
        ListNode head = new ListNode(0);
        ListNode pointer = head;
        int sum = 0;
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            pointer.next = new ListNode(sum % 10);
            pointer = pointer.next;
            sum = sum / 10;
        }
        if (sum > 0) { // has carryover
            pointer.next = new ListNode(sum);
        }
       
        return head.next;
        
    }
}