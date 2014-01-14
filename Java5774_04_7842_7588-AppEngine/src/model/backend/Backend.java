package model.backend;

import java.util.List;

import BE.Bill;
import BE.Component;
import BE.Order;
import BE.Technician;

public interface Backend {

	/**
	 * Add a new technician to database
	 * 
	 * @param technican
	 *            - the technician to add
	 * @throws Exception
	 */
	public void addTechnician(Technician technician) throws Exception;

	public void updateTechnician(Technician technician) throws Exception;

	public void addOrder(Order order) throws Exception;

	public void updateOrder(Order order) throws Exception;

	public void addComponent(Component component) throws Exception;

	public void updateComponent(Component component) throws Exception;

	public void addBill(Bill bill) throws Exception;

	public void updateBill(Bill bill) throws Exception;

	public void deleteTechnician(Technician book) throws Exception;

	public Technician getUserById(long id) throws Exception;

	public Technician getUserByIdAndPassword(long id, String password)
			throws Exception;

	public Technician getTechnicianByName(String technicianName)
			throws Exception;

	public List<Order> getOrdersByTechnicianId(long tecnicainID)
			throws Exception;

	public List<Order> getAllOrders() throws Exception;

	public List<Component> getAllComponents() throws Exception;

	public List<Component> getAvailableComponents() throws Exception;

	public List<Order> getFilteredOrders(String city, long id) throws Exception;

	public Order getOrderByNumber(long orderNumber) throws Exception;

	public List<Technician> getAllTechnicians() throws Exception;

	public List<Bill> getAllBills() throws Exception;

	public List<Component> getComponentsByOrderNumber(long orderNumber) throws Exception;
}
