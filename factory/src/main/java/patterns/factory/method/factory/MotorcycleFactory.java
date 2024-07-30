package patterns.factory.method.factory;

import patterns.factory.method.vehicle.MotorCycle;
import patterns.factory.method.vehicle.MotorVehicle;

public class MotorcycleFactory extends MotorVehicleFactory {
    @Override
    protected MotorVehicle createMotorVehicle() {
        return new MotorCycle();
    }
}
