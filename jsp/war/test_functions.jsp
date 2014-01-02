<%@page import="java.util.List"%>
<%@page import="BE.Component"%>
<%@page import="model.backend.BackendFactory"%>
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
			List<Component> compon = BackendFactory.getInstance().getAvailableComponent();
				for (Component component : compon) {
			%>
			<tr>
				<td><%=component.getName() %></td>
				<td><%=component.getCost() %></td>
				<td><%=component.getSerialNumber() %></td>
				<td><%=component.getDescription() %></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
</body>
</html>