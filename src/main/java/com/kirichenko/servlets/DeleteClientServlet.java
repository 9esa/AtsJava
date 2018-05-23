package com.kirichenko.servlets;

import com.kirichenko.Entities.ClientEntity;
import com.kirichenko.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 23.05.2018.
 */
public class DeleteClientServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sId = request.getParameter("id");
        removeClient(sId);
        response.sendRedirect("/ClientList");
    }

    public static void removeClient(String sIdClient) {
        int id = Integer.valueOf(sIdClient);

        try {

            Session session = HibernateSessionFactory.getSessionFactory().openSession();

            session.beginTransaction();

            Query query = session.createQuery(
                    " From ClientEntity ce where ce.id=:sIdClient");
            query.setParameter("sIdClient", sIdClient);

            ClientEntity clientEntity = new ClientEntity();
            clientEntity.setIdclient(id);

            session.delete(clientEntity);

            session.getTransaction().commit();

            session.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

