package com.kirichenko.servlets;

import com.kirichenko.Entities.DeliveryAddressEntity;
import com.kirichenko.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 09.05.2018.
 */
public class DeleteAddressServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sId = request.getParameter("id");
        removeAddress(sId);
        response.sendRedirect("Addresses");
    }

    public static void removeAddress(String sIdAddress){
        int id = Integer.valueOf(sIdAddress);

        try {

            Session session = HibernateSessionFactory.getSessionFactory().openSession();

            session.beginTransaction();

            Query query = session.createQuery(
                    " DELETE From DeliveryAddressEntity dae where dae.iddeliveryAddress=:idAddress");
            query.setParameter("idAddress", id);

            DeliveryAddressEntity deliveryAddressEntity = new DeliveryAddressEntity();
            deliveryAddressEntity.setIddeliveryAddress(id);

            session.delete(deliveryAddressEntity);

            session.getTransaction().commit();

            session.close();

        }catch (Exception ex){
            System.out.println( ex.getMessage());
        }
    }

}