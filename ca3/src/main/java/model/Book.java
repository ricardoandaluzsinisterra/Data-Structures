package model;

public class Book implements Comparable<Book> {
    
    private final int id;
    private final String title;
    private final String author;
    private final String genre;
    private final String yearOfPublication;

    /**
     * Constructs a new Book object with the specified details.
     *
     * @param id                The ID of the book.
     * @param title             The title of the book.
     * @param author            The author of the book.
     * @param genre             The genre of the book.
     * @param yearOfPublication The year of publication of the book.
     */
    public Book(int id, String title, String author, String genre, String yearOfPublication) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.yearOfPublication = yearOfPublication;
    }

    /**
     * Gets the ID of the book.
     *
     * @return The ID of the book.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the title of the book.
     *
     * @return The title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the author of the book.
     *
     * @return The author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Gets the genre of the book.
     *
     * @return The genre of the book.
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Gets the year of publication of the book.
     *
     * @return The year of publication of the book.
     */
    public String getYearOfPublication() {
        return yearOfPublication;
    }

    /**
     * Returns a string representation of the book.
     *
     * @return A string representation of the book.
     */
    @Override
    public String toString() {
        return "Book{" +
                "Title='" + title + '\'' +
                ", Author='" + author + '\'' +
                ", Genre='" + genre + '\'' +
                ", Year of publication=" + yearOfPublication +
                '}';
    }

    /**
     * Formats the book details into a readable string.
     *
     * @return A formatted string representation of the book.
     */
    public String format() {
        return String.format("Title: %s\nAuthor: %s\nGenre: %s\nYear of Publication: %s",
                title, author, genre, yearOfPublication);
    }

    /**
     * Compares this book to another book based on their IDs.
     *
     * @param o The book to be compared.
     * @return A negative integer, zero, or a positive integer as this book's ID is less than, equal to, or greater than the specified book's ID.
     */
    @Override
    public int compareTo(Book o) {
        return Integer.compare(this.id, o.id);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o The reference object with which to compare.
     * @return True if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;
        return id == book.id;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}