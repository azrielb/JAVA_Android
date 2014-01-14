package model.datasource;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.backend.Backend;
import BE.Bill;
import BE.Component;
import BE.Order;
import BE.Technician;
import android.os.Environment;
import android.util.Log;
import conversions.Convertions;

public class DataSource implements Backend, Serializable {

	private static final long serialVersionUID = 1L;

	private final String PATH = Environment.getExternalStorageDirectory()
			.toString() + "/myFile";

	ArrayList<Technician> technicians;
	ArrayList<Order> orders;
	ArrayList<Component> components;
	ArrayList<Bill> bills;

	public DataSource() {
		// *****************************************
		boolean isFileExist = false;
		try {
			FileInputStream in = new FileInputStream(PATH);
			in.close();
			isFileExist = true;
		} catch (Exception ex) {
			Log.e("model", (ex != null ? ex.getMessage() : "null"));
		}

		if (isFileExist)
			load();
		else {
			technicians = new ArrayList<Technician>();
			orders = new ArrayList<Order>();
			components = new ArrayList<Component>();
			bills = new ArrayList<Bill>();
			Technician t1 = new Technician("Reuven", "Cohen", "1",
					"Reuven.Cohen@gmail.com", 1L);
			technicians.add(t1);
			Order o1 = new BE.Order(1L, "Tel Aviv", "baruch", new Date(),
					"0549912308");
			o1.setTechnicianId(t1.getId());
			Order o2 = new BE.Order(2L, "Jerusalem", "shmuel", new Date(),
					"0503758696");
			o2.setTechnicianId(t1.getId());
			Order o3 = new BE.Order(3L, "Haifa", "ohad", new Date(),
					"052345696");
			o3.setTechnicianId(t1.getId());
			Order o4 = new BE.Order(4L, "Nechalim", "noam", new Date(),
					"0523744496");
			Order o5 = new BE.Order(5L, "Haifa", "kuku", new Date(),
					"0503772998");
			Order o6 = new BE.Order(6L, "Herzelia", "bobo", new Date(),
					"0543258621");
			orders.add(o1);
			orders.add(o2);
			orders.add(o3);
			orders.add(o4);
			orders.add(o5);
			orders.add(o6);
			Component c0 = new Component("choose one --->", 0, "#00000");
			Component c1 = new Component("key", 10, "#12345");
			Component c2 = new Component("key", 10, "#12346");
			Component c3 = new Component("key", 10, "#12347");
			Component c4 = new Component("resistor", 15, "#00014");
			Component c5 = new Component("resistor", 15, "#00015");
			Component c6 = new Component("resistor", 15, "#00016");
			Component c7 = new Component("capacitor", 25, "#30007");
			Component c8 = new Component("capacitor", 25, "#30008");
			Component c9 = new Component("capacitor", 25, "#30009");
			components.add(c0);
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
			this.save();
		}
		// *****************************************
	}

	private void save() {
		FileOutputStream out = null;
		ObjectOutputStream s = null;
		try {
			out = new FileOutputStream(PATH);
			s = new ObjectOutputStream(out);
			s.writeObject(this);
		} catch (Exception ex) {
			Log.e("model", (ex != null ? ex.getMessage() : "null"), ex);
		}
		try {
			if (s != null)
				s.close();
			if (out != null)
				out.close();
		} catch (Exception ex) {
		}
	}

	private void load() {
		FileInputStream in = null;
		ObjectInputStream s = null;
		try {
			in = new FileInputStream(PATH);
			s = new ObjectInputStream(in);
			DataSource data = (DataSource) s.readObject();
			orders = data.orders;
			technicians = data.technicians;
			bills = data.bills;
			components = data.components;
		} catch (Exception ex) {
			Log.e("model", (ex != null ? ex.getMessage() : "null"), ex);
		}
		try {
			if (s != null)
				s.close();
			if (in != null)
				in.close();
		} catch (Exception ex) {
		}
	}

	@Override
	public void addTechnician(Technician technician) {
		for (Technician _technician : technicians) {
			if (technician.getId() == _technician.getId())
				return;
		}
		technicians.add(technician);
		this.save();
	}

	@Override
	public void addOrder(Order order) {
		orders.add(order);
		this.save();

	}

	@Override
	public void addComponent(Component component) {
		components.add(component);
		this.save();

	}

	@Override
	public void addBill(Bill bill) {
		bills.add(bill);
		this.save();
	}

	@Override
	public void deleteTechnician(Technician tech) {
		technicians.remove(tech);
		this.save();
	}

	@Override
	public Technician getUserByIdAndPassword(Long id, String password) {
		for (Technician user : technicians)
			if (user.getId() == id)
				return user.getPassword().equals(password) ? user : null;
		return null;
	}

	@Override
	public Technician getTechnicianByName(String technicianName) {
		for (Technician user : technicians)
			if (user.getName().equalsIgnoreCase(technicianName))
				return user;
		return null;
	}

	@Override
	public Order getOrderByNumber(Long orderNumber) {
		for (Order item : orders)
			if (item.getOrderNumber() == orderNumber)
				return item;
		return null;
	}

	@Override
	public ArrayList<Order> getOrdersByTechnicianId(Long tecnicainID) {
		ArrayList<Order> arr = new ArrayList<Order>();
		for (Order item : orders)
			if (item.getTechnicianId() == tecnicainID)
				arr.add(item);
		if (arr.size() > 0)
			return arr;
		else
			return null;
	}

	@Override
	public ArrayList<Component> getAllComponents() {
		return components;
	}

	@Override
	public ArrayList<Order> getAllOrders() {
		return orders;
	}

	@Override
	public ArrayList<Order> getFilteredOrders(String filter, Long technicianId) {
		ArrayList<Order> temp = getOrdersByTechnicianId(technicianId);
		ArrayList<Order> result = new ArrayList<Order>();
		CharSequence _filter = filter.subSequence(0, filter.length());
		for (Order item : temp)
			if (Convertions.Join(
					new String[] { item.getFullAddress(), item.getCustomer(),
							item.getCustomerPhone() }, " ").contains(_filter))
				result.add(item);
		if (result.size() > 0)
			return result;
		else
			return temp;
	}

	public ArrayList<Component> getAvailableComponent() {
		ArrayList<Component> result = new ArrayList<Component>();
		for (Component item : components)
			if (item.getOrderId() < 0)
				result.add(item);
		if (result.size() > 0)
			return result;
		else
			return null;
	}

	@Override
	public Bill getBillById(Long billId) {
		for (Bill bill : bills)
			if (bill.getOrderID() == billId)
				return bill;
		return null;
	}

	@Override
	public Technician getUserById(Long technicianId) {
		for (Technician user : technicians)
			if (user.getId() == technicianId)
				return user;
		return null;
	}

	@Override
	public List<Component> getComponentsByOrderNumber(long orderNumber)
			throws Exception {
		List<Component> result = new ArrayList<Component>();
		for (Component component : getAllComponents())
			if (component.getOrderId() == orderNumber)
				result.add(component);
		return result;
	}

}
