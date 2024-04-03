package patterns.threads;

import patterns.dto.model.entity.Book;

import java.util.PriorityQueue;
import java.util.Queue;

public class BookQueue {
    public static void main(String[] args) {
        Queue<Book> queue = new PriorityQueue<>();

        Book b1 = new Book(12, "America", 5, "John", "SPB");
        Book b2 = new Book(2, "Poland", 5, "John", "SPB");
        Book b3 = new Book(1, "Africa", 5, "John", "SPB");
        Book b4 = new Book(3, "Asia", 5, "John", "SPB");

        queue.add(b3);
        queue.add(b1);
        queue.add(b2);
        queue.add(b4);

        queue.forEach(System.out::println);

        queue.remove();
        System.out.println("After remove " + queue);

        queue.remove();
        System.out.println("After remove " + queue);
    }
}
