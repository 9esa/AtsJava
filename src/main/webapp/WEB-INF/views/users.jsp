<%@ page import="com.kirichenko.Entities.UsersEntity" %>
<%@ page import="com.kirichenko.servlets.UserServlet" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 15.05.2018
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Информация о пользователях</title>
</head>
<%--<body style="background-image: url('../img/circles-light.png')" ;>--%>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="SELECT_USERS_INFO" value="true"/>
</jsp:include>

<h3 align="center">Список пользователей</h3>

<div align="center">
    <form method="post" name="CreateUser" onsubmit="isProcessCreate(-1);">
        <input type="submit" value="Добавить пользователя"/>
        <input type="hidden" name="create"/>
    </form>
</div>


<form name="ChangeUserSystem">
<div style="position: absolute; left: 50%;">
    <div style="position: relative; left: -50%;  max-width:800px; border:2px solid #ccc;">

            <table cellspacing="10" style="background: white;">

                <tr>
                    <th align="center">Фамилия</th>
                    <th align="center">Имя</th>
                    <th align="center">Телефон</th>
                    <th align="center">Логин</th>
                    <th align="center">Пароль</th>
                </tr>

                <%
                    try {

                        List<UsersEntity> list = UserServlet.loadData();
                        for (UsersEntity usersEntity : list) {
                %>

                <tr align="center">
                    <td><%= usersEntity.getFirstName() %>
                    </td>
                    <td><%= usersEntity.getSecondName() %>
                    </td>
                    <td><%= usersEntity.getPhone() %>
                    </td>
                    <td><%= usersEntity.getLogin() %>
                    </td>
                    <td>*******</td>

                    <td>
                        <input type="button" name="change" value="Изменить"
                               onclick="isProcessChange(<%= usersEntity.getIdusers() %>);"
                               id="<%= usersEntity.getIdusers() %> ">

                        <input type="button" name="delete" value="Удалить"
                               onclick="isProcessDelete(<%= usersEntity.getIdusers() %>);"
                               id="<%= usersEntity.getIdusers() %> ">
                    </td>
                </tr>

                <%
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                %>

            </table>


    </div>
</div>
</form>

<SCRIPT LANGUAGE="JavaScript">
    function isProcessCreate(id) {
        var f = document.CreateUser;
        f.method = "post";
        f.action = '/ModifyUser?id=' + id;
        f.submit();
    }

    function isProcessChange(id) {
        var f = document.CreateUser;
        f.method = "post";
        f.action = '/ModifyUser?id=' + id;
        f.submit();
    }

    function isProcessDelete(id) {
        var answer = confirm("Вы действительно хотите удалить пользователя?");
        if (answer) {
            var f = document.CreateUser;
            f.method = "post";
            f.action = '/ModifyUser?deleteId=' + id;
            f.submit();
            return true;
        } else {
            return false
        }

    }


</SCRIPT>

</body>
</html>
