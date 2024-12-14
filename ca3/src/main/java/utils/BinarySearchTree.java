package utils;

import model.Book;
import java.util.NoSuchElementException;


public class BinarySearchTree {
    private Node root;
    private int size;

    public BinarySearchTree(){
        root = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void add(Book newBook){
        validateBook(newBook);

        root = add(root, newBook);
        size++;
    }

    private Node add(Node root, Book newBook){
        if (root == null){
            return new Node(newBook);
        }
        if(root.data.compareTo(newBook) >= 0){
            root.left = add(root.left, newBook);
        } else{
            root.right = add(root.right, newBook);
        }
        return root;
    }
    
    public Book remove(int id){
        if (root == null){
            return null;
        }
        Book deletedBook = get(id);
        DeletedNode deleted = remove(root, deletedBook);
        if(deleted.element != null){
            size --;
        }
        return deleted.element;
    }

    private DeletedNode remove(Node root, Book book){
        if(root == null){
            return new DeletedNode(null, null);
        }
        if(book.compareTo(root.data) < 0){
            DeletedNode deletedLeft = remove(root.left, book);
            root.left = deletedLeft.node;
        }
        else if(book.compareTo(root.data) > 0){
            DeletedNode deletedRight = remove(root.right, book);
            root.right = deletedRight.node;
            return new DeletedNode(root, book);
        }

        Book deletedBook = root.data;
        if(root.left == null && root.right == null){
            return new DeletedNode(null, deletedBook);
        }
        
        if(root.left == null){
            return new DeletedNode(root.right, deletedBook);
        }

        if(root.right == null){
            return new DeletedNode(root.left, deletedBook);
        }

        Book successorBook = getMin(root.right);
        root.data = successorBook;
        DeletedNode deletedRight = remove(root.right, successorBook);
        root.right = deletedRight.node;
        return new DeletedNode(root, deletedBook);
    }

    public Book get(int id){
        Node book = get(root, id);
        if (book == null){
            throw new NoSuchElementException("No elements were found with the Id provided");
        }
        return book.data;
    }

    private Node get(Node root, int id){
        if(root == null){
            return null;
        }
        if(root.data.getId() == id){
            return root;
        }
        else if(root.data.getId() > id){
            return get(root.left, id);
        }
        else{
            return get(root.right, id);
        }
    }
    public Book getMin(){
        if(root == null){
            return null;
        }
        return getMin(root);
    }

    private Book getMin(Node root){
        if (root.left == null){
            return root.data;
        }
        return getMin(root.left);
    }

    public Book getMax(){
        if(root == null){
            return null;
        }
        return getMax(root);
    }

    private Book getMax(Node root){
        if (root.right == null){
            return root.data;
        }
        return getMax(root.right);
    }

    protected static class Node{
        Book data;
        Node left;
        Node right;

        public Node(Book data){
            this.data = data;
            left = null;
            right = null;
        }
    }

    private static class DeletedNode{
        Book element;
        Node node;

        public DeletedNode(Node node, Book deletedValue){
            this.node = node;
            this.element = deletedValue;
        }
    }
}
