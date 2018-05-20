<%@ page import="com.kirichenko.utils.Constant" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 22.04.2018
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${param.pageTitle}</title>
</head>
<body>
<form id="form_choose_menu" method="POST">
    <center>
        <table border="1" width="100%" cellspacing="10" cellpadding="1">
            <tr>

                <%
                    String sSelectedOrders = request.getParameter(Constant.SELECT_LIST_ORDERS);
                    if (sSelectedOrders != null) {
                %>
                <th align="center" style="background-color: lavender">
                        <%
                    }else{
                %>
                <th align="center">
                    <%
                        }
                    %>
                    <a href="OrderList">Список заказов</a>
                </th>

                <%
                    String sSelectedClients = request.getParameter(Constant.SELECT_CLIENTS);
                    if (sSelectedClients != null) {
                %>
                <th align="center" style="background-color: lavender">
                        <%
                    }else{
                %>
                <th align="center">
                    <%
                        }
                    %>
                    <a href="ClientList">Список клиентов</a>
                </th>

                <%
                    String sSelectedDrivers = request.getParameter(Constant.SELECT_DRIVERS);
                    if (sSelectedDrivers != null) {
                %>
                <th align="center" style="background-color: lavender">
                        <%
                    }else{
                %>
                <th align="center">
                    <%
                        }
                    %>
                    <a href="DriverList">Список водителей</a>
                </th>

                <%
                    String sSelectedJournals = request.getParameter(Constant.SELECT_JOURNALS);
                    if (sSelectedJournals != null) {
                %>
                <th align="center" style="background-color: lavender">
                        <%
                    }else{
                %>
                <th align="center">
                    <%
                        }
                    %>
                    <a href="Journal">Журнал звонков</a>
                </th>

                <%
                    String sSelectedAddress = request.getParameter(Constant.SELECT_ADDRESS);
                    if (sSelectedAddress != null) {
                %>
                <th align="center" style="background-color: lavender">
                        <%
                    }else{
                %>
                <th align="center">
                    <%
                        }
                    %>
                    <a href="Addresses">Адреса</a>
                </th>

                <%
                    String sSelectedSmsInfo = request.getParameter(Constant.SELECT_SMS_INFO);
                    if (sSelectedSmsInfo != null) {
                %>
                <th align="center" style="background-color: lavender">
                        <%
                    }else{
                %>
                <th align="center">
                    <%
                        }
                    %>
                    <a href="SmsInfo">СМС информирование</a>
                </th>

                <%
                    String sSelectedUsersInfo = request.getParameter(Constant.SELECT_USERS_INFO);
                    if (sSelectedUsersInfo != null) {
                %>
                <th align="center" style="background-color: lavender">
                        <%
                    }else{
                %>
                <th align="center">
                    <%
                        }
                    %>
                    <a href="UsersList">Информация о пользователях</a>
                </th>
            </tr>
        </table>
    </center>
</form>
</body>
</html>
