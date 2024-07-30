package patterns.factory.method.factory;

import patterns.factory.method.vehicle.Corporation;
import patterns.factory.method.vehicle.ElectricVehicle;
import patterns.factory.method.vehicle.FlyingElectricVehicle;
import patterns.factory.method.vehicle.FlyingMotorVehicle;
import patterns.factory.method.vehicle.MotorVehicle;

public class FlyingVehicleCorporation extends Corporation {
    @Override
    public MotorVehicle createMotorVehicle() {
        return new FlyingMotorVehicle();
    }

    @Override
    public ElectricVehicle createElectricVehicle() {
        return new FlyingElectricVehicle();
    }
}
