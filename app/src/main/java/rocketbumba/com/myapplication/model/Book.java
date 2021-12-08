package rocketbumba.com.myapplication.model;

public class Book {
    private String id;
    private String book_NAME;
    private String isbn;
    private int aisle;
    private String author;

    public Book() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getBook_NAME() {
        return book_NAME;
    }

    public void setBook_NAME(String book_NAME) {
        this.book_NAME = book_NAME;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getAisle() {
        return aisle;
    }

    public void setAisle(int aisle) {
        this.aisle = aisle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Book(String id, String book_NAME, String isbn, int aisle, String author) {
        this.id = id;
        this.book_NAME = book_NAME;
        this.isbn = isbn;
        this.aisle = aisle;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", book_NAME='" + book_NAME + '\'' +
                ", isbn='" + isbn + '\'' +
                ", aisle=" + aisle +
                ", author='" + author + '\'' +
                '}';
    }
}
