<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>form1</title>
</head>
<%
	ArrayList<String[]> result =
		(ArrayList<String[]>)request.getAttribute("result");
%>

<body>
	<table>

		<% for (String[] ss : result){ %>
		<option VALUE="<%= ss[1] %>>">
			<%=ss[0] %>
		</option>
		<%} %>

	</table>
</body>
</html>