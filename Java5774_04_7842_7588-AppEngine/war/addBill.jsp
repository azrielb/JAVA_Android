<%@page import="appEngineBE.Bill"%>
<%@page import="model.backend.Factory"%>
<%@page import="model.backend.Backend"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<title>���� �����</title>
</head>
<body>
	<p>
		<a href="./">���� ��� ����</a>
	</p>
	<form>
		<h1 align="right">���� �����</h1>
		<table align="right">
			<tr>
				<td><input name="orderId"></td>
				<td>���� �����</td>
			</tr>
			<tr>
				<td><input name="cost"></td>
				<td>���� ����</td>
			</tr>
		</table>
		<br>
		<div align="right">
			<input type="submit" value="�����">
		</div>
		<%
			String orderId = request.getParameter("orderId");
			String cost = request.getParameter("cost");

			if (orderId != null && cost != null) {
				try {
					Bill bill = new Bill(Long.parseLong(orderId),
							Float.parseFloat(cost));
					Factory.getInstance().addBill(bill);
				} catch (Exception e) {
					out.println("<p>" + e.getMessage() + "</p>");
				}
			}
		%>
	</form>
</body>
</html>