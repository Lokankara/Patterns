package patterns.factory.method.factory;

import patterns.factory.method.vehicle.Corporation;
import patterns.factory.method.vehicle.ElectricVehicle;
import patterns.factory.method.vehicle.FutureVehicleElectricCar;
import patterns.factory.method.vehicle.FutureVehicleMotorcycle;
import patterns.factory.method.vehicle.MotorVehicle;

public class FutureVehicleCorporation extends Corporation {
    @Override
    public MotorVehicle createMotorVehicle() {
        return new FutureVehicleMotorcycle();
    }

    @Override
    public ElectricVehicle createElectricVehicle() {
        return new FutureVehicleElectricCar();
    }
}
