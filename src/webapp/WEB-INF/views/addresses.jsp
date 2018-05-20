
<%@ page import="com.kirichenko.Entities.DeliveryAddressEntity" %>
<%@ page import="com.kirichenko.servlets.AddressServlet" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 22.04.2018
  Time: 12:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список адресов</title>
</head>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="SELECT_ADDRESS" value="true"/>
</jsp:include>

<h3 align="center">Список адресов</h3>

<div align="center">
    <form method="post" name="CreateAddress" onsubmit="isProcessCreate(-1);">
        <input type="submit" value="Добавить"/>
        <input type="hidden" name="create"/>
    </form>
</div>

<div style="position: absolute; left: 50%;">
    <div style="position: relative; left: -50%;  max-width:800px; border:2px solid #ccc;">
        <form method="post" name="ChangeAddress">
            <table align="center" width="800" cellspacing="10">

                <tr>
                    <th align="center">Адрес доставки</th>
                    <th align="center">Приоритетный</th>
                    <th align="center">Клиент</th>
                </tr>

                <%
                    try {

                        List<DeliveryAddressEntity> list = AddressServlet.loadData();
                        for (DeliveryAddressEntity deliveryAddressEntity : list) {
//                            if (driverEntity.isRemove()) {
//                                continue;
//                            }
                %>

                <tr align="center">

                    <td><%= deliveryAddressEntity.getAddress() %>
                    </td>
                    <td>
                        <%
                            if (deliveryAddressEntity.getIsMain().equals("false")) {
                        %>
                        Основной
                        <%
                        } else {
                        %>
                        Дополнительный
                        <%
                            }
                        %>
                    </td>
                    <td>
                        <%
                            if (deliveryAddressEntity.getClientByClientId() != null) {
                        %>
                        <%= deliveryAddressEntity.getClientByClientId().getFirstName() + " " + deliveryAddressEntity.getClientByClientId().getSecondName()%>
                        <%
                        } else {
                        %>
                        -
                        <%}%>


                    </td>

                    <td>
                        <input type="button" name="delete" value="Удалить"
                        <%--style="background-color: aqua; font-weight: bold;color: white"--%>
                        onclick="isProcessDelete(<%= deliveryAddressEntity.getIddeliveryAddress() %>);"
                        id="<%= deliveryAddressEntity.getIddeliveryAddress() %> ">

                        <%--<input type="button" name="change" value="Изменить"--%>
                        <%--onclick="isProcessChange(<%= driverEntity.getIddriver() %>);"--%>
                        <%--id="<%= driverEntity.getIddriver() %> ">--%>

                        <%--<input type="hidden" name="driverId" value="<%= driverEntity.getIddriver() %>"/>--%>
                        <%--<input type="hidden" name="answer">--%>

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
        var f = document.CreateAddress;
        f.method = "post";
        f.action = '/ModifyAddress?id=' + id;
        f.submit();
    }

    function isProcessDelete(id) {
        var answer = confirm("Вы действительно хотите удалить адрес клиента?");
        if (answer) {
            var f = document.ChangeAddress;
            f.method = "post";
            f.action = '/DeleteAddress?id=' + id;
            f.submit();
            return true;
        } else {
            return false
        }

    }

</SCRIPT>

</body>


</html>

