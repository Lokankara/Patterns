package patterns.factory.method.vehicle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MotorCycle implements MotorVehicle {
    @Override
    public void build() {
        log.info("Build {}", this.getClass().getSimpleName());
    }
}
