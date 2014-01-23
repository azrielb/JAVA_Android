package model.backend;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import appEngineBE.Bill;
import appEngineBE.Component;
import appEngineBE.Convertions;
import appEngineBE.Order;
import appEngineBE.Technician;

public class DBbackend implements Backend {

	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	private String PERSISTENCE_UNIT_NAME = "transactions-optional";

	public DBbackend() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = emf.createEntityManager();
	}

	private void insert(Object obj) throws Exception {
		em.getTransaction().begin();
		em.persist(obj);
		try {
			em.getTransaction().commit();
		} catch (Exception e) {
			em.clear();
			throw e;
		}
	}

	@Override
	public void addTechnician(Technician technician) throws Exception {
		Technician user = null;
		try {
			user = getUserById(technician.getId());
		} catch (Exception e) {
		}
		if (user != null)
			throw new Exception("User " + technician.getId() + " exists!");
		insert(technician);
	}

	@Override
	public void updateTechnician(Technician technician) throws Exception {
		insert(technician);
	}

	@Override
	public void addOrder(Order order) throws Exception {
		Order existing = null;
		try {
			existing = getOrderByNumber(order.getOrderNumber());
		} catch (Exception e) {
		}
		if (existing != null)
			throw new Exception("Order " + order.getOrderNumber() + " exists!");
		insert(order);
	}

	@Override
	public void updateOrder(Order order) throws Exception {
		insert(order);
	}

	@Override
	public void addComponent(Component component) throws Exception {
		for (Component c : getAllComponents())
			if (c != null
					&& c.getSerialNumber().equalsIgnoreCase(
							component.getSerialNumber()))
				throw new Exception("Component " + c.getSerialNumber()
						+ " exists!");
		insert(component);
	}

	@Override
	public void updateComponent(Component component) throws Exception {
		insert(component);
	}

	@Override
	public void addBill(Bill bill) throws Exception {
		insert(bill);
	}

	@Override
	public void updateBill(Bill bill) throws Exception {
		insert(bill);
	}

	@Override
	public void deleteTechnician(Technician technic) throws Exception {
		// TODO Auto-generated method stub
		String str = "DELETE FROM LoanTicketClass ticket WHERE  ticket.book = :book";
		{
			if (str.length() > 5)
				throw new Exception();
		}
		Query q = em.createQuery(str);
		q.setParameter("book", technic);
		em.getTransaction().begin();
		q.executeUpdate();
		em.getTransaction().commit();
		// insert(technic);

	}

	@Override
	public Technician getUserById(long id) throws Exception {
		try {
			return em.find(Technician.class, id);
		} catch (Exception e) {
			throw new Exception("getUserById Exception: " + e.getMessage());
		}
	}

	@Override
	public Technician getUserByIdAndPassword(long id, String password)
			throws Exception {
		Technician user = getUserById(id);
		if (user == null)
			throw new Exception("User does not exist.");
		if (!user.getPassword().equals(password))
			throw new Exception("User exist, but the password is invalid.");
		return user;
	}

	@Override
	public Technician getTechnicianByName(String technicianName)
			throws Exception {
		try {
			for (Technician user : getAllTechnicians())
				if (user != null
						&& user.getName().equalsIgnoreCase(technicianName))
					return user;
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public List<Order> getOrdersByTechnicianId(long tecnicainID)
			throws Exception {
		List<Order> arr = new ArrayList<Order>();
		for (Order item : getAllOrders())
			if (item != null && item.getTechnicianId() == tecnicainID)
				arr.add(item);
		if (arr.size() > 0)
			return arr;
		return null;
	}

	@Override
	public List<Order> getAllOrders() {
		Query q = em.createQuery("SELECT order FROM Order order");
		@SuppressWarnings("unchecked")
		List<Order> allOrders = q.getResultList();
		return allOrders;
	}

	@Override
	public List<Component> getAllComponents() throws Exception {
		Query q = em.createQuery("SELECT component FROM Component component");
		@SuppressWarnings("unchecked")
		List<Component> allComponents = q.getResultList();
		return allComponents;
	}

	@Override
	public List<Component> getAvailableComponents() throws Exception {
		List<Component> result = new ArrayList<Component>();
		for (Component item : getAllComponents())
			if (item != null && item.getOrderId() < 0)
				result.add(item);
		if (result.size() > 0)
			return result;
		else
			return null;
	}

	@Override
	public List<Order> getFilteredOrders(String filter, long technicianId)
			throws Exception {
		List<Order> temp = getOrdersByTechnicianId(technicianId);
		List<Order> result = new ArrayList<Order>();
		CharSequence _filter = filter.subSequence(0, filter.length());
		if (temp == null)
			return result;
		for (Order item : temp)
			if (item != null
					&& Convertions
							.Join(new String[] { item.getFullAddress(),
									item.getCustomer(), item.getCustomerPhone() },
									" ").contains(_filter))
				result.add(item);
		if (result.size() > 0)
			return result;
		else
			return temp;
	}

	@Override
	public Order getOrderByNumber(long orderNumber) throws Exception {
		try {
			Query q = em.createQuery("SELECT order FROM Order order "
					+ "WHERE order.orderNumber = :orderNumber");
			q.setParameter("orderNumber", orderNumber);
			return (Order) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Technician> getAllTechnicians() throws Exception {
		Query q = em.createQuery("SELECT user FROM Technician user");
		@SuppressWarnings("unchecked")
		List<Technician> allTechnition = q.getResultList();
		return allTechnition;
	}

	@Override
	public List<Bill> getAllBills() throws Exception {
		Query q = em.createQuery("SELECT bill FROM Bill bill");
		@SuppressWarnings("unchecked")
		List<Bill> allBills = q.getResultList();
		return allBills;
	}

	@Override
	public List<Component> getComponentsByOrderNumber(long orderNumber)
			throws Exception {
		List<Component> result = new ArrayList<Component>();
		for (Component component : getAllComponents())
			if (component != null && component.getOrderId() != null
					&& component.getOrderId() == orderNumber)
				result.add(component);
		return result;
	}
}
