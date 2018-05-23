<%@ page import="com.kirichenko.Entities.BillEntity" %>
<%@ page import="com.kirichenko.servlets.OrdersServlet" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 14.04.2018
  Time: 8:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список заказов</title>
</head>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="SELECT_LIST_ORDERS" value="true"/>
</jsp:include>

<h3 align="center">Список заказов</h3>

<div align="center">
    <form method="post" name="CreateOrder" onsubmit="isProcessCreate(-1);">
        <input type="submit" value="Создать заказ"/>
        <input type="hidden" name="create"/>
    </form>
</div>

<div style="position: absolute; left: 50%;">
    <div style="position: relative; left: -50%;  max-width:1400px; border:2px solid #ccc;">
        <form name="ChangeOrder">
            <table align="center" width="1400" cellspacing="10">

                <tr>
                    <th align="center">Номер заказа</th>
                    <th align="center">Клиент</th>
                    <th align="center">Телефон Клиента</th>
                    <th align="center">Водитель</th>
                    <th align="center">Телефон Водителя</th>
                    <th align="center">Дата заказа</th>
                    <th align="center">Дата поставки</th>
                    <th align="center">Стоимость</th>
                    <th align="center">Объем</th>
                    <th align="center">Адрес доставки</th>
                </tr>

                <%
                    try {

                        List<BillEntity> list = OrdersServlet.loadData();
                        for (BillEntity orderEntity : list) {
                %>

                <tr align="center">
                    <td><%= orderEntity.getId() %>
                    </td>
                    <td><%= orderEntity.getDeliveryAddressByIdDeliveryAddress().getClientByClientId().getFirstName()
                            + " " + orderEntity.getDeliveryAddressByIdDeliveryAddress().getClientByClientId().getSecondName()
                    %>
                    </td>
                    <td><%= orderEntity.getDeliveryAddressByIdDeliveryAddress().getClientByClientId().getPhone() %>
                    </td>

                    <td><%= orderEntity.getBillFordriverByIdBillForDriver().getDriverByIdDriver().getFirstName()
                            + " " + orderEntity.getBillFordriverByIdBillForDriver().getDriverByIdDriver().getSecondName()%>
                    </td>
                    <td><%= orderEntity.getBillFordriverByIdBillForDriver().getDriverByIdDriver().getPhone() %>
                    </td>
                    <td><%= orderEntity.getBillDay().substring(0, orderEntity.getBillDay().indexOf(" "))%>
                    </td>
                    <td><%= orderEntity.getDaysByIdDate().getCurrentDate().substring(0, orderEntity.getBillDay().indexOf(" "))%>
                    </td>
                    <td><%= orderEntity.getPrice() %>
                    </td>
                    <td><%= orderEntity.getAmount() %>
                    </td>
                    <td><%= orderEntity.getDeliveryAddressByIdDeliveryAddress().getAddress() %>
                    </td>

                    <td>

                        <input type="button" name="change" value="Изменить"
                               onclick="isProcessChange(<%= orderEntity.getId() %>);"
                               id="<%= orderEntity.getId() %> ">

                        <input type="hidden" name="orderId" value="<%= orderEntity.getId() %>"/>
                        <input type="hidden" name="answer">

                    </td>

                </tr>

                <%
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                %>

            </table>

        </form>
    </div>
</div>


<SCRIPT LANGUAGE="JavaScript">
    function isProcessCreate(id) {
        var f = document.CreateOrder;
        f.method = "post";
        f.action = '/ModifyOrder?id=' + id;
        f.submit();
    }

    function isProcessChange(id) {
        var f = document.ChangeOrder;
        f.method = "post";
        f.action = '/ModifyOrder?id=' + id;
        f.submit();
    }

</SCRIPT>

</body>
</html>


<%--<h3 align="center">Enter Points:</h3>--%>
<%--<h3 align="center">--%>
<%--<label for="Points"></label>--%>
<%--<select name="Points" size="1" id="Points">--%>
<%--<option value="5" selected>5</option>--%>
<%--<option value="10">10</option>--%>
<%--<option value="15">15</option>--%>
<%--<option value="20">20</option>--%>
<%--<option value="25">25</option>--%>
<%--</select>--%>
<%--<br/>--%>
<%--</h3>--%>