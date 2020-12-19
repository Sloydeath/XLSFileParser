<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="accounts" scope="request" type="java.util.List"/>
<jsp:useBean id="totalSumAccountsByClass" scope="request" type="java.util.Map"/>
<jsp:useBean id="totalSumAccountsGroups" scope="request" type="java.util.Map"/>
<jsp:useBean id="classes" scope="request" type="java.util.List"/>
<jsp:useBean id="accountGroups" scope="request" type="java.util.List"/>
<jsp:useBean id="totalSumAccountsWholeFile" scope="request" type="java.util.List"/>
<jsp:useBean id="fileDescription" scope="request" type="by.ey.secondTask.entity.FileDescription"/>


<html>
<head>
    <title>Информация из файла</title>
</head>

<body>
    <h3>Информация из файла</h3>

    <p>${fileDescription.bankName}</p>
    <p>Оборотная ведомость по балансовым счетам</p>
    <p>${fileDescription.period}</p>
    <p>по банку</p>
    <p>В рублях</p>
    <br><br>

    <table border="1" cellpadding="5" cellspacing="1" >
        <tr>
            <th rowspan="2">Б/сч</th>
            <th colspan="2">Входящее сальдо</th>
            <th colspan="2">Обороты</th>
            <th colspan="2">Исходящее сальдо</th>
        </tr>
        <tr>
            <th>Актив</th>
            <th>Пассив</th>
            <th>Дебет</th>
            <th>Кредит</th>
            <th>Актив</th>
            <th>Пассив</th>
        </tr>

        <c:forEach items = "${classes}" var = "accountClass" >
            <th colspan="7">${accountClass.name}</th>
            <c:forEach items = "${accountGroups}" var = "accountGroup" >
                <%
                    int accountsShowCounter = 0;
                %>
                <c:forEach items = "${accounts}" var = "account" >
                    <c:if test = "${account.accountClass.id == accountClass.id && account.accountGroup.id == accountGroup.id}">
                        <tr>
                            <th>${account.id}</th>
                            <th><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${account.inputBalanceAsset}"/></th>
                            <th><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${account.inputBalanceLiability}"/></th>
                            <th><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${account.debit}"/></th>
                            <th><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${account.credit}"/></th>
                            <th><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${account.outgoingBalanceAsset}"/></th>
                            <th><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${account.getOutgoingBalanceLiability}"/></th>
                        </tr>
                        <%
                            accountsShowCounter++;
                        %>
                    </c:if>
                    <%
                        request.setAttribute("accountsShowCounter", accountsShowCounter);
                    %>
                </c:forEach>
                <c:if test="${accountsShowCounter != 0}">
                    <tr>
                        <th>${accountGroup.id}</th>
                        <th><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${totalSumAccountsGroups.get(accountGroup.id).get(0)}"/></th>
                        <th><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${totalSumAccountsGroups.get(accountGroup.id).get(1)}"/></th>
                        <th><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${totalSumAccountsGroups.get(accountGroup.id).get(2)}"/></th>
                        <th><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${totalSumAccountsGroups.get(accountGroup.id).get(3)}"/></th>
                        <th><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${totalSumAccountsGroups.get(accountGroup.id).get(4)}"/></th>
                        <th><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${totalSumAccountsGroups.get(accountGroup.id).get(5)}"/></th>
                    </tr>
                </c:if>
            </c:forEach>
            <tr>
                <th>ПО КЛАССУ</th>
                <th><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${totalSumAccountsByClass.get(accountClass.id).get(0)}"/></th>
                <th><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${totalSumAccountsByClass.get(accountClass.id).get(1)}"/></th>
                <th><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${totalSumAccountsByClass.get(accountClass.id).get(2)}"/></th>
                <th><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${totalSumAccountsByClass.get(accountClass.id).get(3)}"/></th>
                <th><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${totalSumAccountsByClass.get(accountClass.id).get(4)}"/></th>
                <th><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${totalSumAccountsByClass.get(accountClass.id).get(5)}"/></th>
            </tr>
        </c:forEach>
        <tr>
            <th>БАЛАНС</th>
            <th><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${totalSumAccountsWholeFile.get(0)}"/></th>
            <th><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${totalSumAccountsWholeFile.get(1)}"/></th>
            <th><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${totalSumAccountsWholeFile.get(2)}"/></th>
            <th><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${totalSumAccountsWholeFile.get(3)}"/></th>
            <th><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${totalSumAccountsWholeFile.get(4)}"/></th>
            <th><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${totalSumAccountsWholeFile.get(5)}"/></th>
        </tr>
    </table>
</body>
</html>
