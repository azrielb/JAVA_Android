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
	public void addTechnician(Technician technician) /*throws Exception*/;

	public void addOrder(Order order) /*throws Exception*/;

	public void addComponent(Component component) /*throws Exception*/;
	
	public void addBill(Bill bill) /*throws Exception*/;

	public void deleteTechnician(Technician book) /*throws Exception*/;

	public Technician getUserByIdAndPassword(int id, String password)
	/*throws Exception*/;

	public Technician getTechnicianByName(String technicianName) /*throws Exception*/;

	public ArrayList<Order> getOrdersByTechnicianId(int tecnicainID) /*throws Exception*/;
	
	public ArrayList<Order> getAllOrders();

	public ArrayList<Component> getAllComponent()/*throws Exception*/;
	
	public ArrayList<Component> getAvailableComponent()/*throws Exception*/;

	public ArrayList<Order> getOrdersByCity(String city, int id);

	public Order getOrderByNumber(int orderNumber);

}
