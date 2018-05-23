<%@ page import="com.kirichenko.Entities.JournalCallsEntity" %>
<%@ page import="com.kirichenko.servlets.JournalServlet" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 22.04.2018
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список звонков</title>
</head>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="SELECT_JOURNALS" value="true"/>
</jsp:include>

<h3 align="center">Список звонков</h3>

<div style="position: absolute; left: 50%;">
    <div style="position: relative; left: -50%;  max-width:800px; border:2px solid #ccc;">
        <form method="post" name="ChangeDriver">
            <table align="center" width="800" cellspacing="10">

                <tr>
                    <th align="center">Идентификатор звонка</th>
                    <th align="center">Звонящий</th>
                    <th align="center">Телефон звонящего</th>
                    <th align="center">Направление вызова</th>
                    <th align="center">Дата звонка</th>
                </tr>

                <%
                    try {

                        List<JournalCallsEntity> list = JournalServlet.loadData();
                        for (JournalCallsEntity callsEntity : list) {
//                if (driverEntity.isRemove()) {
//                continue;
//                }
                %>

                <tr align="center">
                    <td><%= callsEntity.getId()%>
                    <td>----</td>
                    <td><%= callsEntity.getPhoneCall() %>
                    </td>
                    <td><%= callsEntity.getDestinationCall() %>
                    </td>
                    <td><%= callsEntity.getDateCall().substring(0, callsEntity.getDateCall().indexOf(" "))%>
                    </td>

                    <%--<input type="button" name="delete" value="Удалить"--%>
                    <%--&lt;%&ndash;style="background-color: aqua; font-weight: bold;color: white"&ndash;%&gt;--%>
                    <%--onclick="isProcessDelete(<%= driverEntity.getIddriver() %>);"--%>
                    <%--id="<%= driverEntity.getIddriver() %> ">--%>

                    <%--<input type="button" name="change" value="Изменить"--%>
                    <%--onclick="isProcessChange(<%= driverEntity.getIddriver() %>);"--%>
                    <%--id="<%= driverEntity.getIddriver() %> ">--%>

                    <%--<input type="hidden" name="driverId" value="<%= driverEntity.getIddriver() %>"/>--%>
                    <%--<input type="hidden" name="answer">--%>

                    <%--</td>--%>
                    <%--</tr>--%>

                    <%
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    %>
                </tr>
            </table>
        </form>
    </div>

</div>


</body>
</html>
