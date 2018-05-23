<%@ page import="com.kirichenko.Entities.DeliveryAddressEntity" %>
<%@ page import="com.kirichenko.servlets.AddressServlet" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 09.05.2018
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h3 align="center">Список адресов для клиента</h3>

<form method="post" name="listAddresses">
    <table align="center" width="400" cellspacing="10">

        <%
            try {
                int clientId = 0;
                List<DeliveryAddressEntity> list = AddressServlet.loadData(clientId);
                for (DeliveryAddressEntity deliveryAddressEntity : list) {
        %>

        <tr align="center">
            <td><%= deliveryAddressEntity.getAddress() %>
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


</body>
</html>
