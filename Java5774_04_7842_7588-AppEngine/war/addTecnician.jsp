<%@page import="model.backend.DBbackend"%>
<%@page import="java.util.Date"%>
<%@page import="BE.Technician"%>
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
				<td><input name="firstName"></td>
				<td>�� ����</td>
			</tr>
			<tr>
				<td><input name="lastName"></td>
				<td>�� �����</td>
			</tr>
			<tr>
				<td><input name="password"></td>
				<td>�����</td>
			</tr>
			<tr>
				<td><input name="email"></td>
				<td>������</td>
			</tr>
			<tr>
				<td><input name="id"></td>
				<td>�.�</td>
			</tr>
			<tr>
				<td><input name="cellphone"></td>
				<td>�����</td>
			</tr>
		</table>
		<br>
		<div align="right">
			<input type="submit" value="�����">
		</div>
		<%
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String id = request.getParameter("id");
			String cellphone = request.getParameter("cellphone");

			if (firstName != null && lastName != null && password != null
					&& id != null && cellphone != null) {
				Technician tecnic = new Technician(firstName, lastName,
						password, email, Long.parseLong(id));
				tecnic.setCellphone(cellphone);
				try {
					Factory.getInstance().addTechnician(tecnic);
		%><p>User has been created successfully.</p><%
				} catch (Exception e) {
					out.println(e.getMessage());
				}
			}
		%>
	</form>
</body>
</html>