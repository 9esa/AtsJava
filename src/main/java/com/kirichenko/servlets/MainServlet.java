package com.kirichenko.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 25.03.2018.
 */
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        HttpSession session = request.getSession();
//
//        UserInfo userInfo = MyUtils.getLoginedUser(session);
//        if (userInfo == null) {
//            response.sendRedirect(request.getContextPath() + "/login");
//            return;
//        }
//
//        // Сохранить информацию в request attribute перед тем как forward (перенаправить).
//        request.setAttribute("user", userInfo);

        // Если пользователь уже вошел в систему (login), то forward (перенаправить) к странице
        // /com.main.webapp.main.webapp.WEB-INF/views/userInfoView.jsp
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/main.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


//        String username = request.getParameter("j_username");
//        String password = request.getParameter("j_password");
//
//        System.out.println("username: " +  username);
//        System.out.println("password: " +  password);
//
//        RequestDispatcher req = request.getRequestDispatcher("main.jsp");
//        req.forward(request, response);
     //   super.doPost(req, resp);
    }



}
