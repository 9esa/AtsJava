package com.kirichenko.servlets;

import com.kirichenko.Entities.DriverEntity;
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
 * Created by user on 06.05.2018.
 */
public class ModifyDriverServlet extends HttpServlet {

    public static int iId = -1;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/DriverList");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sFirst = request.getParameter("j_firstname");
        String sSecond = request.getParameter("j_secondname");
        String sPhone = request.getParameter("j_phone");

        if (sFirst == null && sSecond == null && sPhone == null) {

            iId = Integer.valueOf(request.getParameter("id"));

            RequestDispatcher dispatcher =
                    this.getServletContext().getRequestDispatcher("/WEB-INF/views/modification_driver.jsp");
            dispatcher.forward(request, response);


        } else {

            try {

                if (sFirst != null && sSecond != null && sPhone != null) {
                    Session session = HibernateSessionFactory.getSessionFactory().openSession();

                    session.beginTransaction();

                    DriverEntity driverEntity;

                    if (iId != -1) {
                        driverEntity = loadDriverInformation(iId).get(0);
                    } else {
                        driverEntity = new DriverEntity();
                    }

                    driverEntity.setRemove(false);
                    driverEntity.setSecondName(sSecond);
                    driverEntity.setFirstName(sFirst);
                    driverEntity.setPhone(sPhone);

                    if (iId != -1) {
                        session.update(driverEntity);
                    } else {
                        session.save(driverEntity);
                    }

                    session.getTransaction().commit();

                    response.sendRedirect(request.getContextPath() + "/DriverList");

                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static ArrayList<DriverEntity> loadDriverInformation(int iDriverId) {

        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();

            Query query = session.createQuery("from DriverEntity de where de.id =:iDriverId ");
            query.setParameter("iDriverId", iDriverId);

            List userList = query.getResultList();

            session.close();

            return (ArrayList<DriverEntity>) userList;

        } catch (Exception ex) {
            return new ArrayList();
        }
    }

}