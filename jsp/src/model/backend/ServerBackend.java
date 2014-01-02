package model.backend;

import java.util.List;

import BE.Bill;
import BE.Component;
import BE.Order;
import BE.Technician;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;

@Api
public class ServerBackend implements Backend {

	Backend db = new DBbackend();

	@Override
	@ApiMethod
	public void addTechnician(Technician technician) throws Exception {
		db.addTechnician(technician);
	}

	@Override
	@ApiMethod
	public void addOrder(Order order) throws Exception {
		db.addOrder(order);
	}

	@Override
	@ApiMethod
	public void addComponent(Component component) throws Exception {
		db.addComponent(component);
	}

	@Override
	@ApiMethod
	public void addBill(Bill bill) throws Exception {
		db.addBill(bill);

	}

	@Override
	@ApiMethod
	public void deleteTechnician(Technician book) throws Exception {
		db.deleteTechnician(book);
	}

	@Override
	public Technician getUserById(Long id) throws Exception {
		return db.getUserById(id);
	}

	@Override
	public Technician getUserByIdAndPassword(Long id, String password)
			throws Exception {
		return db.getUserByIdAndPassword(id, password);
	}

	@Override
	public Technician getTechnicianByName(String technicianName)
			throws Exception {
		return db.getTechnicianByName(technicianName);
	}

	@Override
	public List<Order> getOrdersByTechnicianId(int tecnicainID)
			throws Exception {
		return db.getOrdersByTechnicianId(tecnicainID);
	}

	@Override
	public List<Order> getAllOrders() {
		return db.getAllOrders();
	}

	@Override
	public List<Component> getAllComponent() throws Exception {
		return db.getAllComponent();
	}

	@Override
	public List<Component> getAvailableComponent() throws Exception {
		return db.getAvailableComponent();
	}

	@Override
	public List<Order> getFilteredOrders(String city, int id) {
		return db.getFilteredOrders(city, id);
	}

	@Override
	public Order getOrderByNumber(int orderNumber) {
		return db.getOrderByNumber(orderNumber);
	}

	@Override
	public List<Technician> getAllTechnichians() {
		return db.getAllTechnichians();
	}
}
