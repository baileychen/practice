/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 1/5/2021
 Start: 10:20pm
 End: 10:46pm (22 minutes)
 */
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        /*
        if list1 is empty: return list2
        // arbitrarily choose list1 as head
        
        if list1.val <= list2.value: 
            head = list1;
            list1 advances
        else: 
            head = list2
            list2 advances
        ptr = head;
        
        while l1 != null && l2 != null:
            if list1 value <= list2 value:
                ptr.next = l1
                l1 = l1.next
            else:
                ptr.next = l2
                l2 = l2.next
            ptr = ptr.next
        
        if l1 == null && l2 == null: return head;
        if l1 == null:
            ptr.next = l2;
        else:
            ptr.next = l1;
        */
        
        ListNode preHead = new ListNode(-1);
        ListNode ptr = preHead;
        
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                ptr.next = list1;
                list1 = list1.next;
            } else {
                ptr.next = list2;
                list2 = list2.next;
            }
            ptr = ptr.next;
        }
        
        ptr.next = list1 == null ? list2 : list1;
        return preHead.next;
    }
}