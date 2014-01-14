package conversions;

import java.util.Date;
import java.util.List;

import BE.Order.statuses;

import com.appspot.android_technician.serverApi.model.Bill;
import com.appspot.android_technician.serverApi.model.Component;
import com.appspot.android_technician.serverApi.model.Order;
import com.appspot.android_technician.serverApi.model.Technician;
import com.google.api.client.json.GenericJson;

public class Adapter {
	@SuppressWarnings("unchecked")
	public static <S extends toGoogleConvertions, T extends GenericJson> S fromGoogle(
			T item) {
		if (item instanceof Bill) {
			return (S) fromGoogle((Bill) item);
		}
		if (item instanceof Component) {
			return (S) fromGoogle((Component) item);
		}
		if (item instanceof Order) {
			return (S) fromGoogle((Order) item);
		}
		if (item instanceof Technician) {
			return (S) fromGoogle((Technician) item);
		}
		return null;
	}

	public static <S extends toGoogleConvertions, T extends GenericJson> 
	void fromGoogle(List<T> src, List<S> dst) throws NullPointerException {
		if (src == null || dst == null) {
			throw new NullPointerException();
		}
		dst.clear();
		for (T item : src) {
			S obj = fromGoogle(item);
			dst.add(obj);
		}
	}

	public static BE.Bill fromGoogle(Bill GoogleBill) {
		BE.Bill AndroidBill = new BE.Bill(GoogleBill.getOrderID(),
				GoogleBill.getCost());
		AndroidBill.setSignatureFilePath(GoogleBill.getSignatureFilePath());
		return AndroidBill;
	}

	public static BE.Component fromGoogle(Component GoogleComponent) {
		BE.Component AndroidComponent = new BE.Component(
				GoogleComponent.getName(), GoogleComponent.getCost(),
				GoogleComponent.getSerialNumber());
		AndroidComponent.setDescription(GoogleComponent.getDescription());
		AndroidComponent.setOrderId(GoogleComponent.getOrderId());
		return AndroidComponent;
	}

	public static BE.Order fromGoogle(Order GoogleOrder) {
		BE.Order AndroidOrder = new BE.Order(GoogleOrder.getOrderNumber(),
				GoogleOrder.getCity(), GoogleOrder.getCustomer(), new Date(
						GoogleOrder.getCreateDate().getValue()),
				GoogleOrder.getCustomerPhone());
		AndroidOrder.setAddres(GoogleOrder.getAddres());
		AndroidOrder.setBillId(GoogleOrder.getBillId());
		AndroidOrder.setDetailes(GoogleOrder.getDetailes());
		AndroidOrder.setFinish(GoogleOrder.getFinish());
		AndroidOrder.setStart(GoogleOrder.getStart());
		AndroidOrder.setStatus(statuses.valueOf(GoogleOrder.getStatus()));
		AndroidOrder.setTechnicianId(GoogleOrder.getTechnicianId());
		AndroidOrder.setTechnicianComments(GoogleOrder.getTechnicianComments());
		AndroidOrder.setTitle(GoogleOrder.getTitle());
		return AndroidOrder;
	}

	public static BE.Technician fromGoogle(Technician GoogleTechnician) {
		BE.Technician technician = new BE.Technician(
				GoogleTechnician.getFirstName(),
				GoogleTechnician.getLastName(), GoogleTechnician.getPassword(),
				GoogleTechnician.getEMail(), GoogleTechnician.getId());
		technician.setCellphone(GoogleTechnician.getCellphone());
		return technician;
	}

	@SuppressWarnings("unchecked")
	public static <T extends GenericJson, S extends toGoogleConvertions> T toGoogle(
			S item) {
		if (item instanceof BE.Bill) {
			return (T) toGoogle((BE.Bill) item);
		}
		if (item instanceof BE.Component) {
			return (T) toGoogle((BE.Component) item);
		}
		if (item instanceof BE.Order) {
			return (T) toGoogle((BE.Order) item);
		}
		if (item instanceof BE.Technician) {
			return (T) toGoogle((BE.Technician) item);
		}
		return null;
	}

	public static <T extends GenericJson, S extends toGoogleConvertions> void toGoogle(
			List<S> src, List<T> objects) throws NullPointerException {
		if (src == null || objects == null) {
			throw new NullPointerException();
		}
		for (S item : src) {
			T obj = toGoogle(item);
			objects.add(obj);
		}
	}

	public static Bill toGoogle(BE.Bill AndroidBill) {
		Bill GoogleBill = new Bill();
		GoogleBill.setOrderID(AndroidBill.getOrderID());
		GoogleBill.setCost(AndroidBill.getCost());
		GoogleBill.setSignatureFilePath(AndroidBill.getSignatureFilePath());
		return GoogleBill;
	}

	public static Component toGoogle(BE.Component AndroidComponent) {
		Component GoogleComponent = new Component();
		GoogleComponent.setCost(AndroidComponent.getCost());
		GoogleComponent.setDescription(AndroidComponent.getDescription());
		GoogleComponent.setOrderId(AndroidComponent.getOrderId());
		GoogleComponent.setName(AndroidComponent.getName());
		GoogleComponent.setSerialNumber(AndroidComponent.getSerialNumber());
		return GoogleComponent;
	}

	public static Order toGoogle(BE.Order AndroidOrder) {
		Order Google_Order = new Order();
		Google_Order.setOrderNumber(AndroidOrder.getOrderNumber());
		Google_Order.setCity(AndroidOrder.getCity());
		Google_Order.setCustomer(AndroidOrder.getCustomer());
		Google_Order.setCreateDate(new com.google.api.client.util.DateTime(
				AndroidOrder.getCreateDate()));
		Google_Order.setCustomerPhone(AndroidOrder.getCustomerPhone());
		Google_Order.setAddres(AndroidOrder.getAddres());
		Google_Order.setBillId(AndroidOrder.getBillId());
		Google_Order.setDetailes(AndroidOrder.getDetailes());
		Google_Order.setFinish(AndroidOrder.getFinish());
		Google_Order.setStart(AndroidOrder.getStart());
		Google_Order.setStatus(AndroidOrder.getStatus().name());
		Google_Order.setTechnicianId(AndroidOrder.getTechnicianId());
		Google_Order
				.setTechnicianComments(AndroidOrder.getTechnicianComments());
		Google_Order.setTitle(AndroidOrder.getTitle());
		return Google_Order;
	}

	public static Technician toGoogle(BE.Technician AndroidTechnician) {
		Technician GoogleTechnician = new Technician();
		GoogleTechnician.setCellphone(AndroidTechnician.getCellphone());
		GoogleTechnician.setEMail(AndroidTechnician.geteMail());
		GoogleTechnician.setFirstName(AndroidTechnician.getFirstName());
		GoogleTechnician.setId(AndroidTechnician.getId());
		GoogleTechnician.setLastName(AndroidTechnician.getLastName());
		GoogleTechnician.setPassword(AndroidTechnician.getPassword());
		return GoogleTechnician;
	}
}
