package com.kirichenko.servlets;

import com.kirichenko.Entities.DeliveryAddressEntity;
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
 * Created by user on 22.04.2018.
 */
public class AddressServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        RequestDispatcher dispatcher =
                this.getServletContext().getRequestDispatcher("/WEB-INF/views/addresses.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    public static List<DeliveryAddressEntity> loadData() {

        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();

            Query query = session.createQuery("from DeliveryAddressEntity ");

            List deliveryAddressList = query.getResultList();

            session.close();

            return deliveryAddressList;

        } catch (Exception ex) {
            return new ArrayList();
        }
    }

    public static List<DeliveryAddressEntity> loadData(int clientId) {
        return new ArrayList<DeliveryAddressEntity>();
    }
}