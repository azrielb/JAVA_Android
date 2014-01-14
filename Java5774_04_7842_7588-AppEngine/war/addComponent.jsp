<%@page import="BE.Component"%>
<%@page import="model.backend.Factory"%>
<%@page import="model.backend.Backend"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<title>���� ����</title>
</head>
<body>
	<p>
		<a href="./">���� ��� ����</a>
	</p>
	<form>
		<h1 align="right">���� ����</h1>
		<table>
			<tr>
				<td><input name="itemName"></td>
				<td>�� ����</td>
			</tr>
			<tr>
				<td><input name="cost"></td>
				<td>����</td>
			</tr>
			<tr>
				<td><input name="serialNumber"></td>
				<td>���� ������</td>
			</tr>
			<tr>
				<td><input name="description"></td>
				<td>�����</td>
			</tr>
		</table>
		<br>
		<div align="right">
			<input type="submit" value="�����">
		</div>
		<%
			String itemName = request.getParameter("itemName");
			String strCost = request.getParameter("cost");
			String serialNumber = request.getParameter("serialNumber");
			String description = request.getParameter("description");

			if (itemName != null && serialNumber != null && strCost != null
					&& !itemName.isEmpty() && !serialNumber.isEmpty()
					&& !strCost.isEmpty()) {
				try {
					float cost = Float.parseFloat(strCost);
					if (cost <= 0)
						throw new Exception("���� �� ���� ����� �����!");
					Component compon = new Component(itemName, cost,
							serialNumber);
					if (description != null && !description.isEmpty())
						compon.setDescription(description);
					Factory.getInstance().addComponent(compon);
					out.println("<p>���� ���� ������!</p>");
				} catch (Exception e) {
					out.println("<p>Exception: " + e.getMessage() + "</p>");
				}
			}
		%>
	</form>
</body>
</html>