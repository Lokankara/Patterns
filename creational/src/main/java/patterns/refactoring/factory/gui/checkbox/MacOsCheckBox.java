package patterns.refactoring.factory.gui.checkbox;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MacOsCheckBox
        implements CheckBox {
    @Override
    public void draw() {
        log.info("{}", this.getClass().getSimpleName());
    }
}
