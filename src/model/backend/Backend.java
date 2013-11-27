package model.backend;

import java.util.ArrayList;

import BE.Bill;
import BE.Component;
import BE.Order;
import BE.Technician;

public interface Backend {

	/**
	 * Add a new technician to database    
	 * @param technican - the technician to add
	 * @throws Exception
	 */
	public void addTechnician(Technician technician) throws Exception;

	public void addOrder(Order order) throws Exception;

	public void addComponent(Component component) throws Exception;
	
	public void addBill(Bill bill) throws Exception;

	public void deleteTechnician(Technician book) throws Exception;

	public Technician getUserByEmailAndPassword(String firstName, String password)
			throws Exception;

	public Technician getTechnicianByName(String technicianName) throws Exception;

	public ArrayList<Order> getOrdersByTechnician(int tecnicainID) throws Exception;

	public ArrayList<Component> getAllComponent(Component user)
			throws Exception;

}
