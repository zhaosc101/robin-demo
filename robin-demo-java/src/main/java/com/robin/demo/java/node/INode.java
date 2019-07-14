/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/4/24 16:31
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.demo.java.node;

import java.util.HashMap;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/4/24 16:31
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/4/24 16:31
 */
public class INode {

    public static void main(String[] args) {

        Node next = new Node("0");
        Node node1 = new Node("1");
        next.setNext(node1);
        Node node2 = new Node("2");
        node1.setNext(node2);
        Node node3 = new Node("3");
        node2.setNext(node3);
        Node node4 = new Node("4");
        node3.setNext(node4);

        //        for (int i = 1; i <10 ; i++) {
//
//            Node node = new Node(i);
//            next.setNext(node);
//            next = node;
//        }
        print(next);
        Node newNode = change(next);
        System.out.println("``````````````````");
        print(newNode);

    }

    public static void print(Node node) {

        while (node.next != null) {
            System.out.println(node.getData());
            node = node.next;
        }
        System.out.println(node.getData());
    }

    /**
     * 翻转链表
     * @param node
     * @return
     */
    public static Node change(Node node) {

        if(node==null||node.next==null) {
            return node;
        }
        Node pre = null;
        Node cur = node;
        Node next = null;
        while (null != cur) {
            next = cur.getNext();//nextNode 指向下一个节点
            cur.setNext(pre);//将当前节点next域指向前一个节点
            pre = cur;//preNode 指针向后移动
            cur = next;//curNode指针向后移动
        }
        return pre;//返回pre
    }


    public static class Node {

        private Object data;
        private Node next;
        private Node(Object data){
            this.data=data;
        }
        private Node(Object data,Node next){
            this.data=data;
            this.next=next;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
