package patterns.dto.model.entity;

import java.util.Objects;

public class Book
        implements Comparable<Book> {

    public Book() {
    }

    public Book(
            int id,
            String name,
            int quantity,
            String author,
            String publisher) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.author = author;
        this.publisher = publisher;
    }

    private int id;
    private String name;
    private int quantity;
    private String author;
    private String publisher;

    @Override
    public int compareTo(Book o) {
        return Integer.compare(this.id, o.id);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Book{id=%d, name='%s', author='%s', publisher='%s', quantity=%d}"
                .formatted(id, name, author, publisher, quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && quantity == book.quantity && Objects.equals(
                name, book.name) && Objects.equals(author,
                                                   book.author) && Objects.equals(publisher, book.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author, publisher, quantity);
    }
}
