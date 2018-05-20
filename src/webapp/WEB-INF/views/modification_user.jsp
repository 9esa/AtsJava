<%@ page import="com.kirichenko.Entities.UsersEntity" %>
<%@ page import="com.kirichenko.servlets.ModifyUserServlet" %>
<%@ page import="com.kirichenko.servlets.ModifyOrderServlet" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 16.05.2018
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>

    <%
        if (ModifyUserServlet.iId == -1) {
    %>
    <title>Создание пользователя</title>
    <%
    } else {
    %>
    <title>Изменения пользователя</title>
    <%
        }
    %>

    <SCRIPT LANGUAGE="JavaScript">

        function isProcessCreateUser() {
            var f = document.modify_user;
            f.method = "post";
            f.action = '/ModifyUser?action=create';
            f.submit();

        }

        function isProcessModifyUser() {
            var f = document.modify_user;
            f.method = "post";
            f.action = '/ModifyUser?action=update';
            f.submit();
        }


        function isCloseModifyUser() {
            var f = document.modify_user;
            f.method = "post";
            f.action = '/UsersList';
            f.submit();
        }

        function isSendSMS(code, sPhone) {
            var xhr = new XMLHttpRequest();
            xhr.open('POST', '/SendTryLogin?Receiver=' + sPhone + '&Message= Проверочный код: ' + code, false);
            xhr.send();
        }

        function checkParams() {

            var firstName = document.getElementById("j_firstname_id").value;
            var secondName = document.getElementById("j_secondname_id").value;
            var phone = document.getElementById("j_phone_id").value;
            var code = document.getElementById("j_code_id").value;
            var login = document.getElementById("j_login_id").value;
            var pass = document.getElementById("j_pass_id").value;
            var pass_again = document.getElementById("j_pass_again_id").value;

            if (firstName.length != 0 && secondName.length != 0
                && phone.length != 0 && code.length != 0
                && login.length != 0 && pass.length != 0
                && pass_again.length != 0
            ) {
                document.getElementById("createBtn").removeAttribute("disabled");
                document.getElementById("updateBtn").removeAttribute("disabled");
            } else {
                document.getElementById("createBtn").setAttribute("disabled", "disabled");
                document.getElementById("updateBtn").setAttribute("disabled", "disabled");
            }
        }

    </SCRIPT>

</head>
<%--<body style="background-image: url('../img/circles-light.png')" ;>--%>
<body>
<%
    UsersEntity usersEntity = new UsersEntity(true);
    if (ModifyUserServlet.iId == -1) {
%>
<h3 align="center">Создание пользователя</h3>
<%
} else {
%>
<h3 align="center">Изменения пользователя</h3>
<%
        usersEntity = ModifyUserServlet.loadUserInformation(ModifyUserServlet.iId).get(0);
    }

%>

<form id="modify_user" name="modify_user" width="700" align="center" method="POST">
    <center>
        <div align="center" style="max-width:800px; border:2px solid #ccc;">

            <table cellspacing="5" style="background: white">

                <tr style="height: 70px">
                    <th height="50" align="right" style="font-size: larger"> Фамилия:</th>
                    <td align="left"><input type="text" id="j_firstname_id" name="j_firstname" onkeyup="checkParams()"
                                            value="<%= usersEntity.getFirstName()%>"
                                            style="height: 50px; width: 400px; font-size: larger"></td>
                </tr>
                <tr style="height: 70px">
                    <th height="50" align="right" style="font-size: larger"> Имя:</th>
                    <td align="left"><input type="text" id="j_secondname_id" name="j_secondname" onkeyup="checkParams()"
                                            value="<%= usersEntity.getSecondName()%>"
                                            style="height: 50px; width: 400px; font-size: larger"></td>
                </tr>

                <tr style="height: 70px">
                    <th align="right" style="font-size: larger"><p>Телефон в формате +7-xxx-xxx-xx-xx </p></th>
                    <td align="center"><input id="j_phone_id" type="tel"
                                              pattern="\+7\-[0-9]{3}\-[0-9]{3}\-[0-9]{2}\-[0-9]{2}" name="j_phone"
                                              onkeyup="checkParams()"
                                              value="<%= usersEntity.getPhone()%>"
                                              style="height: 50px; width: 400px; font-size: larger"></td>
                    <%
                        if (ModifyUserServlet.iId == -1) {
                    %>
                    <td align="center">
                        <input type="button" name="create" value="Отправить код подтверждения"
                               onclick="isSendSMS(<%=ModifyUserServlet.iValueForCheck %>, j_phone.value)">
                    </td>
                    <%
                        }
                    %>

                </tr>

                <%
                    if (ModifyUserServlet.iId == -1) {
                %>
                <tr style="height: 70px">
                    <th height="30" align="right" style="font-size: medium"> Код подтверждения:</th>
                    <td align="left"><input type="text" id="j_code_id" name="j_code" onkeyup="checkParams()"
                                            style="height: 30px; width: 100px; font-size: medium"></td>
                </tr>
                <%
                    }
                %>

                <tr style="height: 70px">
                    <th height="50" align="right" style="font-size: larger"> Логин</th>
                    <td align="left"><input type="text" id="j_login_id" name="j_login" onkeyup="checkParams()"
                                            value="<%= usersEntity.getLogin()%>"
                                            style="height: 50px; width: 400px; font-size: larger"></td>
                </tr>

                <tr style="height: 70px">
                    <th height="50" align="right" style="font-size: larger"> Пароль:</th>
                    <td align="left"><input type="text" id="j_pass_id" name="j_pass" onkeyup="checkParams()"
                                            value="<%= usersEntity.getPassword()%>"
                                            style="height: 50px; width: 400px; font-size: larger"></td>
                </tr>

                <tr style="height: 70px">
                    <th height="50" align="right" style="font-size: larger"> Подтверждение пароля:</th>
                    <td align="left"><input type="text" id="j_pass_again_id" name="j_pass_again" onkeyup="checkParams()"
                                            value="<%= usersEntity.getPassword()%>"
                                            style="height: 50px; width: 400px; font-size: larger"></td>
                </tr>

                <%
                    if (ModifyUserServlet.iId == -1) {
                %>
                <tr style="align-content: center">
                    <td style="height: 70px" align="right">
                        <input style="height: 70px" id="createBtn" type="button" value="Создать"
                               onclick="isProcessCreateUser()" disabled>
                    </td>
                    <td style="height: 70px" align="right">
                        <input style="height: 70px" type="button" value="Закрыть"
                               onclick="isCloseModifyUser()">
                    </td>
                </tr>
                <%
                } else {
                %>
                <tr style="align-content: center">
                    <td style="height: 70px" align="right">
                        <input style="height: 70px" id="updateBtn" type="button" value="Обновить"
                               onclick="isProcessModifyUser()">
                    </td>
                    <td style="height: 70px" align="right">
                        <input style="height: 70px" type="button" value="Закрыть"
                               onclick="isCloseModifyUser()">
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
