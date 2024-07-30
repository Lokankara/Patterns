package patterns.visitor;

import lombok.Getter;

@Getter
public class NameVisitor implements Visitor {

    private final StringBuilder names = new StringBuilder();

    @Override
    public void visit(Book book) {
        names.append("Book: ").append(book.getTitle()).append("\n");
    }

    @Override
    public void visit(Fruit fruit) {
        names.append("Fruit: ").append(fruit.getName()).append("\n");
    }
}
