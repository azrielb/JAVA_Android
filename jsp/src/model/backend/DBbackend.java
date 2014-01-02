package model.backend;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import BE.Bill;
import BE.Component;
import BE.Order;
import BE.Technician;

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
	public void addOrder(Order order) throws Exception {
		Order existing = null;
		try {
			existing = getOrderByNumber((int) (long) (order.getOrderNumber()));
		} catch (Exception e) {
		}
		if (existing != null)
			throw new Exception("Order " + order.getOrderNumber() + " exists!");
		insert(order);
	}

	@Override
	public void addComponent(Component component) throws Exception {
		for (Component c : getAllComponent())
			if (c.getSerialNumber() == component.getSerialNumber())
				throw new Exception("Component " + component.getSerialNumber()
						+ " exists!");
		insert(component);
	}

	@Override
	public void addBill(Bill bill) throws Exception {
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

	public Technician getUserById(Long id) throws Exception {
		return em.find(Technician.class, id);
	}

	@Override
	public Technician getUserByIdAndPassword(Long id, String password)
			throws Exception {
		Technician user = getUserById(id);
		if (user.getPassword() != password)
			throw new Exception("invalid password");
		return user;
	}

	@Override
	public Technician getTechnicianByName(String technicianName)
			throws Exception {
		try {
			for (Technician user : getAllTechnichians())
				if (user.getName().equalsIgnoreCase(technicianName))
					return user;
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public List<Order> getOrdersByTechnicianId(int tecnicainID) {
		// TODO check it
		List<Order> arr =new ArrayList<Order>();
		for (Order item : getAllOrders() )
			if (item.getTechnicianID()== (tecnicainID))
				arr.add(item);
		if (arr.size() > 0)
			return arr;
		return null;
	}

	@Override
	public List<Order> getAllOrders() {
		// TODO check it
		Query q = em.createQuery("SELECT order FROM Order order");
		@SuppressWarnings("unchecked")
		List<Order> allOrders= q.getResultList();
		return allOrders;
	}

	@Override
	public List<Component> getAllComponent() {
		// TODO check it
		Query q = em.createQuery("SELECT component FROM Component component");
		@SuppressWarnings("unchecked")
		List<Component> allComponents= q.getResultList();
		return allComponents;
	}

	@Override
	public List<Component> getAvailableComponent() {
		// TODO check it
		List<Component> result = new ArrayList<Component>();
		for (Component item : getAllComponent())
			if (item.isExist())
				result.add(item);
		if (result.size() > 0)
			return result;
		else
			return null;
	}

	@Override
	public List<Order> getFilteredOrders(String city, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order getOrderByNumber(int orderNumber) {
		try {
			Query q = em.createQuery("SELECT order FROM Order order "
					+ "WHERE order.orderNumber = :orderNumber");
			q.setParameter("orderNumber", (long) orderNumber);
			return (Order) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Technician> getAllTechnichians() {
		Query q = em.createQuery("SELECT user FROM Technician user");
		@SuppressWarnings("unchecked")
		List<Technician> allTechnition = q.getResultList();
		return allTechnition;
	}
}
