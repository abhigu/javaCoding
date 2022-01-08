package LeetCode;

public class NodeList {
    int val;
    NodeList next;
    NodeList() {}
    NodeList(int val) { this.val = val; }
    NodeList(int val, NodeList next) { this.val = val; this.next = next; }
}