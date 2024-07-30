package patterns.visitor;

public class VisitorPatternTest {
    public static void main(String[] args) {
        Element[] items = new Element[]{
                new Book("Java Programming", 29.99),
                new Fruit("Apple", 2.5)
        };

        PriceVisitor priceVisitor = new PriceVisitor();
        NameVisitor nameVisitor = new NameVisitor();

        for (Element item : items) {
            item.accept(priceVisitor);
            item.accept(nameVisitor);
        }

        System.out.println("Total cost: $" + priceVisitor.getTotalCost());
        System.out.println("Items:\n" + nameVisitor.getNames());
    }
}
