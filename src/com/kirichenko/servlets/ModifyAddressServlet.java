package com.kirichenko.servlets;

import com.kirichenko.Entities.ClientEntity;
import com.kirichenko.Entities.DeliveryAddressEntity;
import com.kirichenko.utils.HibernateSessionFactory;
import org.hibernate.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 09.05.2018.
 */
public class ModifyAddressServlet extends HttpServlet {

    public static int iId = -1;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sAddress = request.getParameter("j_address");
        String sClient = request.getParameter("j_client");

        if (sAddress == null && sClient == null) {

            iId = Integer.valueOf(request.getParameter("id"));

            RequestDispatcher dispatcher =
                    this.getServletContext().getRequestDispatcher("/WEB-INF/views/modification_address.jsp");
            dispatcher.forward(request, response);

        } else {

            try {

                if (sAddress != null && sClient != null) {
                    Session session = HibernateSessionFactory.getSessionFactory().openSession();

                    DeliveryAddressEntity deliveryAddressEntity = new DeliveryAddressEntity();
                    deliveryAddressEntity.setAddress(sAddress);
                    deliveryAddressEntity.setIsMain("false");

                    ClientEntity oClientEntity = new ClientEntity();
                    oClientEntity.setIdclient(Integer.valueOf(sClient));
                    deliveryAddressEntity.setClientByClientId(oClientEntity);

                    session.beginTransaction();

                    session.save(deliveryAddressEntity);

                    session.getTransaction().commit();

                    response.sendRedirect(request.getContextPath() + "/Addresses");

                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }


    }
}