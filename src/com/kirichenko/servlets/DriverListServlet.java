package com.kirichenko.servlets;

import com.kirichenko.Entities.DriverEntity;
import com.kirichenko.dto.DriverDTO;
import com.kirichenko.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by user on 13.05.2018.
 */
public class DriverListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String data = null;

        String format = req.getParameter("format");
        String driverName = req.getParameter("driverName");

        if (format != null && format.equalsIgnoreCase("json")) {
            data = getClientAsJson(driverName);
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
        }

        PrintWriter writer = resp.getWriter();
        writer.println(data);
        writer.flush();

    }

    public static String getClientAsJson(String criteria) {
        String lowerPrefix = "";
        if (criteria != null && criteria.length() > 0) {
            lowerPrefix = criteria.toLowerCase();
        }

        TreeMap<String, DriverDTO> stateData = getStateData();
        JSONArray json = new JSONArray();

        Set<String> keySet = stateData.keySet();
        Iterator<String> ClientIter = keySet.iterator();

        while (ClientIter.hasNext()) {

            String curKey = ClientIter.next();
            DriverDTO curState = stateData.get(curKey);
            if (curState != null) {
                String curUserData = curState.getM_driverData().toLowerCase();

                if (curUserData.startsWith(lowerPrefix)) {
                    json.put(curState.getM_driverData());
                }
            }

        }
        return json.toString();
    }

    /**
     * Returns a TreeMap of StateDTO objects
     * @return TreeMap contains all state data
     */
    public static TreeMap<String, DriverDTO> getStateData() {

        TreeMap<String, DriverDTO> stateData = new TreeMap<String, DriverDTO>();

        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        Query query = session.createQuery("from DriverEntity ");

        ArrayList<DriverEntity> driverList = (ArrayList<DriverEntity>) query.getResultList();

        session.close();

        if (driverList == null) return stateData;

        for(DriverEntity oDriverEntity :driverList){
            DriverDTO driverDTO = new DriverDTO(String.valueOf(oDriverEntity.getIddriver()),
                    String.valueOf(oDriverEntity.getFirstName() + " "
                    + oDriverEntity.getSecondName() + " - [ "+ oDriverEntity.getPhone() + " ]"));
            stateData.put(String.valueOf(oDriverEntity.getIddriver()), driverDTO);
        }

        return stateData;
    }


}