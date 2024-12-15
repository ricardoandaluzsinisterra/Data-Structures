package model;

public class Book implements Comparable<Book> {
    
    private final int id;
    private final String title;
    private final String author;
    private final String genre;
    private final String yearOfPublication;

    public Book(int id, String title, String author, String genre, String yearOfPublication){
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.yearOfPublication = yearOfPublication;
        this.id = id;
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


    public String format() {
    return String.format("Title: %s\nAuthor: %s\nGenre: %s\nYear of Publication: %s",
            title, author, genre, yearOfPublication);
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
        return Integer.compare(this.id, o.id);
    }


}
