package utils;

import java.util.NoSuchElementException;

public class LinkedList {
    protected int count;
    protected Node head;
    protected Node tail;

    public LinkedList(){
        this.head = null;
        this.tail = null;
        count = 0;
    }

    public int size(){
        return count;
    }

    public int get(int pos){
        validatePosition(pos);

        Node current = head;
        for (int i = 0; i < pos; i++) {
            current = current.next;
        }

        return current.data;
    }

    public int indexOf(int value){
        Node current = head;
        for (int i = 0; i < count; i++) {
            if(value == current.data){
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    public void add(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            tail = newNode;
        }else{
            tail.next = newNode;
            tail = newNode;
        }
        count++;
    }

    public void insert(int value, int pos){
        if(pos < 0 || pos > count){
            throw new IndexOutOfBoundsException("Illegal position supplied - position must be within bounds of list");
        }

        if(pos == count){
            add(value);
        }else if(pos == 0){
            addToStart(value);
        }else {
            Node newNode = new Node(value);

            Node current = head;
            Node prev = null;
            for (int i = 0; i < pos; i++) {
                prev = current;
                current = current.next;
            }
            newNode.next = current;
            prev.next = newNode;

            count++;
        }
    }

    public void addToStart(int value){
        Node newNode = new Node(value);
        if(head == null){
            head = newNode;
            tail = newNode;
        }else{
            newNode.next = head;
            head = newNode;
        }
        count++;
    }

    public int remove(int pos){
        validatePosition(pos);

        if(pos == 0){
            return removeFirst();
        }else if(pos == count-1){
            return removeLast();
        }else{
            Node current = head;
            Node prev = null;

            for (int i = 0; i < pos; i++) {
                prev = current;
                current = current.next;
            }
            int deleted = current.data;
            prev.next = current.next;
            count--;
            return deleted;
        }
    }

    private void validatePosition(int pos) {
        if(pos < 0 || pos >= count){
            throw new IndexOutOfBoundsException("Position must be within the boundaries of the list");
        }
    }

    public int removeFirst(){
        if(head == null){
            throw new NoSuchElementException("Cannot delete from an empty list");
        }

        int deleted = head.data;

        head = head.next;
        if(head == null){
            tail = null;
        }
        count--;

        return deleted;
    }

    public int removeLast(){
        if(head == null){
            throw new NoSuchElementException("Cannot delete from an empty list");
        }

        int deleted = 0;
        if(head == tail) {
            deleted = head.data;
            head = null;
            tail = null;
        }else {
            Node current = head;
            while(current.next != tail) {
                current = current.next;
            }
            current.next = null;
            deleted = tail.data;
            tail = current;
        }
        count--;

        return deleted;
    }

    protected static class Node{
        int data;
        Node next;

        public Node(int data){
            this.data = data;
            next = null;
        }
    }
}
