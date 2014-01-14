<%@page import="BE.Bill"%>
<%@page import="BE.Order"%>
<%@page import="BE.Technician"%>
<%@page import="java.util.List"%>
<%@page import="BE.Component"%>
<%@page import="model.backend.Factory"%>
<%@page import="model.backend.Backend"%>
<%@page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html dir="ltr">
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<title>Test functions in our back-end</title>
</head>
<body dir="rtl">
	<p>
		<a href="./">חזרה לדף הבית</a>
	</p>
	<p>
		<a href="addBill.jsp">צור חשבון</a> <br>
	</p>
	<%
		String componentToInverse = request
				.getParameter("componentToInverse");
		if (componentToInverse != null) {
			for (Component component : Factory.getInstance()
					.getAllComponents()) {
				if (component.getSerialNumber().equalsIgnoreCase(
						componentToInverse)) {
					component.setOrderId(-component.getOrderId());
					try {
						Factory.getInstance().updateComponent(component);
						out.println("<p>הרכיב עודכן בהצלחה.</p>");
					} catch (Exception e) {
						out.println(e.getMessage());
					}
				}
			}
		}
	%>
	<table>
		<tr>
			<td>
				<table border="1px" bordercolor="blue" rules="all" cellpadding="5px">
					<thead>
						<tr>
							<th colspan="10">רשימת רכיבים זמינים</th>
						</tr>
						<tr>
							<th>שם רכיב</th>
							<th>מחיר</th>
							<th>מספר סידורי</th>
							<th>תיאור</th>
						</tr>
					</thead>
					<tbody>
						<%
							List<Component> compon = Factory.getInstance()
									.getAvailableComponents();
							if (compon != null)
								for (Component component : compon) {
						%>
						<tr>
							<td><%=component.getName()%></td>
							<td><%=component.getCost()%></td>
							<td><%=component.getSerialNumber()%></td>
							<td><%=component.getDescription()%></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</td>
			<td>
				<table border="1px" bordercolor="blue" rules="all" cellpadding="5px">
					<thead>
						<tr>
							<th colspan="10">רשימת כל הרכיבים</th>
						</tr>
						<tr>
							<th>שם רכיב</th>
							<th>מחיר</th>
							<th>מספר סידורי</th>
							<th>תיאור</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<%
							compon = Factory.getInstance().getAllComponents();
							for (Component component : compon) {
						%>
						<tr>
							<td><%=component.getName()%></td>
							<td><%=component.getCost()%></td>
							<td><%=component.getSerialNumber()%></td>
							<td><%=component.getDescription()%></td>
							<td><a
								href="test_functions.jsp?componentToInverse=<%=component.getSerialNumber()%>">החלף
									מצב זמינות</a></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table border="1px" bordercolor="green" rules="all"
					cellpadding="5px">
					<thead>
						<tr>
							<th colspan="10">רשימת ההזמנות, מקובצת לפי טכנאי</th>
						</tr>
						<tr>
							<th>כתובת</th>
							<th>שם הלקוח</th>
							<th>טלפון הלקוח</th>
							<th>תאריך יצירה</th>
							<th>טכנאי</th>
						</tr>
					</thead>
					<tbody>
						<%
							List<Technician> allTech = Factory.getInstance()
									.getAllTechnicians();
							if (allTech != null)
								for (Technician tech : allTech) {
									String techName = null;
									List<Order> orders = Factory.getInstance()
											.getOrdersByTechnicianId(tech.getId());
									if (orders != null)
										for (Order ord : orders) {
						%><tr>
							<td><%=ord.getFullAddress()%></td>
							<td><%=ord.getCustomer()%></td>
							<td><%=ord.getCustomerPhone()%></td>
							<td><%=ord.getCreateDate()%></td>
							<%
								if (techName == null) {
													techName = tech.getName();
													out.println(String.format(
															"<td rowspan='%s'>%s</td>",
															orders.size(), techName));
												}
							%>
						</tr>
						<%
							}
								}
						%>
					</tbody>
				</table>
			</td>
			<td>
				<table border="1px" bordercolor="green" rules="all"
					cellpadding="5px">
					<thead>
						<tr>
							<th colspan="10">רשימת ההזמנות</th>
						</tr>
						<tr>
							<th>כתובת</th>
							<th>שם הלקוח</th>
							<th>טלפון הלקוח</th>
							<th>תאריך יצירה</th>
							<th>טכנאי</th>
						</tr>
					</thead>
					<tbody>
						<%
							List<Order> orders = Factory.getInstance().getAllOrders();
							if (orders != null)
								for (Order ord : orders) {
									Technician tech = Factory.getInstance().getUserById(
											ord.getTechnicianId());
						%><tr>
							<td><%=ord.getFullAddress()%></td>
							<td><%=ord.getCustomer()%></td>
							<td><%=ord.getCustomerPhone()%></td>
							<td><%=ord.getCreateDate()%></td>
							<td><%=tech != null ? tech.getName() : "לא קיים"%></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table>
					<thead>
						<tr>
							<th colspan="10">רשימת כל החשבוניות</th>
						</tr>
						<tr>
							<th>מספר חשבונית</th>
							<th>מחיר</th>
						</tr>
					</thead>
					<tbody>
						<%
							List<Bill> bills = Factory.getInstance().getAllBills();
							if (bills != null)
								for (Bill bill : bills) {
						%>
						<tr>
							<td><%=bill.getOrderID()%></td>
							<td><%=bill.getCost()%></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</td>
			<td align="center">
				<form>
					<fieldset>
						<legend>סינון הזמנות</legend>
						<%
							String techName = request.getParameter("techName");
							Technician tech = null;
							String address = request.getParameter("address");
							List<Order> filteredOrders = null;
							if (address == null)
								address = "";
							if (techName == null)
								techName = "";
							else if (!techName.isEmpty())
								tech = Factory.getInstance().getTechnicianByName(techName);
							if (tech != null)
								filteredOrders = Factory.getInstance().getFilteredOrders(
										address, tech.getId());
						%>
						<p>
							<label for="techName">שם טכנאי</label> <input id="techName"
								name="techName" value="<%=techName%>" />
						</p>
						<p>
							<label for="address">פילטר</label> <input id="address"
								name="address" value="<%=address%>" />
						</p>
						<p>
							<input type="submit" />
						</p>
						<%
							if (filteredOrders != null) {
						%>
						<table border="1px" bordercolor="green" rules="all"
							cellpadding="5px">
							<thead>
								<tr>
									<th colspan="10">רשימת ההזמנות המסוננת</th>
								</tr>
								<tr>
									<th>כתובת</th>
									<th>שם הלקוח</th>
									<th>טלפון הלקוח</th>
									<th>תאריך יצירה</th>
								</tr>
							</thead>
							<tbody>
								<%
									for (Order ord : filteredOrders) {
								%><tr>
									<td><%=ord.getFullAddress()%></td>
									<td><%=ord.getCustomer()%></td>
									<td><%=ord.getCustomerPhone()%></td>
									<td><%=ord.getCreateDate()%></td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
						<%
							}
						%>
					</fieldset>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>