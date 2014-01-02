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

	public void addOrder(Order order) throws Exception;

	public void addComponent(Component component) throws Exception;

	public void addBill(Bill bill) throws Exception;

	public void deleteTechnician(Technician book) throws Exception;

	public Technician getUserById(Long id) throws Exception;

	public Technician getUserByIdAndPassword(Long id, String password)
			throws Exception;

	public Technician getTechnicianByName(String technicianName)
			throws Exception;

	public List<Order> getOrdersByTechnicianId(int tecnicainID)
			throws Exception;

	public List<Order> getAllOrders();

	public List<Component> getAllComponent() throws Exception;

	public List<Component> getAvailableComponent() throws Exception;

	public List<Order> getFilteredOrders(String city, int id);
	
	public List<Technician> getAllTechnichians();

	public Order getOrderByNumber(int orderNumber);

}
