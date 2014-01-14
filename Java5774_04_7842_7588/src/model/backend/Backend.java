package model.backend;

import java.util.ArrayList;
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

	public Technician getUserByIdAndPassword(Long id, String password)
			throws Exception;

	public Technician getTechnicianByName(String technicianName)
			throws Exception;

	public ArrayList<Order> getOrdersByTechnicianId(Long tecnicainID)
			throws Exception;

	public ArrayList<Order> getAllOrders() throws Exception;

	public ArrayList<Component> getAllComponents() throws Exception;

	public ArrayList<Component> getAvailableComponent() throws Exception;

	public ArrayList<Order> getFilteredOrders(String city, Long id)throws Exception;

	public Order getOrderByNumber(Long orderNumber) throws Exception;

	public Bill getBillById(Long billId)throws Exception;

	public Technician getUserById(Long technicianId)throws Exception;

	public List<Component> getComponentsByOrderNumber(long orderNumber) throws Exception;
}
