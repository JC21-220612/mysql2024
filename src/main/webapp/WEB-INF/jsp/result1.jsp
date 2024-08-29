<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>result1</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>Item ID</th>
			<th>Item Name</th>
			<th>Price</th>
		</tr>
		<%
			ArrayList<String[]> result = (ArrayList<String[]>) request.getAttribute("result");
			
			for (String[] ss : result) {
		%>
		<tr>
			<td><%= ss[1] %></td>
			<td><%= ss[0] %></td>
			<td><%= ss[2] %></td>
		</tr>
		<%
			}
		%>
		<tr>
	
	</table>
</body>
</html>
