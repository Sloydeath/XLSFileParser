<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Список файлов</title>
</head>
<body>


<h3>Список файлов</h3>

<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>ID</th>
        <th>Полное имя файла</th>
    </tr>
    <c:forEach items="${fileList}" var="file" >
        <tr>
            <td>${file.id}</td>
            <td>${file.fileName}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>