<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Book</title>
</head>
<body>
<form name="separateFields" method="post" action="/Warsztaty5/book/add">
    <input type="text" name="isbn" value="12345678"/>
    <input type="text" name="title" value="Beniowski"/>
    <input type="text" name="author" value="Juliusz Slowacki"/>
    <input type="text" name="publisher" value="Ossolineum"/>
    <input type="text" name="type" value="poematy"/>
    
    <input type="submit"/>
</form>
</body>
</html>