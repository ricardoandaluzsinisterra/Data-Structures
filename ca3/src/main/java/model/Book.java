package model;

public class Book implements Comparable<Book> {
    private static int bookCount = 0;

    private int id;
    private String title;
    private String author;
    private String genre;
    private String yearOfPublication;

    public Book(int id, String title, String author, String genre, String yearOfPublication){
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.yearOfPublication = yearOfPublication;

        bookCount++;
        this.id = bookCount;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getYearOfPublication() {
        return yearOfPublication;
    }

    @Override
    public String toString() {
        return "Book{" +
                "Title='" + title + '\'' +
                ", Author='" + author + '\'' +
                ", Genre=" + genre + '\'' +
                ", Year of publication=" + yearOfPublication +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book person = (Book) o;
        return id == person.id;
    }

    @Override
    public int compareTo(Book o) {
        return Integer.compare(o.id, this.id);
    }


}
