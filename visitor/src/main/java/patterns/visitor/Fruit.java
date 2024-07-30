package patterns.visitor;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class Fruit implements Element {
    private String name;
    private double weight;

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
