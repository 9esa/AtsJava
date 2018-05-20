
<%@ page import="com.kirichenko.Entities.ClientEntity" %>
<%@ page import="com.kirichenko.servlets.ClientsServlet" %>
<%@ page import="com.kirichenko.servlets.ModifyAddressServlet" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 09.05.2018
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        if (ModifyAddressServlet.iId == -1) {
    %>
    <title>Добавление адреса доставки</title>
    <%
    } else {
    %>
    <title>Изменения адреса доставки</title>
    <%
        }
    %>
</head>
<body>
<%
    if (ModifyAddressServlet.iId == -1) {
%>
<h3 align="center">Добавление адреса доставки</h3>
<%
} else {
%>
<h3 align="center">Изменения адреса доставки</h3>
<%
    }
%>

<form id="form_address" width="400" align="center" method="POST">
    <center>
        <div align="center" style="max-width:600px; border:2px solid #ccc;">

            <table cellspacing="5">

                <tr>
                    <th height="100" align="right" style="font-size: larger">Адрес доставки:</th>
                    <td height="100" align="left" style="font-size: larger"><input type="text" name="j_address"
                                                                                   size="35"
                                                                                   style="height: 100px; width: 400px; font-size: larger">
                    </td>
                </tr>


                <tr>
                    <th height="50" align="right" style="font-size: larger">Клиент</th>
                    <td height="50" align="center">
                        <select name="j_client" size="1" id="Clients" style="font-size: larger">
                            <%
                                try {
                                    List<ClientEntity> list = ClientsServlet.loadData();
                                    for (ClientEntity clientEntity : list) {
                            %>

                            <option value=<%= clientEntity.getIdclient() %> selected>
                                <%= clientEntity.getFirstName() + " " + clientEntity.getSecondName() + " " + clientEntity.getPhone()%>
                            </option>

                            <%
                                    }
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                            %>

                        </select>
                    </td>
                </tr>
                <%--<tr>--%>
                    <%--<td align="center">--%>
                        <%--&lt;%&ndash;<div style="position: absolute; left: 50%;">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<div style="position: relative; left: -50%;  max-width:400px; border:2px solid #ccc;">&ndash;%&gt;--%>
                                <%--<jsp:include page="addresses_for_client.jsp"/>--%>
                            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <tr>
                    <td height="50" align="right"><input type="submit" value="Ок"
                                                         style="height: 50px; width: 75px; font-size: larger"></td>
                </tr>
                <%--<jsp:include page="clients.jsp">--%>
                <%--<jsp:param name="SELECT_CLIENTS" value="true"/>--%>
                <%--</jsp:include>--%>


            </table>
        </div>
    </center>

    <p style="color: red;">${ERROR_LOGIN}</p>

</form>
</body>
</html>
