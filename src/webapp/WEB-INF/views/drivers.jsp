<%@ page import="com.kirichenko.Entities.DriverEntity" %>
<%@ page import="com.kirichenko.servlets.DriversServlet" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 14.04.2018
  Time: 8:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список водителей</title>

    <SCRIPT LANGUAGE="JavaScript">

        function isProcessDelete(id) {
            var answer = confirm("Вы действительно хотите удалить водителя ?");
            if (answer) {
                var f = document.ChangeDriver;
                f.method = "post";
                f.action = '/DeleteDriver?id=' + id;
                f.submit();
                return true;
            } else {
                return false
            }

        }

        function isProcessCreate(id) {
            var f = document.CreateDriver;
            f.method = "post";
            f.action = '/ModifyDriver?id=' + id;
            f.submit();
        }

        function isProcessChange(id) {
            var f = document.ChangeDriver;
            f.method = "post";
            f.action = '/ModifyDriver?id=' + id;
            f.submit();
        }

    </script>

</head>
<body>

<jsp:include page="header.jsp">
    <jsp:param name="SELECT_DRIVERS" value="true"/>
</jsp:include>

<h3 align="center">Список водителей</h3>

<div align="center">
    <form method="post" name="CreateDriver" onsubmit="isProcessCreate(-1);">
        <input type="submit" value="Добавить"/>
        <input type="hidden" name="create"/>
    </form>
</div>

<div style="position: absolute; left: 50%;">
    <div style="position: relative; left: -50%;  max-width:800px; border:2px solid #ccc;">
        <form method="post" name="ChangeDriver">
            <table align="center" width="800" cellspacing="10">

                <tr>
                    <th align="center">Фамилия</th>
                    <th align="center">Имя</th>
                    <th align="center">Телефон</th>
                    <th align="center">Действия</th>
                </tr>

                <%
                    if (request.getParameter("buttonName") != null) {
                        session.setAttribute("status", "guest");
                    }
                %>

                <%
                    try {

                        List<DriverEntity> list = DriversServlet.loadData();
                        for (DriverEntity driverEntity : list) {
                            if (driverEntity.isRemove()) {
                                continue;
                            }
                %>

                <tr align="center">
                    <td><%= driverEntity.getFirstName() %>
                    </td>
                    <td><%= driverEntity.getSecondName() %>
                    </td>
                    <td><%= driverEntity.getPhone() %>
                    </td>

                    <%
                        String sValue = driverEntity.getSecondName() + " " + driverEntity.getFirstName();
                    %>

                    <td>
                        <input type="button" name="delete" value="Удалить"
                        <%--style="background-color: aqua; font-weight: bold;color: white"--%>
                               onclick="isProcessDelete(<%= driverEntity.getIddriver() %>);"
                               id="<%= driverEntity.getIddriver() %> ">

                        <input type="button" name="change" value="Изменить"
                               onclick="isProcessChange(<%= driverEntity.getIddriver() %>);"
                               id="<%= driverEntity.getIddriver() %> ">

                        <input type="hidden" name="driverId" value="<%= driverEntity.getIddriver() %>"/>
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

</body>
