<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 25.03.2018
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <%
        Integer hitsCount = (Integer)application.getAttribute("hitCounter");
    %>

    <form id="form_login"  align="center" method="POST">
        <center>
            <table border="0" cellspacing="5">
                <tr>
                    <th align="right">Пользователь:</th>
                    <td align="left"><input type="text" name="j_username" value= "${LOGIN}"></td>
                </tr>
                <tr>
                    <th align="right">Пароль:</th>
                    <td align="left"><input type="password" name="j_password"></td>
                </tr>
                <tr>
                    <td align="right"><input type="submit" value="Ок"></td>
                    <td align="left"><input type="reset"></td>
                </tr>
            </table>
        </center>

        <p style="color: red;">${ERROR_LOGIN}</p>

    </form>
</body>
</html>
