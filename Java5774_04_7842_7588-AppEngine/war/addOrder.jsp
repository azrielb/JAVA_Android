<%@page import="java.util.Date"%>
<%@page import="BE.Order"%>
<%@page import="BE.Technician"%>
<%@page import="model.backend.Factory"%>
<%@page import="model.backend.Backend"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html dir="ltr">
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<title>���� �����</title>
</head>
<body dir="rtl">
	<p>
		<a href="./">���� ��� ����</a>
	</p>
	<%
		if (request.getParameter("add") != null) {
			try {
				String orderId = request.getParameter("orderId");
				String addres = request.getParameter("addres");
				String city = request.getParameter("city");
				String customerName = request.getParameter("customerName");
				String customerPhone = request
						.getParameter("customerPhone");
				String technicianName = request
						.getParameter("technicianName");

				if (orderId == "" || addres == "" || city == ""
						|| customerName == "" || customerPhone == ""
						|| technicianName == "") {
					throw new Exception("��� ����� ���");
				}
				Order order = new Order(Long.parseLong(orderId), city,
						customerName, new Date(), customerPhone);
				order.setTechnicianId(Factory.getInstance()
						.getTechnicianByName(technicianName).getId());
				order.setAddres(addres);
				Factory.getInstance().addOrder(order);
	%>
	<h1>������ ����� ������!</h1>
	<table>
		<tr>
			<td>���� �����</td>
			<td><%=orderId%></td>
		</tr>
		<tr>
			<td>�����</td>
			<td><%=addres%></td>
		</tr>
		<tr>
			<td>���</td>
			<td><%=city%></td>
		</tr>
		<tr>
			<td>�� ����</td>
			<td><%=customerName%></td>
		</tr>
		<tr>
			<td>�����</td>
			<td><%=customerPhone%></td>
		</tr>
		<tr>
			<td>�����</td>
			<td><%=technicianName%></td>
		</tr>
	</table>
	<%
		} catch (Exception e) {
				out.println("<p style='color: red;'><b>" + e.getMessage()
						+ "</b></p>");
			}
		}
	%>
	<div>
		<form method="post">
			<h1>���� �����</h1>
			<table>
				<tr>
					<td>���� �����</td>
					<td><input name="orderId"></td>
				</tr>
				<tr>
					<td>�����</td>
					<td><input name="addres"></td>
				</tr>
				<tr>
					<td>���</td>
					<td><input name="city"></td>
				</tr>
				<tr>
					<td>�� ����</td>
					<td><input name="customerName"></td>
				</tr>
				<tr>
					<td>�����</td>
					<td><input name="customerPhone"></td>
				</tr>
				<tr>
					<td>�����</td>
					<td><select name="technicianName">
							<%
								for (Technician t : Factory.getInstance().getAllTechnicians()) {
							%>
							<option value="<%=t.getName()%>"><%=t.getName()%></option>
							<%
								}
							%>
					</select></td>
				</tr>

				<tr>
					<td colspan="2" align="center"><input type="submit" name="add"
						value="����"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>