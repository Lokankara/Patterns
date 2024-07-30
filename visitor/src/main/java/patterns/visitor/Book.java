package patterns.visitor;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Book implements Element {

    private String title;
    private double price;

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
