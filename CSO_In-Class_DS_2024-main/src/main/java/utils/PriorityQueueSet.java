package utils;

import exceptions.DuplicateElementException;

public class PriorityQueueSet extends LinkedList {
    public PriorityQueueSet(){
        super();
    }

    public void add(int value){
        if(indexOf(value) != -1){
            throw new DuplicateElementException("Queue does allow duplicates");
        }

        int pos = calcInsertionPoint(value);
        super.insert(value, pos);
    }

    private int calcInsertionPoint(int value){
        Node current = super.head;
        int i = 0;
        while(current != null){
            if(current.data < value){
                return i;
            }
            current = current.next;
            i++;
        }
        return i;
    }

    public void insert(int value, int pos){
        throw new UnsupportedOperationException("Operation not allowed for queues");
    }
}
