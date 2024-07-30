package patterns.factory.method.vehicle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FlyingElectricVehicle implements ElectricVehicle {
    @Override
    public void build() {
        log.info("Build {}", this.getClass().getSimpleName());
    }
}
