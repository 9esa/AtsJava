package com.kirichenko.servlets;

import com.kirichenko.Entities.JournalCallsEntity;
import com.kirichenko.utils.HibernateSessionFactory;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 17.05.2018.
 */
public class ServletWhoCalls extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String sPhoneNumber = req.getParameter("numa");

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        String hql = "from DriverEntity s where s.phone = :phone";
        List listOfDrivers = session.createQuery(hql)
                .setParameter("phone", sPhoneNumber)
                .list();
        session.close();

        boolean bIsDriver = false;

        if (listOfDrivers.size() > 0) {
            bIsDriver = true;
        }

        String sAnswer = "";
        //todo убрать отрицание
        if (bIsDriver) {
            //Активация меню выбран водитель
            sAnswer = "{ \"returned_code\": 2}";
        } else {
            //Активация меню выбран клиент
            sAnswer = "{ \"returned_code\": 1}";
        }

        PrintWriter writer = resp.getWriter();
        writer.println(sAnswer);
        writer.flush();

        markCalls(sPhoneNumber);

    }

    private void markCalls(String sValuePhone) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        JournalCallsEntity journalCallsEntity = new JournalCallsEntity();
        journalCallsEntity.setPhoneCall(sValuePhone);
        journalCallsEntity.setDateCall(dateFormat.format(new Date()));
        journalCallsEntity.setDestinationCall("-");
        session.save(journalCallsEntity);

        session.getTransaction().commit();
        session.close();
    }

}