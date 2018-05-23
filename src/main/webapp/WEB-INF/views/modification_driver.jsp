<%@ page import="com.kirichenko.servlets.ModifyDriverServlet" %>
<%@ page import="com.kirichenko.Entities.DriverEntity" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 06.05.2018
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <SCRIPT LANGUAGE="JavaScript">

        function isCloseModifyDriver() {
            var f = document.form_driver;
            f.method = "get";
            f.action = '/ModifyDriver';
            f.submit();
        }

        function isModifyDriver() {
            var f = document.form_driver;
            f.method = "post";
            f.action = '/ModifyDriver';
            f.submit();
        }

        function checkParams() {

            var firstName = document.getElementById("j_firstname_id").value;
            var secondName = document.getElementById("j_secondname_id").value;
            var phone = document.getElementById("j_phone_id").value;

            if (firstName.length != 0 && secondName.length != 0
                && phone.length != 0) {
                document.getElementById("createBtn").removeAttribute("disabled");
                document.getElementById("updateBtn").removeAttribute("disabled");
            } else {
                document.getElementById("createBtn").setAttribute("disabled", "disabled");
                document.getElementById("updateBtn").setAttribute("disabled", "disabled");
            }
        }

    </SCRIPT>
    <%
        if (ModifyDriverServlet.iId == -1) {
    %>
    <title>Добавление водителя</title>
    <%
    } else {
    %>
    <title>Изменения водителя</title>
    <%
        }
    %>
</head>
<body>

<%
    DriverEntity driverEntity = new DriverEntity(true);
    if (ModifyDriverServlet.iId == -1) {
%>
<h3 align="center">Добавление водителя</h3>
<%
} else {
%>
<h3 align="center">Изменения водителя</h3>
<%
        driverEntity = ModifyDriverServlet.loadDriverInformation(ModifyDriverServlet.iId).get(0);
    }

%>

<form id="form_driver" name="form_driver" width="300" align="center" method="POST">
    <center>
        <div align="center" style="max-width:500px; border:2px solid #ccc;">

            <table cellspacing="5">
                <tr>
                    <th height="50" align="right" style="font-size: larger">Фамилия:</th>
                    <td align="left"><input type="text" id="j_firstname_id" name="j_firstname"
                                            onkeyup="checkParams()" value="<%= driverEntity.getFirstName()%>"></td>
                </tr>
                <tr>
                    <th height="50" align="right" style="font-size: larger">Имя:</th>
                    <td align="left"><input type="text" id="j_secondname_id" name="j_secondname"
                                            onkeyup="checkParams()" value="<%= driverEntity.getSecondName()%>"></td>
                </tr>

                <tr>
                    <th height="50" align="right" style="font-size: larger">Телефон (79112223344):</th>
                    <td align="left"><input type="text" id="j_phone_id" name="j_phone"
                                            onkeyup="checkParams()" value="<%= driverEntity.getPhone()%>"></td>
                </tr>

                <%
                    if (ModifyDriverServlet.iId == -1) {
                %>
                <tr style="align-content: center">
                    <td style="height: 70px" align="right">
                        <input style="height: 70px" id="createBtn" type="button" value="Создать"
                               onclick="isModifyDriver()" disabled>
                    </td>
                    <td style="height: 70px" align="right">
                        <input style="height: 70px" type="button" value="Закрыть"
                               onclick="isCloseModifyDriver()">
                    </td>
                </tr>
                <%
                } else {
                %>
                <tr style="align-content: center">
                    <td style="height: 70px" align="right">
                        <input style="height: 70px" id="updateBtn" type="button" value="Обновить"
                               onclick="isModifyDriver()">
                    </td>
                    <td style="height: 70px" align="right">
                        <input style="height: 70px" type="button" value="Закрыть"
                               onclick="isCloseModifyDriver()">
                    </td>
                </tr>
                <%
                    }
                %>


            </table>
        </div>
    </center>

    <p style="color: red;">${ERROR_LOGIN}</p>

</form>


</body>
</html>
