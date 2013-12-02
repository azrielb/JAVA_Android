package model.datasource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import model.backend.Backend;
import BE.Bill;
import BE.Component;
import BE.Order;
import BE.Technician;

public class DataSource implements Backend, Serializable {

	private static final long serialVersionUID = 1L;

	// private final String PATH = Environment.getExternalStorageDirectory()
	// .toString() + "/myFile";

	ArrayList<Technician> technicians;
	ArrayList<Order> orders;
	ArrayList<Component> components;
	ArrayList<Bill> bills;

	public DataSource() {
		technicians = new ArrayList<Technician>();
		orders = new ArrayList<Order>();
		components = new ArrayList<Component>();
		bills = new ArrayList<Bill>();
		Technician t1 = new Technician("a", "a", "1", "a@a", 1);
		technicians.add(t1);
		Order o1 = new BE.Order(1, "Tel Aviv", "baruch", new Date());
		o1.setTechnician(t1);
		Order o2 = new BE.Order(2, "Jerusalem", "shmuel", new Date());
		o2.setTechnician(t1);
		Order o3 = new BE.Order(3, "Haifa", "ohad", new Date());
		o3.setTechnician(t1);
		Order o4 = new BE.Order(4, "Nechalim", "noam", new Date());
		Order o5 = new BE.Order(5, "Haifa", "kuku", new Date());
		Order o6 = new BE.Order(6, "Herzelia", "bobo", new Date());
		orders.add(o1);
		orders.add(o2);
		orders.add(o3);
		orders.add(o4);
		orders.add(o5);
		orders.add(o6);
		Component c1 = new Component("key", 10, "#12345");
		Component c2 = new Component("key", 10, "#12346");
		Component c3 = new Component("key", 10, "#12347");
		Component c4 = new Component("resistor", 15, "#00014");
		Component c5 = new Component("resistor", 15, "#00015");
		Component c6 = new Component("resistor", 15, "#00016");
		Component c7 = new Component("capacitor", 25, "#30007");
		Component c8 = new Component("capacitor", 25, "#30008");
		Component c9 = new Component("capacitor", 25, "#30009");
		components.add(c1);
		components.add(c2);
		components.add(c3);
		components.add(c4);
		components.add(c5);
		components.add(c6);
		components.add(c7);
		components.add(c8);
		components.add(c9);
		o1.addComponent(c1);
		o1.addComponent(c4);
		o1.addComponent(c7);
	}

	@Override
	public void addTechnician(Technician technician) {
		technicians.add(technician);
	}

	@Override
	public void addOrder(Order order) {
		orders.add(order);
	}

	@Override
	public void addComponent(Component component) {
		components.add(component);
	}

	@Override
	public void addBill(Bill bill) {
		bills.add(bill);
	}

	@Override
	public void deleteTechnician(Technician book) {
		// TODO Auto-generated method stub

	}

	@Override
	public Technician getUserByIdAndPassword(int id, String password) {
		for (Technician user : technicians)
			if (user.getId() == (id) && user.getPassword().equals(password))
				return user;

		// throw new Exception("Wrong Email address or password.");
		return null;
	}

	@Override
	public Technician getTechnicianByName(String technicianName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order getOrderByNumber(int orderNumber) {
		for (Order item : orders)
			if (item.getOrderNumber() == orderNumber)
				return item;
		return null;
	}

	@Override
	public ArrayList<Order> getOrdersByTechnicianId(int tecnicainID) {
		ArrayList<Order> arr = new ArrayList<Order>();
		for (Order item : orders)
			if (item.getTechnician().getId() == (tecnicainID))
				arr.add(item);
		if (arr.size() > 0)
			return arr;
		else
			return null;
	}

	@Override
	public ArrayList<Component> getAllComponent() {
		return components;
	}

	@Override
	public ArrayList<Order> getAllOrders() {
		return orders;
	}

	@Override
	public ArrayList<Order> getOrdersByCity(String city, int id) {
		ArrayList<Order> temp = getOrdersByTechnicianId(id);
		ArrayList<Order> result = new ArrayList<Order>();
		for (Order item : temp)
			if (compareWords(city, item.getCity()))
				result.add(item);
		if (result.size() > 0)
			return result;
		else
			return temp;
	}

	private boolean compareWords(String source, String word) {
		if (source.length() == word.length())
			return source.equals(word);
		if (source.length() > word.length())
			return false;
		return (source.equals(word.subSequence(0, source.length())));
	}

	public ArrayList<Component> getAvailableComponent() {
		ArrayList<Component> result = new ArrayList<Component>();
		for (Component item : components)
			if (item.isExist())
				result.add(item);
		if (result.size() > 0)
			return result;
		else
			return null;
	}

}
