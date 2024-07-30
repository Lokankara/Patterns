package patterns.visitor;

import lombok.Getter;

@Getter
public class PriceVisitor implements Visitor {

    private double totalCost;

    @Override
    public void visit(Book book) {
        totalCost += book.getPrice();
    }

    @Override
    public void visit(Fruit fruit) {
        totalCost += fruit.getWeight() * 2;
    }
}
