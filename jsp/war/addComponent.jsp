<%@page import="BE.Component"%>
<%@page import="model.backend.BackendFactory"%>
<%@page import="model.backend.Backend"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<title>הוסף רכיב</title>
</head>
<body>
<p><a href="./">חזרה לדף הבית</a></p>
	<form>
		<h1 align="right">הוסף רכיב</h1>
		<table>
			<tr>
				<td><input name="itemName"></td>
				<td>שם רכיב</td>
			</tr>
			<tr>
				<td><input name="cost"></td>
				<td>מחיר</td>
			</tr>
			<tr>
				<td><input name="serialNumber"></td>
				<td>מספר סידורי</td>
			</tr>
			<tr>
				<td><input name="description"></td>
				<td>תיאור</td>
			</tr>
		</table>
		<br>
		<div align="right">
			<input type="submit" value="אישור">
		</div>
		<%
			String itemName = request.getParameter("itemName");
			String cost = request.getParameter("cost");
			String serialNumber = request.getParameter("serialNumber");
			String description = request.getParameter("description");

			if (itemName != null && serialNumber != null && serialNumber != null) {
				Component compon = new Component(itemName, Float.parseFloat(cost), serialNumber);
				BackendFactory.getInstance().addComponent(compon);
			}
		%>
	</form>
</body>
</html>