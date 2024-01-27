package patterns.factory.gui.checkbox;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LinuxCheckBox
        implements CheckBox {
    @Override
    public void draw() {
        log.info("{}", this.getClass().getSimpleName());
    }
}
