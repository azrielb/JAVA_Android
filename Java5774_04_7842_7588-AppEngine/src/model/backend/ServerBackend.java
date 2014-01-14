package model.backend;

import java.util.List;

import javax.inject.Named;

import BE.Bill;
import BE.Component;
import BE.Order;
import BE.Technician;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;

@Api(name = "serverApi")
public class ServerBackend implements Backend {

	Backend dal = Factory.getInstance();

	@Override
	@ApiMethod(name = "addTechnician", path = "serverApi.addTechnician", httpMethod = HttpMethod.POST)
	public void addTechnician(Technician technician) throws Exception {
		dal.addTechnician(technician);
	}

	@Override
	@ApiMethod(name = "addOrder", path = "serverApi.addOrder", httpMethod = HttpMethod.POST)
	public void addOrder(Order order) throws Exception {
		dal.addOrder(order);
	}

	@Override
	@ApiMethod(name = "addComponent", path = "serverApi.addComponent", httpMethod = HttpMethod.POST)
	public void addComponent(Component component) throws Exception {
		dal.addComponent(component);
	}

	@Override
	@ApiMethod(name = "updateComponent", path = "serverApi.updateComponent", httpMethod = HttpMethod.POST)
	public void updateComponent(Component component) throws Exception {
		dal.updateComponent(component);
	}

	@Override
	@ApiMethod(name = "addBill", path = "serverApi.addBill", httpMethod = HttpMethod.POST)
	public void addBill(Bill bill) throws Exception {
		dal.addBill(bill);

	}

	@Override
	@ApiMethod(name = "deleteTechnician", path = "serverApi.deleteTechnician", httpMethod = HttpMethod.POST)
	public void deleteTechnician(Technician book) throws Exception {
		dal.deleteTechnician(book);
	}

	@Override
	@ApiMethod(name = "getUserById", path = "serverApi.getUserById", httpMethod = HttpMethod.POST)
	public Technician getUserById(@Named("id") long id) throws Exception {
		return dal.getUserById(id);
	}

	@Override
	@ApiMethod(name = "getUserByIdAndPassword", path = "serverApi.getUserByIdAndPassword", httpMethod = HttpMethod.POST)
	public Technician getUserByIdAndPassword(@Named("id") long id,
			@Named("password") String password) throws Exception {
		return dal.getUserByIdAndPassword(id, password);
	}

	@Override
	@ApiMethod(name = "getTechnicianByName", path = "serverApi.getTechnicianByName", httpMethod = HttpMethod.POST)
	public Technician getTechnicianByName(
			@Named("technicianName") String technicianName) throws Exception {
		return dal.getTechnicianByName(technicianName);
	}

	@Override
	@ApiMethod(name = "getOrdersByTechnicianId", path = "serverApi.getOrdersByTechnicianId", httpMethod = HttpMethod.POST)
	public List<Order> getOrdersByTechnicianId(@Named("technicianID") long tecnicainID)
			throws Exception {
		return dal.getOrdersByTechnicianId(tecnicainID);
	}

	@Override
	@ApiMethod(name = "getAllOrders", path = "serverApi.getAllOrders", httpMethod = HttpMethod.POST)
	public List<Order> getAllOrders() throws Exception {
		return dal.getAllOrders();
	}

	@Override
	@ApiMethod(name = "getAllComponents", path = "serverApi.getAllComponents", httpMethod = HttpMethod.POST)
	public List<Component> getAllComponents() throws Exception {
		return dal.getAllComponents();
	}

	@Override
	@ApiMethod(name = "getAvailableComponents", path = "serverApi.getAvailableComponents", httpMethod = HttpMethod.POST)
	public List<Component> getAvailableComponents() throws Exception {
		return dal.getAvailableComponents();
	}

	@Override
	@ApiMethod(name = "getFilteredOrders", path = "serverApi.getFilteredOrders", httpMethod = HttpMethod.POST)
	public List<Order> getFilteredOrders(@Named("filter") String filter, @Named("id") long id)
			throws Exception {
		return dal.getFilteredOrders(filter, id);
	}

	@Override
	@ApiMethod(name = "getOrderByNumber", path = "serverApi.getOrderByNumber", httpMethod = HttpMethod.POST)
	public Order getOrderByNumber(@Named("orderNumber") long orderNumber) throws Exception {
		return dal.getOrderByNumber(orderNumber);
	}

	@Override
	@ApiMethod(name = "getAllTechnicians", path = "serverApi.getAllTechnicians", httpMethod = HttpMethod.POST)
	public List<Technician> getAllTechnicians() throws Exception {
		return dal.getAllTechnicians();
	}

	@Override
	@ApiMethod(name = "updateTechnician", path = "serverApi.updateTechnician", httpMethod = HttpMethod.POST)
	public void updateTechnician(Technician technician) throws Exception {
		dal.updateTechnician(technician);
	}

	@Override
	@ApiMethod(name = "updateOrder", path = "serverApi.updateOrder", httpMethod = HttpMethod.POST)
	public void updateOrder(Order order) throws Exception {
		dal.updateOrder(order);
	}

	@Override
	@ApiMethod(name = "updateBill", path = "serverApi.updateBill", httpMethod = HttpMethod.POST)
	public void updateBill(Bill bill) throws Exception {
		dal.updateBill(bill);
	}

	@Override
	@ApiMethod(name = "getAllBills", path = "serverApi.getAllBills", httpMethod = HttpMethod.POST)
	public List<Bill> getAllBills() throws Exception {
		return dal.getAllBills();
	}

	@Override
	@ApiMethod(name = "getComponentsByOrderNumber", path = "serverApi.getComponentsByOrderNumber", httpMethod = HttpMethod.POST)
	public List<Component> getComponentsByOrderNumber(@Named("orderNumber") long orderNumber) throws Exception {
		return dal.getComponentsByOrderNumber(orderNumber);
	}
}
