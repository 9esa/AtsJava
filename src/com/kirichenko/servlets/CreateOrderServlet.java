package com.kirichenko.servlets;

import com.kirichenko.Entities.*;
import com.kirichenko.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by user on 13.05.2018.
 */
public class CreateOrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sClient = request.getParameter("client");
        String sAddress = request.getParameter("address");
        String sDriver = request.getParameter("driver");
        String sDateDelivery = request.getParameter("j_date_delivery");
        String sVolumeDelivery = request.getParameter("j_volume_delivery");
        String sPriceDelivery = request.getParameter("j_price_delivery");
        String sComment = request.getParameter("j_commentary");

        String sId = request.getParameter("id");

        createOrder(sClient, sAddress, sDriver, sDateDelivery, sVolumeDelivery, sPriceDelivery, sComment);

        RequestDispatcher dispatcher =
                this.getServletContext().getRequestDispatcher("/WEB-INF/views/orders.jsp");
        dispatcher.forward(request, response);

    }


    private void createOrder(String sClient, String sAddress, String sDriver,
                             String sDateDelivery, String sVolumeDelivery, String sPriceDelivery,
                             String sComment) {
        try {

            Session session = HibernateSessionFactory.getSessionFactory().openSession();

            session.beginTransaction();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            BillEntity billEntity = new BillEntity();
            billEntity.setPrice(Integer.valueOf(sPriceDelivery));
            billEntity.setAmount(Integer.valueOf(sVolumeDelivery));
            billEntity.setComment(sComment);
            billEntity.setBillDay(dateFormat.format(new Date()));
            billEntity.setDaysByIdDate(getDate(session, sDateDelivery));

            DeliveryAddressEntity oDeliveryAddressEntity = new DeliveryAddressEntity();

            Query query = session.createQuery("from DeliveryAddressEntity dae where dae.address=:address");
            query.setParameter("address", sAddress);

            ArrayList<DeliveryAddressEntity> deliveryAddress = (ArrayList<DeliveryAddressEntity>) query.getResultList();
            oDeliveryAddressEntity = deliveryAddress.get(0);

            billEntity.setDeliveryAddressByIdDeliveryAddress(oDeliveryAddressEntity);

            int iBillId = (Integer)session.save(billEntity);

            billEntity.setBillFordriverByIdBillForDriver( connectDriver(session, iBillId, billEntity, sDriver));

            session.getTransaction().commit();

            session.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private BillFordriverEntity connectDriver(Session session,  int iBillId, BillEntity billEntity , String sDriver) {
        Query query = session.createQuery("from DriverEntity de where de.phone=:phone");
        query.setParameter("phone", (sDriver.substring(sDriver.indexOf("[") +1, sDriver.indexOf("]"))).trim());

        ArrayList<DriverEntity> driverEntities = (ArrayList<DriverEntity>) query.getResultList();

        query = session.createQuery("from BillEntity be where be.daysByIdDate=:deliveryDate and be.billFordriverByIdBillForDriver.driverByIdDriver=:idDriver");
        query.setParameter("deliveryDate", billEntity.getDaysByIdDate());
        query.setParameter("idDriver", driverEntities.get(0));

        ArrayList<BillFordriverEntity> billFordriverEntitiesList = (ArrayList<BillFordriverEntity>) query.getResultList();

        BillFordriverEntity billFordriverEntity = new BillFordriverEntity();
        billFordriverEntity.setDriverByIdDriver(driverEntities.get(0));
        billFordriverEntity.setTimeForDay(billFordriverEntitiesList.size() + 1);
        billFordriverEntity.setIdBill(iBillId);
        session.save(billFordriverEntity);
        return billFordriverEntity;
    }


    private DaysEntity getDate(Session session, String sDateDelivery) {

        Query query = session.createQuery("from DaysEntity dae where dae.currentDate=:currentDate");
        query.setParameter("currentDate", sDateDelivery);

        ArrayList<DaysEntity> daysEntities = (ArrayList<DaysEntity>) query.getResultList();

        if (daysEntities.size() > 0) {
            return daysEntities.get(0);
        } else {
            DaysEntity oDaysEntity = new DaysEntity(sDateDelivery);
            session.save(oDaysEntity);
            return getDate(session, sDateDelivery);
        }

    }

}
