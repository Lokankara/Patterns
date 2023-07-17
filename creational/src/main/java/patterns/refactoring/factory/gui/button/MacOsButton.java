package patterns.refactoring.factory.gui.button;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MacOsButton
        implements Button{
    @Override
    public void draw() {
        log.info("{}", this.getClass().getSimpleName());
    }
}
