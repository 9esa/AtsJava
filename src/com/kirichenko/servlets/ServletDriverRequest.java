package com.kirichenko.servlets;

import com.kirichenko.Entities.DaysEntity;
import com.kirichenko.Entities.JournalCallsEntity;
import com.kirichenko.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

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
 * Created by user on 20.05.2018.
 */
public class ServletDriverRequest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //http://localhost:8080/driverRequest?numa=79818926024&input_result=1#

        String sPhoneNumber = req.getParameter("numa");
        String sInputResult = req.getParameter("input_result");
        String sAnswer = "";

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        String hql = "from DriverEntity s where s.phone = :phone";
        List listOfDrivers = session.createQuery(hql)
                .setParameter("phone", sPhoneNumber)
                .list();
        session.close();

        if (listOfDrivers.size() > 0) {
            DaysEntity oDaysEntity = getCurrentIdDay();

            if (oDaysEntity.getIddays() == -1) {
                sAnswer = "{ \"returned_code\": 1}";
            } else {
                String sPhone = "79818926024";
                sAnswer = "{ \"text\": \"Устанавливаю соединение\", " +
                        "\"phones\":[\"" + sPhone + "\"]}";
            }
        } else {
            sAnswer = "{ \"returned_code\": 1}";
        }

        PrintWriter writer = resp.getWriter();
        writer.println(sAnswer);
        writer.flush();

    }

    private DaysEntity getCurrentIdDay() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        Query query = session.createQuery("from DaysEntity de where de.currentDate= :currentDate");
        query.setParameter("currentDate", dateFormat.format(new Date()));

        List listOfDays = query.getResultList();
        session.close();

        if (listOfDays.size() > 0) {
            return (DaysEntity) listOfDays.get(0);
        } else {
            return new DaysEntity(-1);
        }
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