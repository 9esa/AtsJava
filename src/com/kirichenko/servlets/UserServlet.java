package com.kirichenko.servlets;

import com.kirichenko.Entities.UsersEntity;
import com.kirichenko.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 15.05.2018.
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        RequestDispatcher dispatcher =
                this.getServletContext().getRequestDispatcher("/WEB-INF/views/users.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/UsersList");

    }

    public static List<UsersEntity> loadData() {
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();

            Query query = session.createQuery("from UsersEntity ");

            List userList = query.getResultList();

            session.close();

            return userList;

        }catch (Exception ex){
            return new ArrayList();
        }
    }
}