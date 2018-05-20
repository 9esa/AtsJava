package com.kirichenko.servlets;

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
 * Created by user on 09.04.2018.
 */
public class ClientsServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//todo Временно убрали загрузку
//        List <ClientEntity> clientEntityList = loadData();
//
//        request.setAttribute("clientEntityList", clientEntityList);
//
//        HttpSession session = request.getSession();
//
//        UserInfo userInfo = MyUtils.getLoginedUser(session);
//        if (userInfo == null) {
//            response.sendRedirect(request.getContextPath() + "/login");
//            return;
//        }
//
//        request.setAttribute("user", userInfo);

        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/clients.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    public static List loadData(){
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();

            Query query = session.createQuery("from ClientEntity");

            List clientsList = query.getResultList();

            session.close();

            return clientsList;

        }catch (Exception ex){
            return new ArrayList();
        }
    }
}
