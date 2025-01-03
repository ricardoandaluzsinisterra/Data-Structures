package applications;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import model.Book;

public class ListApp {
    public static void main(String[] args) {
        ArrayList<Book> list = createAndPopulate();
        search(list, 1);
        search(list, "fiction");
        hasBook(list, "Con Ardientes Fulgores de gloria");
        retrieveAndSort(list, "1941");
    }

    /**
     * Creates and populates an ArrayList with Book objects.
     *
     * @return An ArrayList containing Book objects.
     */
    public static ArrayList<Book> createAndPopulate() {
        Book book1 = new Book(1, "Con Ardientes Fulgores de gloria", "Juan David Morgan", "History/Fiction", "2017");
        Book book2 = new Book(2, "Crime and Punishment", "Fyodor Dostoevsky", "Fiction", "1866");
        Book book3 = new Book(3, "La Loma de Cristal", "Rogelio Sinán", "Fiction", "1941");
        Book book4 = new Book(4, "Luna Verde", "Rogelio Sinán", "Poetry", "1944");
        Book book5 = new Book(5, "El Ahogado", "Tristán Solarte", "Fiction", "1963");

        ArrayList<Book> list = new ArrayList<>();

        list.add(book1);
        list.add(book2);
        list.add(book3);
        list.add(book4);
        list.add(book5);

        return list;
    }

    /**
     * Searches for a book by its ID and prints its details.
     *
     * @param list The ArrayList containing Book objects.
     * @param id   The ID of the book to search for.
     */
    public static void search(ArrayList<Book> list, int id) {
        System.out.println("1. Search for books by ID:");
        try {
            Book book = idSearch(list, id);
            System.out.println(book.format());
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }

    /**
     * Searches for a book by its ID.
     *
     * @param list The ArrayList containing Book objects.
     * @param id   The ID of the book to search for.
     * @return The Book object with the specified ID.
     * @throws NoSuchElementException If no book is found with the specified ID.
     */
    private static Book idSearch(ArrayList<Book> list, int id) {
        for (Book book : list) {
            if (book.getId() == id) {
                return book;
            }
        }
        throw new NoSuchElementException("Book not found");
    }

    /**
     * Searches for books by their genre and prints their details.
     *
     * @param list  The ArrayList containing Book objects.
     * @param genre The genre of the books to search for.
     */
    public static void search(ArrayList<Book> list, String genre) {
        System.out.println("\n2. Find all books under a particular genre:");
        try {
            ArrayList<Book> genreBooks = genreSearch(list, genre);
            for (Book book : genreBooks) {
                System.out.println(book.format() + "\n");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }

    /**
     * Searches for books by their genre.
     *
     * @param list  The ArrayList containing Book objects.
     * @param genre The genre of the books to search for.
     * @return An ArrayList of Book objects with the specified genre.
     * @throws NoSuchElementException If no books are found with the specified genre.
     */
    private static ArrayList<Book> genreSearch(ArrayList<Book> list, String genre) {
        ArrayList<Book> genreBooks = new ArrayList<>();
        for (Book book : list) {
            if (book.getGenre().toLowerCase().equals(genre.toLowerCase())) {
                genreBooks.add(book);
            }
        }

        if (genreBooks.isEmpty()) {
            throw new NoSuchElementException("No books were found.");
        }

        return genreBooks;
    }

    /**
     * Checks if a book with a specific title exists in the library.
     *
     * @param list  The ArrayList containing Book objects.
     * @param title The title of the book to search for.
     * @return True if a book with the specified title exists, false otherwise.
     */
    private static boolean contains(ArrayList<Book> list, String title) {
        for (Book book : list) {
            if (book.getTitle().toLowerCase().equals(title.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines whether a book with a specific title exists in the library and prints the result.
     *
     * @param list  The ArrayList containing Book objects.
     * @param title The title of the book to search for.
     */
    public static void hasBook(ArrayList<Book> list, String title) {
        System.out.println("\n3. Determine whether a book with a specific title exists in the library");
        System.out.println(contains(list, title));
    }

    /**
     * Retrieves books published in a specific year.
     *
     * @param list The ArrayList containing Book objects.
     * @param year The year of publication to search for.
     * @return An ArrayList of Book objects published in the specified year.
     * @throws NoSuchElementException If no books are found published in the specified year.
     */
    private static ArrayList<Book> retrieveBooks(ArrayList<Book> list, String year) {
        ArrayList<Book> BooksOfYear = new ArrayList<>();
        for (Book book : list) {
            if (book.getYearOfPublication().equals(year)) {
                BooksOfYear.add(book);
            }
        }

        if (BooksOfYear.isEmpty()) {
            throw new NoSuchElementException("No books were found.");
        }

        return BooksOfYear;
    }

    /**
     * Retrieves books published in a specific year, sorted by their titles, and prints their details.
     *
     * @param list The ArrayList containing Book objects.
     * @param year The year of publication to search for.
     */
    public static void retrieveAndSort(ArrayList<Book> list, String year) {
        System.out.println("\n4. Retrieve books published in a specific year, sorted by their titles.\n");
        System.out.println("Done, but not sorted. For that sort by title I would need to create my own sort or modify the natural order of Book.\n");
        try {
            ArrayList<Book> BooksOfYear = retrieveBooks(list, year);
            for (Book book : BooksOfYear) {
                System.out.println(book.format() + "\n");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }
}