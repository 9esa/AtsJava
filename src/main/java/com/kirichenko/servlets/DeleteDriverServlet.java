package com.kirichenko.servlets;

import com.kirichenko.Entities.DriverEntity;
import com.kirichenko.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by user on 04.05.2018.
 */
public class DeleteDriverServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sId = request.getParameter("id");
        removeDriver(sId);
        response.sendRedirect("DriverList");
    }

    public static void removeDriver(String sIdDriver){
        int id = Integer.valueOf(sIdDriver);

        try {

            Session session = HibernateSessionFactory.getSessionFactory().openSession();

            session.beginTransaction();

            Query query = session.createQuery(
                    "from DriverEntity de where de.iddriver=:iddriver");
            query.setParameter("iddriver", id);

            ArrayList<DriverEntity> driverList = ( ArrayList<DriverEntity>) query.getResultList();

            driverList.get(0).setRemove(true);

            session.update(driverList.get(0));

            session.getTransaction().commit();

            session.close();

        }catch (Exception ex){
            System.out.println( ex.getMessage());
        }
    }

}