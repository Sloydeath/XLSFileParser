<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 14.12.2020
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>EY</title>
</head>

<body>
<h3>Выберите действие</h3>
<a href="${pageContext.request.contextPath}/download">Загрузка файла в базу данных</a> <br>
<a href="${pageContext.request.contextPath}/files">Список загруженных файлов</a> <br>
<a href="${pageContext.request.contextPath}/content">Вывод содержимого файла на экран</a> <br>

</body>
</html>
