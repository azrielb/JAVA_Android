package conversions;

import java.util.ArrayList;
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
	public static <S extends toGoogleConvertions> S fromGoogle(GenericJson item) {
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

	public static <S extends toGoogleConvertions> ArrayList<S> fromGoogle(
			List<? extends GenericJson> src) throws NullPointerException {
		if (src == null)
			return null;
		ArrayList<S> dst = new ArrayList<S>();
		for (GenericJson item : src) {
			S obj = fromGoogle(item);
			dst.add(obj);
		}
		return dst;
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
	public static <T extends GenericJson> T toGoogle(toGoogleConvertions item) {
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

	public static <T extends GenericJson> List<T> toGoogle(
			List<? extends toGoogleConvertions> src) throws NullPointerException {
		if (src == null)
			return null;
		List<T> dst = new ArrayList<T>();
		for (toGoogleConvertions item : src) {
			T obj = toGoogle(item);
			dst.add(obj);
		}
		return dst;
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
