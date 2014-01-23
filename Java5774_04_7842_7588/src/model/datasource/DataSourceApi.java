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
	public void addTechnician(androidBE.Technician AndroidTechnician) throws Exception {
		// TODO check
		Technician googleTechnician = Adapter.toGoogle(AndroidTechnician);
		myApi.addTechnician(googleTechnician).execute();
	}

	@Override
	public void addOrder(androidBE.Order AndroidOrder) throws Exception {
		// TODO check
		Order googleOrder = Adapter.toGoogle(AndroidOrder);
		myApi.addOrder(googleOrder).execute();
	}

	@Override
	public void addComponent(androidBE.Component AndroidComponent) throws Exception {
		// TODO check
		Component googleComponent = Adapter.toGoogle(AndroidComponent);
		myApi.addComponent(googleComponent).execute();
	}

	@Override
	public void addBill(androidBE.Bill AndroidBill) throws Exception {
		// TODO check
		Bill googleBill = Adapter.toGoogle(AndroidBill);
		myApi.addBill(googleBill).execute();
	}

	@Override
	public void deleteTechnician(androidBE.Technician book) throws Exception {
		throw new Exception("not implemented");
	}

	@Override
	public androidBE.Technician getUserByIdAndPassword(Long id, String password)
			throws Exception {
		// TODO check
		androidBE.Technician technician = Adapter.fromGoogle(myApi
				.getUserByIdAndPassword(id, password).execute());
		return technician;
	}

	@Override
	public androidBE.Technician getTechnicianByName(String technicianName)
			throws Exception {
		// TODO check
		androidBE.Technician technician = Adapter.fromGoogle(myApi
				.getTechnicianByName(technicianName).execute());
		return technician;
	}

	@Override
	public ArrayList<androidBE.Order> getOrdersByTechnicianId(Long technicianID)
			throws Exception {
		// TODO check
		ArrayList<androidBE.Order> orderList = Adapter.fromGoogle(myApi
				.getOrdersByTechnicianId(technicianID).execute().getItems());
		return orderList;
	}

	@Override
	public ArrayList<androidBE.Order> getAllOrders() throws Exception {
		throw new Exception("not implemented");
	}

	@Override
	public ArrayList<androidBE.Component> getAllComponents() throws Exception {
		throw new Exception("not implemented");
	}

	@Override
	public ArrayList<androidBE.Component> getAvailableComponent() throws Exception {
		// TODO check
		ArrayList<androidBE.Component> components = Adapter.fromGoogle(myApi
				.getAvailableComponents().execute().getItems());
		return components;
	}

	@Override
	public ArrayList<androidBE.Order> getFilteredOrders(String city, Long id)
			throws Exception {
		// TODO check
		ArrayList<androidBE.Order> orders = Adapter.fromGoogle(myApi
				.getFilteredOrders(city, id).execute().getItems());
		return orders;
	}

	@Override
	public androidBE.Order getOrderByNumber(Long orderNumber) throws Exception {
		// TODO check
		return Adapter
				.fromGoogle(myApi.getOrderByNumber(orderNumber).execute());
	}

	@Override
	public androidBE.Bill getBillById(Long billId) throws Exception {
		// TODO check
		List<Bill> bills = myApi.getAllBills().execute().getItems();
		if (bills != null)
			for (Bill bill : bills)
				if (bill.getOrderID() == billId)
					return Adapter.fromGoogle(bill);
		return null;
	}

	@Override
	public androidBE.Technician getUserById(Long technicianId) throws Exception {
		// TODO check
		return Adapter.fromGoogle(myApi.getUserById(technicianId).execute());
	}

	@Override
	public List<androidBE.Component> getComponentsByOrderNumber(long orderNumber)
			throws Exception {
		// TODO check
		return Adapter.fromGoogle(myApi.getComponentsByOrderNumber(orderNumber)
				.execute().getItems());
	}

	@Override
	public void updateTechnician(androidBE.Technician technician) throws Exception {
		myApi.updateTechnician(Adapter.toGoogle(technician)).execute();
	}

	@Override
	public void updateOrder(androidBE.Order order) throws Exception {
		myApi.updateOrder(Adapter.toGoogle(order)).execute();
	}

	@Override
	public void updateComponent(androidBE.Component component) throws Exception {
		myApi.updateComponent(Adapter.toGoogle(component)).execute();
	}

	@Override
	public void updateBill(androidBE.Bill bill) throws Exception {
		myApi.updateBill(Adapter.toGoogle(bill)).execute();
	}
}
