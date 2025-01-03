package applications;

import model.Book;
import utils.BinarySearchTree;

public class BSTApp {
    public static void main(String[] args) {
        BinarySearchTree tree = createAndPopulate();
        search(tree, 5);
        findAll();
        hasBook(tree, "Con Ardientes Fulgores de gloria");
        booksInYear();
    }

    /**
     * Creates and populates a BinarySearchTree with Book objects.
     *
     * @return A BinarySearchTree containing Book objects.
     */
    public static BinarySearchTree createAndPopulate() {
        Book book1 = new Book(1, "Con Ardientes Fulgores de gloria", "Juan David Morgan", "History/Fiction", "2017");
        Book book2 = new Book(2, "Crime and Punishment", "Fyodor Dostoevsky", "Fiction", "1866");
        Book book3 = new Book(3, "La Loma de Cristal", "Rogelio Sinán", "Fiction", "1941");
        Book book4 = new Book(4, "Luna Verde", "Rogelio Sinán", "Poetry", "1944");
        Book book5 = new Book(5, "El Ahogado", "Tristán Solarte", "Fiction", "1963");

        BinarySearchTree tree = new BinarySearchTree();

        tree.add(book1);
        tree.add(book2);
        tree.add(book3);
        tree.add(book4);
        tree.add(book5);

        return tree;
    }

    /**
     * Searches for a book by its ID and prints its details.
     *
     * @param tree The BinarySearchTree containing Book objects.
     * @param id   The ID of the book to search for.
     */
    public static void search(BinarySearchTree tree, int id) {
        System.out.println("1. Search for books by ID:");
        try {
            Book book = tree.get(id);
            System.out.println(book.format());
        } catch (Exception e) {
            System.err.println("Error. No elements were found:\n" + e);
        }
    }

    /**
     * Prints a message indicating that finding all books under a particular genre is not supported.
     */
    public static void findAll() {
        System.out.println("\n2. Find all books under a particular genre.");
        System.err.println("Can't do in BST. Stacks (the base for BST's) are not designed for this kind of retrieval.");
        System.err.println("For me to implement this I would need to (from the BST) iterate through the Stack and return a List of books but this would be over complicating the Data Structure\n");
    }

    /**
     * Determines whether a book with a specific title exists in the library and prints its details.
     *
     * @param tree  The BinarySearchTree containing Book objects.
     * @param title The title of the book to search for.
     */
    public static void hasBook(BinarySearchTree tree, String title) {
        System.out.println("3. Determine whether a book with a specific title exists in the library");
        try {
            Book book = tree.getByTitle(title);
            System.out.println(book.format());
        } catch (Exception e) {
            System.err.println("Error. No elements were found:\n" + e);
        }
    }

    /**
     * Prints a message indicating that retrieving and sorting books published in a specific year is not supported.
     */
    public static void booksInYear() {
        System.out.println("\n4. Retrieve books published in a specific year, sorted by their titles.");
        System.err.println("Again, the same logic:");
        System.err.println("Can't do in BST. Stacks (the base for BST's) are not designed for this kind of retrieval.");
        System.err.println("For me to implement this I would need to (from the BST) iterate through the Stack and return a List of books but this would be over complicating the Data Structure\n");
    }
}