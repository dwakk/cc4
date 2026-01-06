package domain;

public class Moto extends Vehicle {
	public Moto(int id, VehicleCategory category, String model, int km) {
		super(id, category, model, km);
	}

	@Override
	public VehicleKind getKind() {
		return VehicleKind.MOTO;
	}
	
}
