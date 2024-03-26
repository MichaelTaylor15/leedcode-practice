package Y24;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
    ListNode(int[] arr){
        ListNode cur=this;
        for (int i=0;i<arr.length-1;i++){
            cur.val=arr[i];
            cur.next=new ListNode();
            cur=cur.next;
        }
        cur.val=arr[arr.length-1];
    }
    ListNode() {
    }
}
class Solution {

    //160. 相交链表
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode ha=headA;
        ListNode hb=headB;
        HashMap<ListNode,Integer> hashMap=new HashMap<>();
        while (ha!=null){
            hashMap.put(ha,1);
            ha=ha.next;
        }
        while (hb!=null){
            if (hashMap.containsKey(hb)){
                return hb;
            }
            hb=hb.next;
        }
        return null;
    }
    //206. 反转链表[1->2->3->4->5] []
    public static ListNode reverseList(ListNode head) {
        if (head==null) return null;
        ListNode pre=head;
        ListNode cur;
        ListNode temp;
        cur=head.next;
        head.next=null;
        if (cur==null) return head;
        while (cur.next!=null){
            temp=cur.next;
            cur.next=pre;
            pre=cur;
            cur=temp;
        }
        cur.next=pre;
        return cur;
    }
    //234. 回文链表
    public static boolean isPalindrome(ListNode head) {
        boolean rs=true;
        ListNode node=head;
        ArrayList<Integer> arrayList=new ArrayList<>();
        while (node!=null){
            arrayList.add(node.val);
            node=node.next;
        }
        Integer[] arr=arrayList.toArray(new Integer[0]);
        int left=0;
        int right=arr.length-1;
        while (left<right){
            if (arr[left]!=arr[right]){
                return false;
            }
            left++;
            right--;
        }
        return rs;
    }
    //141. 环形链表
    public boolean hasCycle(ListNode head) {
        HashMap<ListNode,Integer> hashMap=new HashMap<>();
        while (head!=null){
            if (hashMap.containsKey(head)){
                return true;
            }
            hashMap.put(head,1);
            head=head.next;
        }
        return false;
    }
    //142. 环形链表 II
    public ListNode detectCycle(ListNode head) {
        HashMap<ListNode,Integer> hashMap=new HashMap<>();
        int c=0;
        int ans=-1;
        while (head!=null){
            if (hashMap.containsKey(head)){
                ans=hashMap.get(head);
                return head;
            }
            hashMap.put(head,c);
            head=head.next;
            c++;
        }
        return null;
    }
    //21. 合并两个有序链表
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1==null && list2==null) return null;
        if (list1==null) return list2;
        if (list2==null) return list1;
        ListNode rs = new ListNode();
        ListNode head=rs;
        while (list1!=null && list2!=null){
            if (list1.val<list2.val){
                head.val=list1.val;
                list1=list1.next;
            } else if (list1.val==list2.val) {
                head.val=list1.val;
                head.next=new ListNode();
                head=head.next;
                head.val=list2.val;
                list2=list2.next;
                list1=list1.next;
            }else {
                head.val=list2.val;
                list2=list2.next;
            }
            if (list1!=null && list2!=null){
                head.next=new ListNode();
                head=head.next;
            }
        }
        while (list1!=null){
            head.next=new ListNode();
            head=head.next;
            head.val=list1.val;
            list1=list1.next;
        }
        while (list2!=null){
            head.next=new ListNode();
            head=head.next;
            head.val=list2.val;
            list2=list2.next;
        }
        return rs;
    }
    //2. 两数相加
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2){
        ListNode t1=l1;
        ListNode t2=l2;
        ListNode rs=new ListNode();
        ListNode head=rs;
        int pre=0;
        while (t1!=null && t2!=null){
            int sum=t1.val+t2.val+pre;
            if (sum<10){
                head.val=sum;
                pre=0;
            }else if (sum==10){
                head.val=0;
                pre=1;
            }else{
                pre=1;
                head.val=sum-10;
            }
            t2=t2.next;
            t1=t1.next;
            if (t1!=null && t2!=null){
                head.next=new ListNode();
                head=head.next;
            }
        }
        ListNode last=t1==null? t2 :t1;
        while (last!=null){
            head.next=new ListNode();
            head=head.next;
            int sum=last.val+pre;
            if (sum==10){
                sum=0;
                pre=1;
            }else if (sum>10){
                sum=sum-10;
                pre=1;
            }else{
                pre=0;
            }
            head.val=sum;
            last=last.next;
        }
        if (pre!=0){
            head.next=new ListNode();
            head.next.val=pre;
        }
        return rs;
    }
    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        StringBuilder s1=new StringBuilder();
        StringBuilder s2=new StringBuilder();
        while (l1!=null){
            s1.append(l1.val);
            l1=l1.next;
        }
        while (l2!=null){
            s2.append(l2.val);
            l2=l2.next;
        }
        s1.reverse();
        s2.reverse();
        long res=Long.parseLong(s1.toString())+Long.parseLong(s2.toString());
        StringBuilder str= new StringBuilder(Long.toString(res));
        str.reverse();
        ListNode top=new ListNode();
        ListNode head=top;
        for (int i=0;i<str.length();i++){
            char c=str.charAt(i);
            head.val=Integer.parseInt(String.valueOf(c));
            if (i<str.length()-1){
                head.next = new ListNode();
            }
            head=head.next;
        }
        return top;
    }
    //19. 删除链表的倒数第 N 个结点
    //预先统计链表中的节点数，再次遍历删除节点
    public static ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode node=head;
        int c=0;
        while (node!=null){
            c++;
            node=node.next;
        }
        if (c<=1){
            return null;
        }
        int idx=c-n;
        int t=0;
        ListNode temp=head;
        ListNode pre = null;
        while (temp!=null){
            if (t==idx){
                break;
            }
            pre=temp;
            temp=temp.next;
            t++;
        }
        if (temp!=null && pre!=null){
            if (temp.next==null){
                pre.next=null;
            }else{
                pre.next=temp.next;
            }
        }else if (pre==null){
            return temp.next;
        }
        return head;
    }
    //                      p  c           c  p  t        p  c  t
    //24. 两两交换链表中的节点  0->1->2->3->4  2->1->3->4  2->1->3->4 2->1->4->3->5->6
    //                      p
    //                      0->2->3->4
    //                      2->1->3->4
    public static ListNode swapPairs(ListNode head) {
        ListNode cur=head;
        ListNode dummy=new ListNode(-1);
        dummy.next=head;
        ListNode pre=dummy;
        while (cur!=null){
            pre.next=cur.next;
            cur.next=cur;

           break;
        }
        return dummy.next;
    }

    //148. 排序链表
    public static ListNode sortList(ListNode head) {
        ListNode node=head;
        ListNode dummy=new ListNode(-1);
        ListNode cur=dummy;
        dummy.next=new ListNode();
        while (node!=null){
            ListNode temp=node.next;
            ListNode change = node;
            while (temp!=null){
                if (node.val>temp.val){
                    change=temp;
                }
                temp=temp.next;
            }
            cur.next=change;
            node=node.next;
        }
        return dummy.next;
    }
    //146. LRU 缓存

    public static void print(ListNode head){
        while (head.next!=null){
            System.out.print(head.val+" -> ");
            head=head.next;
        }
        System.out.print(head.val);
        System.out.println();
    }
    public static void main(String[] args) {
        ListNode head=new ListNode(new int[]{1,4,5,3});
        ListNode list1=new ListNode(new int[]{1,2,3,4});
        ListNode list2=new ListNode(new int[]{9,9,9,9});
        //8,9,9,9,0,0,0,1
        //ListNode rs = swapPairs(head);
       // print(rs);
        sortList(head);
        print(head);
    }
}
//double direct link
class DLinkNode{
    DLinkNode pre;
    DLinkNode next;
    int key;
    int val;
    public DLinkNode(){}
}
//LRU缓存，get、put操作时间复杂度为O(1),容量过载时需要删除最近最少使用的node
class LRUCache {
    HashMap<Integer,DLinkNode> hashMap;
    DLinkNode head;
    DLinkNode tail;
    int capacity;
    int size;
    public LRUCache(int capacity) {
        this.capacity=capacity;
        size=0;
        head=new DLinkNode();
        tail=new DLinkNode();
        head.next=tail;
        tail.pre=head;
        hashMap=new HashMap<>();
    }
    public int get(int key) {
        if (hashMap.containsKey(key)){
            DLinkNode node=hashMap.get(key);
            //删除当前节点
            node.pre.next=node.next;
            node.next.pre=node.pre;
            //移动头节点
            moveToHead(node);
            return node.val;
        }
        return -1;
    }
    public void put(int key, int value) {
        if (hashMap.containsKey(key)){
            DLinkNode node=hashMap.get(key);
            //删除当前节点
            node.pre.next=node.next;
            node.next.pre=node.pre;
            //存入
            DLinkNode temp=new DLinkNode();
            temp.key=key;
            temp.val=value;
            hashMap.put(key,temp);
            moveToHead(temp);
            return;
        }
        if (size<capacity){
            DLinkNode node=new DLinkNode();
            node.val=value;
            node.key=key;
            //存进头节点
            moveToHead(node);
            hashMap.put(key,node);
            size++;
        }else{
            //容量过载，删除末尾元素
            hashMap.remove(tail.pre.key);
            tail.pre.pre.next=tail;
            tail.pre=tail.pre.pre;
            //存进头节点
            DLinkNode node=new DLinkNode();
            node.val=value;
            node.key=key;
            moveToHead(node);
            hashMap.put(key,node);
        }
    }
    //移动到头节点
    public void moveToHead(DLinkNode node){
        node.next=head.next;
        head.next.pre=node;
        head.next=node;
        node.pre=head;
    }
}

public class Link {
    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.get(2);
        lRUCache.put(2, 1);
        lRUCache.put(1, 1);
        lRUCache.put(2, 3);
        lRUCache.put(4, 1);
        lRUCache.get(1);
        lRUCache.get(2);
    }
}
