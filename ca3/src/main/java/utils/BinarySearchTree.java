package utils;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

import model.Book;


public class BinarySearchTree {
    private Node root;
    private int size;

    /**
     * Constructs an empty BinarySearchTree.
     */
    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    /**
     * Returns the number of elements in the tree.
     *
     * @return The number of elements in the tree.
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the tree is empty.
     *
     * @return True if the tree is empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Adds a new book to the tree.
     *
     * @param newBook The book to be added.
     * @throws IllegalArgumentException If the book is null.
     */
    public void add(Book newBook) {
        validateBook(newBook);
        root = add(root, newBook);
        size++;
    }

    private Node add(Node root, Book newBook) {
        if (root == null) {
            return new Node(newBook);
        }
        if (root.data.compareTo(newBook) >= 0) {
            root.left = add(root.left, newBook);
        } else {
            root.right = add(root.right, newBook);
        }
        return root;
    }

    /**
     * Removes a book by its ID.
     *
     * @param id The ID of the book to be removed.
     * @return The removed book, or null if no book with the specified ID is found.
     */
    public Book remove(int id) {
        if (root == null) {
            return null;
        }
        Book deletedBook = get(id);
        DeletedNode deleted = remove(root, deletedBook);
        if (deleted.element != null) {
            size--;
        }
        return deleted.element;
    }

    private DeletedNode remove(Node root, Book book) {
        if (root == null) {
            return new DeletedNode(null, null);
        }
        if (book.compareTo(root.data) < 0) {
            DeletedNode deletedLeft = remove(root.left, book);
            root.left = deletedLeft.node;
        } else if (book.compareTo(root.data) > 0) {
            DeletedNode deletedRight = remove(root.right, book);
            root.right = deletedRight.node;
            return new DeletedNode(root, book);
        }

        Book deletedBook = root.data;
        if (root.left == null && root.right == null) {
            return new DeletedNode(null, deletedBook);
        }

        if (root.left == null) {
            return new DeletedNode(root.right, deletedBook);
        }

        if (root.right == null) {
            return new DeletedNode(root.left, deletedBook);
        }

        Book successorBook = getMin(root.right);
        root.data = successorBook;
        DeletedNode deletedRight = remove(root.right, successorBook);
        root.right = deletedRight.node;
        return new DeletedNode(root, deletedBook);
    }

    /**
     * Retrieves a book by its ID.
     *
     * @param id The ID of the book to be retrieved.
     * @return The book with the specified ID.
     * @throws NoSuchElementException If no book is found with the specified ID.
     */
    public Book get(int id) {
        Node book = get(root, id);
        if (book == null) {
            throw new NoSuchElementException("No books were found with the Id provided");
        }
        return book.data;
    }

    private Node get(Node root, int id) {
        if (root == null) {
            return null;
        }
        if (root.data.getId() == id) {
            return root;
        } else if (root.data.getId() > id) {
            return get(root.left, id);
        } else {
            return get(root.right, id);
        }
    }

    /**
     * Retrieves the book with the minimum ID.
     *
     * @return The book with the minimum ID, or null if the tree is empty.
     */
    public Book getMin() {
        if (root == null) {
            return null;
        }
        return getMin(root);
    }

    private Book getMin(Node root) {
        if (root.left == null) {
            return root.data;
        }
        return getMin(root.left);
    }

    /**
     * Retrieves the book with the maximum ID.
     *
     * @return The book with the maximum ID, or null if the tree is empty.
     */
    public Book getMax() {
        if (root == null) {
            return null;
        }
        return getMax(root);
    }

    private Book getMax(Node root) {
        if (root.right == null) {
            return root.data;
        }
        return getMax(root.right);
    }

    /**
     * Retrieves a book by its title.
     *
     * @param title The title of the book to be retrieved.
     * @return The book with the specified title.
     * @throws NoSuchElementException If no book is found with the specified title.
     */
    public Book getByTitle(String title) {
        Node book = getByTitle(root, title);
        if (book == null) {
            throw new NoSuchElementException("No books were found with the title provided");
        }
        return book.data;
    }

    private Node getByTitle(Node root, String title) {
        if (root == null) {
            return null;
        }
        InOrderIterator iterator = iterator();
        while (iterator.hasNext()) {
            Node currentNode = iterator.next();
            if (currentNode.data.getTitle().equals(title)) {
                return currentNode;
            }
        }
        return null;
    }

    /**
     * Prints the books in the tree in in-order traversal format.
     */
    public void formatInOrder() {
        if (isEmpty()) {
            System.out.println("Tree is empty!");
        } else {
            formatInOrder(root);
        }
    }

    private void formatInOrder(Node root) {
        if (root != null) {
            formatInOrder(root.left);
            System.out.print(root.data + " ");
            formatInOrder(root.right);
        }
    }

    /**
     * Returns an in-order iterator for the tree.
     *
     * @return An in-order iterator for the tree.
     */
    public InOrderIterator iterator() {
        return new InOrderIterator(root);
    }

    /**
     * The InOrderIterator class provides an iterator for in-order traversal of the tree.
     */
    static class InOrderIterator implements Iterator<Node> {
        private final Stack<Node> traversal;

        /**
         * Constructs an InOrderIterator for the specified root node.
         *
         * @param root The root node of the tree.
         */
        public InOrderIterator(Node root) {
            traversal = new Stack<>();
            fillLeft(root);
        }

        /**
         * Checks if there are more elements to iterate.
         *
         * @return True if there are more elements, false otherwise.
         */
        public boolean hasNext() {
            return !traversal.isEmpty();
        }

        private void fillLeft(Node current) {
            while (current != null) {
                traversal.push(current);
                current = current.left;
            }
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return The next element in the iteration.
         * @throws NoSuchElementException If there are no more elements.
         */
        public Node next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No elements remaining");
            }

            Node current = traversal.pop();
            if (current.right != null) {
                fillLeft(current.right);
            }
            return current;
        }
    }

    /**
     * The Node class represents a node in the binary search tree.
     */
    protected static class Node {
        Book data;
        Node left;
        Node right;

        /**
         * Constructs a Node with the specified book data.
         *
         * @param data The book data for the node.
         */
        public Node(Book data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    /**
     * The DeletedNode class represents a node that has been deleted from the tree.
     */
    private static class DeletedNode {
        Book element;
        Node node;

        /**
         * Constructs a DeletedNode with the specified node and deleted book.
         *
         * @param node         The node that was deleted.
         * @param deletedValue The book that was deleted.
         */
        public DeletedNode(Node node, Book deletedValue) {
            this.node = node;
            this.element = deletedValue;
        }
    }

    /**
     * Validates that the book is not null.
     *
     * @param book The book to be validated.
     * @throws IllegalArgumentException If the book is null.
     */
    private static void validateBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Cannot search for nulls");
        }
    }
}