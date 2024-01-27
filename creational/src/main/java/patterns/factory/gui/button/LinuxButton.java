package patterns.factory.gui.button;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LinuxButton implements Button{
    @Override
    public void draw() {
        log.info("{}", this.getClass().getSimpleName());
    }
}
