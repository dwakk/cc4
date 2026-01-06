package menu;

import dao.DataAccessObject;
import domain.Vehicle;

public class Controller {
	private DataAccessObject dao;
	
	public Controller(DataAccessObject dao) {
		this.dao = dao;
	}
	
	public String[] create(Vehicle vehicle) {
		return dao.create(vehicle);
	}
	
	public String[] read() {
		return dao.read();
	}
	
	public String[] readCritical() {
		return dao.readCritical();
	}
	
	public String[] update(String id, Vehicle vehicle) {
		return dao.update(id, vehicle);
	}
	
	public String[] delete(String id) {
		return dao.delete(id);
	}

}
