package patterns.decorator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class Coffee {
    private final double coffee;
    private final double sugar;
    private final double water;
    private final double milk;

    public abstract double getCost();

    public String getDescription() {
        return getClass().getSimpleName();
    }
}
