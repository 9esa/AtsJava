package com.kirichenko.servlets;

import com.kirichenko.Entities.ClientEntity;
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
 * Created by user on 08.05.2018.
 */
public class ModifyClientServlet extends HttpServlet {

    public static int iId = -1;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sFirstName = request.getParameter("j_firstname");
        String sSecondName = request.getParameter("j_secondname");
        String sPhone = request.getParameter("j_phone");

        if (sFirstName == null && sSecondName == null && sPhone == null) {

            iId = Integer.valueOf(request.getParameter("id"));

            RequestDispatcher dispatcher =
                    this.getServletContext().getRequestDispatcher("/WEB-INF/views/modification_client.jsp");
            dispatcher.forward(request, response);

        } else {

            try {

                if (sFirstName != null && sSecondName != null && sPhone != null) {
                    Session session = HibernateSessionFactory.getSessionFactory().openSession();

                    session.beginTransaction();

                    ClientEntity clientEntity = new ClientEntity();
                    clientEntity.setSecondName(sSecondName);
                    clientEntity.setFirstName(sFirstName);
                    clientEntity.setPhone(sPhone);

                    session.save(clientEntity);

                    session.getTransaction().commit();

                    response.sendRedirect(request.getContextPath() + "/ClientList");

                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static ArrayList<ClientEntity> loadClientInformation(int iId) {

        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();

            Query query = session.createQuery("from ClientEntity ce where ce.id =:iUserId ");
            query.setParameter("iUserId", iId);

            List userList = query.getResultList();

            session.close();

            return (ArrayList<ClientEntity>) userList;

        } catch (Exception ex) {
            return new ArrayList();
        }
    }

}
