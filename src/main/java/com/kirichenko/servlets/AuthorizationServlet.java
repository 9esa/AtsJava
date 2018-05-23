package com.kirichenko.servlets;

import com.kirichenko.beans.UserInfo;
import com.kirichenko.utils.Constant;
import com.kirichenko.utils.HibernateSessionFactory;
import com.kirichenko.utils.MyUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by user on 01.04.2018.
 */
public class AuthorizationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        RequestDispatcher dispatcher =
                this.getServletContext().getRequestDispatcher("/WEB-INF/views/autorization.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //action='<%= response.encodeURL("MainMenu") %>
        String username = request.getParameter("j_username");
        String password = request.getParameter("j_password");

        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();

            Query query = session.createQuery("from UsersEntity where login = :login and password = :password");
            query.setParameter("login", username);
            query.setParameter("password", password);

            UserInfo userInfo = new UserInfo();
            userInfo.setUserName(username);
            userInfo.setPassword(password);

            List arrayLIst = query.getResultList();

            if (arrayLIst.size() > 0) {

                HttpSession httpSession = request.getSession();
                MyUtils.storeLoginedUser(httpSession, userInfo);

                // Если пользователь выбирает функцию "Remember me".
               // if (remember) {
                    MyUtils.storeUserCookie(response, userInfo);
               // }
                // Наоборот, удалить Cookie
//                else {
//                    MyUtils.deleteUserCookie(response);
//                }

                // Redirect (Перенаправить) на страницу /userInfo.
                response.sendRedirect(request.getContextPath() + "/ClientList");

            } else {

                request.setAttribute(Constant.ERROR_LOGIN, Constant.ERROR_LOGIN_MESSAGE);
                request.setAttribute(Constant.LOGIN, username);

                RequestDispatcher dispatcher
                        = this.getServletContext().getRequestDispatcher("/WEB-INF/views/autorization.jsp");
                dispatcher.forward(request, response);
            }
            session.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


}
