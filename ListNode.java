package List;

class Node{
    public int val;
    public Node next = null;
    public Node(int val){
        this.val = val;
    }
}

public class ListNode {
    //删除某个结点。创建新链表，将不符的数字放置其中
    public static Node removeElements(Node head,int val){
        Node newList = null;
        Node last = null;
        Node cur = head;
        while (cur != null){
            //若不不等于，则尾插至新链表
            if (cur.val != val){
                if (newList == null){
                    //新链表为空，头插与尾插一致
                    cur.next = newList;
                    newList = cur;
                }else{
                    //新链表不为空，每次需要尾插
                    last.next = cur;
                }
                //更新last，保证last始终为最后一个结点
                last = cur;
            }
        }
        if (last != null){
            last.next = null;
        }
        return newList;
    }

    //反转链表。
    public static Node reverseList(Node head){
        Node newList = null;
        Node cur = head;
        while(cur != null){
            //因为 cur.next 会变化，所以前提保存其值
            Node next =cur.next;
            //头插
            cur.next=newList;
            newList=cur;
            //让 cur 往后遍历
            cur=next;
        }
        //翻过去的头结点
        return newList;
    }

    //求链表长度
    //求倒数第k个
    public Node FindKthToTail1(Node head,int k) {
        //长度-k
        Node cur = head;
        int count = 0;
        while (cur != null) {
            cur = cur.next;
            count++;
        }
        if (count == k) {
            return head;
        }
        if (count < k) {
            return null;
        }
        int n = count - k;
        Node kt = head;
        for (int i = 0; i < n; i++) {
            kt = kt.next;
        }
        return kt;
    }

    public Node FindKthToTail2(Node head,int k){
        Node front = head;
        Node back = head;

        for(int i = 0;i<k;i++){  //使front和back相差k个数
            if(front == null){
                return null;
            }
            front = front.next;
        }

        while(front != null){     //两个同时为往后遍历
            front = front.next;
            back = back.next;
        }
        return back;
    }

    //求中间结点
    public Node middleNode(Node head){
        //周期：红蓝红，求中间的点 红的是蓝的2倍
        //所以蓝色的为中间值
        Node fast = head;
        Node slow = head;
        while(fast != null){
            fast=fast.next;
            if(fast == null){
                break;
            }
            slow=slow.next;
            fast=fast.next;
        }
        return slow;
    }

    //两个链表和一起
    public Node mergeTwo(Node l1,Node l2){
        if(l1==null){return l2;}
        if(l2==null){return l1;}
        Node cur1=l1;   //可以理解为给链表(头结点)重新命名
        Node cur2=l2;   //原来的链表为l1，l2；
        Node L3=null;
        Node last=null;
        while (cur1 != null && cur2 != null){
            if(cur1.val <= cur2.val){
                Node next = cur1.next;
                if(L3 == null){
                    cur1.next = L3;  //将链表1中的结点放入新链表3
                    L3 = cur1;  //此时链表为空，尾插相当于头插；更新新链表结点
                    //访问对象的属性
                }
                else{   //真正的尾插
                    cur1.next = null;   //放入当前结点
                    last.next = cur1;  //更新最后一个结点
                }
                //保证last永远指向最后一个结点
                last = cur1;
                cur1 = next;
            }
            else{
                Node next = cur2.next;
                if(L3 == null){
                    cur2.next=L3;  //此时尾插相当于头插
                    L3=cur2;
                    //访问对象的属性
                }
                else{
                    cur2.next = null;
                    last.next = cur2;  //插入当前结点
                }
                //保证last永远指向最后一个结点
                last = cur2;
                cur2 = next;    //让循环走起来
            }
        }
        //cur  current当前
        //链表长度不同
        if(cur1 == null){
            last.next = cur2;
        }
        else if(cur2 == null) {
            last.next = cur1;
        }
        return L3;
    }
}
