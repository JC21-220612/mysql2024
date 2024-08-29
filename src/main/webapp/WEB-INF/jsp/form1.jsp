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
	ArrayList<String[]> result = (ArrayList<String[]>)request.getAttribute("result");
%>

<body>
	<form method="GET" action="./result1">
	<select name="ID">
	<option value="200">のり弁当</option>
	<option value="201">のりデラックス</option>
	<option value="202">のりからあげ弁当</option>
	<option value="510">とん汁</option>
	</select>
	<input type="submit" value="絞り込む">
	</form>
</body>
</html>