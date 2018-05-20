<%@ page import="com.kirichenko.servlets.ModifyOrderServlet" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="ajax" uri="/WEB-INF/tlds/ajax_controls.tld" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <%
        if (ModifyOrderServlet.iId == -1) {
    %>
    <title>Создание заказа</title>
    <%
    } else {
    %>
    <title>Изменения заказа</title>
    <%
        }
    %>
    <ajax:page/>


    <SCRIPT LANGUAGE="JavaScript">
        function isChangeClient(id) {
            alert("Что то выбрали");
            var f = document.form_order;
            f.method = "post";
            f.action = '/ModifyOrder?id=' + id;
            f.submit();
        }

        function isProcessCreateOrder() {
            var client = document.getElementById("clientName").value;
            var address = document.getElementById("addressName").value;
            var driver = document.getElementById("driverName").value;

            var f = document.modify_order;
            f.method = "post";
            f.action = '/CreateOrder?client=' + client + "&address=" + address + "&driver=" + driver;
            f.submit();

        }
    </SCRIPT>

</head>
<body>
<%
    if (ModifyOrderServlet.iId == -1) {
%>
<h3 align="center">Создание заказа</h3>
<%
} else {
%>
<h3 align="center">Изменения заказа</h3>
<%
    }
%>

<form id="form_order" name="modify_order" width="900" align="center" method="POST">
    <center>
        <div align="center" style="max-width:900px; border:2px solid #ccc;">

            <table cellspacing="5">

                <%--<tr>--%>

                <%--<th height="50" align="right" style="font-size: larger">Клиент</th>--%>

                <%--<td height="50" align="center">--%>
                <%--<select name="j_client" size="1" id="Clients" style="font-size: larger">--%>
                <%--<%--%>
                <%--try {--%>
                <%--List<ClientEntity> list = ClientsServlet.loadData();--%>
                <%--for (ClientEntity clientEntity : list) {--%>
                <%--%>--%>

                <%--<option value= <%= clientEntity.getIdclient() %>  selected>--%>
                <%--<%= clientEntity.getFirstName() + " " + clientEntity.getSecondName() + " " + clientEntity.getPhone()%>--%>
                <%--</option>--%>

                <%--<%--%>
                <%--}--%>
                <%--} catch (Exception e) {--%>
                <%--System.out.println(e);--%>
                <%--}--%>
                <%--%>--%>

                <%--</select>--%>
                <%--</td>--%>
                <%--</tr>--%>

                <tr>
                    <th height="50" align="right" style="font-size: larger"> Клиент</th>

                    <td class="fieldValue" style="height: 50px; width: 400px; font-size: larger">
                        <ajax:dropdown id="clientName" dataurl="/GetClientList" width="240"
                                       updatemessage="Загружаем информацию..."
                                       cascadeto="addressName"


                        />
                    </td>
                </tr>

                <tr>
                    <th height="50" align="right" style="font-size: larger">Адрес поставки</th>

                    <td class="fieldValue" style="height: 50px; width: 400px; font-size: larger">
                        <ajax:dropdown id="addressName" dataurl="/GetAddressForClient" width="240"
                                       updatemessage="Загружаем информацию..."
                                       cascadefrom="clientName"

                        />
                    </td>
                </tr>

                <tr>
                    <th height="50" align="right" style="font-size: larger">Водитель</th>

                    <td class="fieldValue" style="height: 50px; width: 400px; font-size: larger">
                        <ajax:dropdown id="driverName" dataurl="/GetDriverList" width="240"
                                       updatemessage="Загружаем информацию..."
                        />
                    </td>
                </tr>

                <tr>
                    <th height="50" align="right" style="font-size: larger"> Дата поставки</th>
                    <td align="left"><input height="50" type="date" name="j_date_delivery"
                                            style="height: 50px; width: 400px; font-size: larger"></td>
                </tr>

                <tr>
                    <th height="50" align="right" style="font-size: larger"> Объем поставки</th>
                    <td align="left"><input height="50" type="text" name="j_volume_delivery" pattern="^[ 0-9]+$"
                                            style="height: 50px; width: 400px; font-size: larger"></td>
                </tr>


                <tr>
                    <th height="50" align="right" style="font-size: larger"> Стоимость поставки</th>
                    <td align="left"><input height="50" type="text" name="j_price_delivery" pattern="^[ 0-9]+$"
                                            style="height: 50px; width: 400px; font-size: larger"></td>
                </tr>

                <tr>
                    <th height="50" align="right" style="font-size: larger"> Комментарий</th>
                    <td height="100" align="left" style="font-size: larger"><input type="text" name="j_commentary"
                                                                                   style="height: 100px; width: 400px; font-size: larger">
                </tr>

                <tr>
                    <td align="right">
                        <input height="50" type="button" name="create" value="Создать"
                               onclick="isProcessCreateOrder()">
                    </td>
                    <td align="left"><input height="50" width="200" type="reset"></td>
                </tr>

            </table>
        </div>
    </center>

    <p style="color: red;">${ERROR_LOGIN}</p>

</form>

</body>

</html>
