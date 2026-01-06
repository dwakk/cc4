package domain;

public class Car extends Vehicle {
	public Car(int id, VehicleCategory category, String model, int km) {
		super(id, category, model, km);
	}

	@Override
	public VehicleKind getKind() {
		return VehicleKind.CAR;
	}
}
