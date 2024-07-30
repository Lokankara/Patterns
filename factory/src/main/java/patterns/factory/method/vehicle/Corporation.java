package patterns.factory.method.vehicle;

public abstract class Corporation {
    public abstract MotorVehicle createMotorVehicle();

    public abstract ElectricVehicle createElectricVehicle();
}
