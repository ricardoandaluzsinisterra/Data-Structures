package utils;

import java.util.LinkedList;

public class Stack{
    private final LinkedList<String> stack;

    public Stack(){
        stack = new LinkedList<String>();
    }

    public boolean empty(){
        return stack.isEmpty();
    }

    public void push(String value){
        stack.addFirst(value);
    }

    public String pop(){
        return stack.remove();
    }
}
