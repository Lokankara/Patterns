package patterns.factory.method.factory;

import patterns.factory.method.vehicle.Car;
import patterns.factory.method.vehicle.MotorVehicle;

public class CarFactory extends MotorVehicleFactory {
    @Override
    protected MotorVehicle createMotorVehicle() {
        return new Car();
    }
}
