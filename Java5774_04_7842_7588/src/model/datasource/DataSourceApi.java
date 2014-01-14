package model.datasource;

import java.util.ArrayList;
import java.util.List;

import model.backend.Backend;

import com.appspot.android_technician.serverApi.ServerApi;
import com.appspot.android_technician.serverApi.model.Bill;
import com.appspot.android_technician.serverApi.model.Component;
import com.appspot.android_technician.serverApi.model.Order;
import com.appspot.android_technician.serverApi.model.Technician;
import com.example.java5774_04_7842_7588.CloudEndpointUtils;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson.JacksonFactory;

import conversions.Adapter;

public class DataSourceApi implements Backend {

	private ServerApi myApi = null;

	public DataSourceApi() {
		ServerApi.Builder endpointBuilder = new ServerApi.Builder(
				AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
				new HttpRequestInitializer() {
					public void initialize(HttpRequest httpRequest) {
					}
				});

		myApi = CloudEndpointUtils.updateBuilder(endpointBuilder).build();
	}

	@Override
	public void addTechnician(BE.Technician AndroidTechnician) throws Exception {
		// TODO check
		Technician googleTechnician = Adapter.toGoogle(AndroidTechnician);
		myApi.addTechnician(googleTechnician).execute();
	}

	@Override
	public void addOrder(BE.Order AndroidOrder) throws Exception {
		// TODO check
		Order googleOrder = Adapter.toGoogle(AndroidOrder);
		myApi.addOrder(googleOrder).execute();
	}

	@Override
	public void addComponent(BE.Component AndroidComponent) throws Exception {
		// TODO check
		Component googleComponent = Adapter.toGoogle(AndroidComponent);
		myApi.addComponent(googleComponent).execute();
	}

	@Override
	public void addBill(BE.Bill AndroidBill) throws Exception {
		// TODO check
		Bill googleBill = Adapter.toGoogle(AndroidBill);
		myApi.addBill(googleBill).execute();
	}

	@Override
	public void deleteTechnician(BE.Technician book) throws Exception {
		throw new Exception("not implemented");
	}

	@Override
	public BE.Technician getUserByIdAndPassword(Long id, String password)
			throws Exception {
		// TODO check
		BE.Technician technician = Adapter.fromGoogle(myApi
				.getUserByIdAndPassword(id, password).execute());
		return technician;
	}

	@Override
	public BE.Technician getTechnicianByName(String technicianName)
			throws Exception {
		// TODO check
		BE.Technician technician = Adapter.fromGoogle(myApi
				.getTechnicianByName(technicianName).execute());
		return technician;
	}

	@Override
	public ArrayList<BE.Order> getOrdersByTechnicianId(Long technicianID)
			throws Exception {
		// TODO check
		ArrayList<BE.Order> orderList = new ArrayList<BE.Order>();
		List<Order> orders = (myApi.getOrdersByTechnicianId(technicianID)
				.execute()).getItems();
		Adapter.fromGoogle(orders, orderList);
		return orderList;
	}

	@Override
	public ArrayList<BE.Order> getAllOrders() throws Exception {
		throw new Exception("not implemented");
	}

	@Override
	public ArrayList<BE.Component> getAllComponents() throws Exception {
		throw new Exception("not implemented");
	}

	@Override
	public ArrayList<BE.Component> getAvailableComponent() throws Exception {
		// TODO check
		ArrayList<BE.Component> components = new ArrayList<BE.Component>();
		Adapter.fromGoogle(myApi.getAvailableComponents().execute().getItems(),
				components);
		return components;
	}

	@Override
	public ArrayList<BE.Order> getFilteredOrders(String city, Long id)
			throws Exception {
		// TODO check
		ArrayList<BE.Order> orders = new ArrayList<BE.Order>();
		Adapter.fromGoogle(myApi.getFilteredOrders(city, id).execute()
				.getItems(), orders);
		return orders;
	}

	@Override
	public BE.Order getOrderByNumber(Long orderNumber) throws Exception {
		// TODO check
		return Adapter
				.fromGoogle(myApi.getOrderByNumber(orderNumber).execute());
	}

	@Override
	public BE.Bill getBillById(Long billId) throws Exception {
		// TODO check
		List<Bill> bills = myApi.getAllBills().execute().getItems();
		for (Bill bill : bills)
			if (bill.getOrderID() == billId)
				return Adapter.fromGoogle(bill);
		return null;
	}

	@Override
	public BE.Technician getUserById(Long technicianId) throws Exception {
		// TODO check
		return Adapter.fromGoogle(myApi.getUserById(technicianId).execute());
	}

	@Override
	public List<BE.Component> getComponentsByOrderNumber(long orderNumber)
			throws Exception {
		// TODO check
		return Adapter.fromGoogle(myApi.getComponentsByOrderNumber(orderNumber)
				.execute());
	}

}
