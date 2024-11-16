package utils;

public class GenericLinkedList<T> {

    protected int count;
    protected Node<T> head;
    protected Node<T> tail;

    public GenericLinkedList(){
        this.head = null;
        this.tail = null;
        count = 0;
    }

    public int size(){
        return count;
    }

    public T get(int pos){
        validatePosition(pos);
        Node<T> current = head;
        for (int i=0; i< pos; i++){
            //sort of counter loop where in each iteration it says the current to the next node on the list
            current = current.next;
        }

        return current.data;
    }

    public int indexOf(T element){
        Node<T> current = head;
        count = 0;
        while(current != null){
            if(current.data.equals(element)){
                return count;
            }
            else{
                //assign the next element to current. Normal iteration in linked lists.
                current = current.next;
                count += 1;
            }
        }
        //outside of the loop because it has to finish iterating and then returns.
        return -1;
    }

    public void add(T element){
        Node<T> newNode = new Node<T>(element);
        if (head == null){
            head = newNode;
            tail = newNode;
        }
        else{
            newNode.next = head;
            head = newNode;
            count ++;
        }
    }

    public void append(T element){
        Node<T> newNode = new Node<T>(element);
        if (head == null){
            head = newNode;
            tail = newNode;
        }
        else{
            Node<T> current = head;
            while (current.next != null){
                current = current.next;
            }
            current.next = newNode;
            tail = newNode;
            count++;
        }
    }

    public void insert(T element, int pos){
        validatePosition(pos);
        Node<T> newNode = new Node<T>(element);
        if (pos == 0){
            add(element);
        }
        else{
            Node<T> current = head;
            int counter = 0;
            //-1 because we need the node BEFORE our the pos
            while (current.next != null && counter != pos-1){
                current = current.next;
                counter++;
            }
            //since we need the node BEFORE our position, we want to insert our node between this one and it’s next
            //this means newNodes next node will become the currents next and the current next the new node, sequentially.
            newNode.next = current.next;
            current.next = newNode;
            count++;
            
        }
    }

    private void validatePosition(int pos){
        if(pos <= 0 || pos > count){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    protected static class Node<T>{
        T data;
        Node<T> next;

        public Node(T data){
            this.data = data;
            next = null;
        }
    }

}
