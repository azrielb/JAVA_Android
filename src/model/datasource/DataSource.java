package model.datasource;

import java.io.Serializable;
import java.util.ArrayList;

import model.backend.Backend;
import BE.Bill;
import BE.Component;
import BE.Order;
import BE.Technician;

public class DataSource implements Backend, Serializable {

	private static final long serialVersionUID = 1L;

	/*
	private final String PATH = Environment.getExternalStorageDirectory()
			.toString() + "/myFile";
	 */
	ArrayList<Technician> technicians;
	ArrayList<Order> orders;
	ArrayList<Component> components;
	ArrayList<Bill> bills;

	public DataSource() {
		technicians = new ArrayList<Technician>();
		orders = new ArrayList<Order>();
		components = new ArrayList<Component>();
		bills = new ArrayList<Bill>();
	}

	@Override
	public void addTechnician(Technician technician) throws Exception {
		technicians.add(technician);

	}

	@Override
	public void addOrder(Order order) throws Exception {
		orders.add(order);

	}

	@Override
	public void addComponent(Component component) throws Exception {
		components.add(component);

	}

	@Override
	public void addBill(Bill bill) throws Exception {
		bills.add(bill);
	}

	@Override
	public void deleteTechnician(Technician book) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Technician getUserByEmailAndPassword(String firstName,
			String password) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Technician getTechnicianByName(String technicianName)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Order> getOrdersByTechnician(int tecnicainID)
			throws Exception {
		ArrayList<Order> arr = new ArrayList<Order>();
		for (Order item : orders)
			if (item.getTechnician().getId() == (tecnicainID))
				arr.add(item);
		if (arr.size() > 0)
			return arr;
		else
			throw new Exception("User doesn't have orders");
	}

	@Override
	public ArrayList<Component> getAllComponent(Component user)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
