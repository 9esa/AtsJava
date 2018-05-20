<%@ page import="com.kirichenko.servlets.ModifyDriverServlet" %>
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
    if (ModifyDriverServlet.iId == -1) {
%>
<h3 align="center">Добавление водителя</h3>
<%
} else {
%>
<h3 align="center">Изменения водителя</h3>
<%
    }
%>

<form id="form_driver" name = "form_driver" width="300" align="center" method="POST">
    <center>
        <div align="center" style="max-width:500px; border:2px solid #ccc;">

            <table cellspacing="5">
                <tr>
                    <th height="50" align="right" style="font-size: larger">Фамилия:</th>
                    <td align="left"><input type="text" name="j_firstname"></td>
                </tr>
                <tr>
                    <th height="50" align="right" style="font-size: larger">Имя:</th>
                    <td align="left"><input type="text" name="j_secondname"></td>
                </tr>

                <tr>
                    <th height="50" align="right" style="font-size: larger">Телефон (79112223344):</th>
                    <td align="left"><input type="text" name="j_phone"></td>
                </tr>
                <tr>
                    <td align="right"><input type="submit" value="Ок"></td>
                    <td style="height: 70px" align="right">
                        <input style="height: 70px" type="button" value="Закрыть"
                               onclick="isCloseModifyDriver()">
                    </td>
                </tr>
            </table>
        </div>
    </center>

    <p style="color: red;">${ERROR_LOGIN}</p>

</form>


</body>
</html>
