package applications;


import java.util.HashMap;
import java.util.NoSuchElementException;

import model.Book;

public class HashMapApp {
    public static void main(String[] args) {
        HashMap<Integer, Book> map = createAndPopulate();
        search(map, 1);
        findAll();
        hasBook(map, "Crime and Punishment");
        retrieveAndSort();
    }

    public static HashMap<Integer, Book> createAndPopulate(){
        Book book1 = new Book(1, "Con Ardientes Fulgores de gloria", "Juan David Morgan", "History/Fiction" , "2017");
        Book book2 = new Book(2, "Crime and Punishment", "Fyodor Dostoevsky", "Fiction", "1866");
        Book book3 = new Book(3, "La Loma de Cristal", "Rogelio Sinán", "Fiction", "1941");
        Book book4 = new Book(4, "Luna Verde", "Rogelio Sinán", "Poetry", "1944");
        Book book5 = new Book(5, "El Ahogado", "Tristán Solarte", "Fiction", "1963");

        HashMap<Integer, Book> map = new HashMap<>();
        
        map.put(book1.getId(), book1);
        map.put(book2.getId(), book2);
        map.put(book3.getId(), book3);
        map.put(book4.getId(), book4);
        map.put(book5.getId(), book5);

        return map;
    }

    public static void search(HashMap<Integer, Book> map, int id){
        System.out.println("1. Search for books by ID:");
        try {
            Book book = idSearch(map, id);
            System.out.println(book.format());
        } 
        catch (Exception e) {
            System.err.println("Error: "+e);
        }
    }

    private static Book idSearch(HashMap<Integer, Book> map, int id){
        Book book = map.get(id);

        if (book == null){
            throw new NoSuchElementException("Book not found");
        }

        return book;
    }

    public static void findAll(){
        System.out.println("\n2. Find all books under a particular genre.");
        System.err.println("Not within the limitations.");
        System.err.println("I would need to use a list to store the books under the genre for this to be efficient\n");
    }

    private static boolean contains(HashMap<Integer, Book> map, String title) {
        for (Book book : map.values()) {
            if (book.getTitle().toLowerCase().equals(title.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static void hasBook(HashMap<Integer, Book> map, String title){
        System.out.println("\n3. Determine whether a book with a specific title exists in the library.");
        System.out.println(contains(map, title)+"\n");
    }

    public static void retrieveAndSort(){
        System.out.println("4. Retrieve books published in a specific year, sorted by their titles.");
        System.out.println("Same logic as 2.");
        System.err.println("I would need to use a list to store the books under the genre for this to be efficient\n");
    }
}
