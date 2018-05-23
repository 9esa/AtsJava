<%@ page import="com.kirichenko.Entities.ClientEntity" %>
<%@ page import="com.kirichenko.servlets.ClientsServlet" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 09.04.2018
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список клиентов</title>
</head>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="SELECT_CLIENTS" value="true"/>
</jsp:include>

<h3 align="center">Список клиентов</h3>

<div align="center">
    <form method="post" name="CreateUser" onsubmit="isProcessCreate(-1);">
        <input type="submit" value="Добавить"/>
        <input type="hidden" name="create"/>
    </form>
</div>


<div style="position: absolute; left: 50%;">
    <div style="position: relative; left: -50%;  max-width:800px; border:2px solid #ccc;">
        <form name="ChangeUser">
            <table align="center" width="800" cellspacing="10">

                <tr>
                    <th align="center">Фамилия</th>
                    <th align="center">Имя</th>
                    <th align="center">Телефон</th>

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

                </tr>

                <%
                    try {

                        List<ClientEntity> list = ClientsServlet.loadData();
                        for (ClientEntity clientEntity : list) {
                %>

                <tr align="center">
                    <td><%= clientEntity.getFirstName() %>
                    </td>
                    <td><%= clientEntity.getSecondName() %>
                    </td>
                    <td><%= clientEntity.getPhone() %>
                    </td>

                    <td>

                        <input type="button" name="change" value="Удалить"
                               onclick="isProcessRemove(<%= clientEntity.getIdclient() %>);"
                               id="<%= clientEntity.getIdclient() %> ">

                        <input type="button" name="change" value="Изменить"
                               onclick="isProcessChange(<%= clientEntity.getIdclient() %>);"
                               id="<%= clientEntity.getIdclient() %> ">

                        <input type="button" name="createOrder" value="Создать заказ"
                               onclick="isOrderCreate(<%= clientEntity.getIdclient() %>);"
                               id="<%= clientEntity.getIdclient() %> ">


                        <input type="hidden" name="clientId" value="<%= clientEntity.getIdclient() %>"/>
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
        var f = document.CreateUser;
        f.method = "post";
        f.action = '/ModifyClient?id=' + id;
        f.submit();
    }

    function isProcessChange(id) {
        var f = document.ChangeUser;
        f.method = "post";
        f.action = '/ModifyClient?id=' + id;
        f.submit();
    }

    function isProcessRemove(id) {
        var answer = confirm("Вы действительно хотите удалить клиента?");
        if (answer) {
            var f = document.ChangeUser;
            f.method = "post";
            f.action = '/DeleteClient?id=' + id;
            f.submit();
            return true;
        } else {
            return false
        }
    }

    function isOrderCreate(id) {
        var f = document.ChangeUser;
        f.method = "post";
        f.action = '/ModifyOrder?id=' + -1 + '&clientId=' + id;
        f.submit();
    }

</SCRIPT>

</body>
</html>
