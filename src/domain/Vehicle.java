package domain;

public abstract class Vehicle {
	protected int id;
	protected VehicleCategory category;
	protected String model;
	protected int km;
	
	public Vehicle(int id, VehicleCategory category, String model, int km) {
		assert id > 0;
		assert model != null;
		assert km > 0;
		
		this.id = id;
		this.category = category;
		this.model = model;
		this.km = km;
	}
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public VehicleCategory getCategory() {
		return category;
	}



	public void setType(VehicleCategory category) {
		this.category = category;
	}



	public String getModel() {
		return model;
	}



	public void setModel(String model) {
		this.model = model;
	}



	public int getKm() {
		return km;
	}



	public void setKm(int km) {
		this.km = km;
	}

	public abstract VehicleKind getKind();

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", category=" + category + ", model=" + model + ", km=" + km +  "]";
	}
	
	
}
