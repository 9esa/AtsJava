package com.kirichenko.servlets;

import com.kirichenko.Entities.ClientEntity;
import com.kirichenko.Entities.DeliveryAddressEntity;
import com.kirichenko.dto.AddressDTO;
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
 * Created by user on 09.05.2018.
 */
public class AddressForClientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String data = null;

        String format = req.getParameter("format");
        String addressName = req.getParameter("addressName");
        String clientName = req.getParameter("clientName");

        // If format is not null and it's 'json'
        if (format != null && format.equalsIgnoreCase("json")) {
            data = getAddressAsJson(addressName, clientName);
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
        }

        String sClientId = req.getParameter("clientId");

//        if (format != null &amp; format.equalsIgnoreCase("json")) {
//            // Получение название города в зависимости от штата и префикса
//            // названия города
//            data = LocationService.getCitiesAsJson(cityName, stateName);
//            data = "{'Denis','Sasha'}";
//            resp.setContentType("text/plain");
////        }
        // Запись ответа
        // Получение механизма записи ответа сервлета
        PrintWriter writer = resp.getWriter();
        writer.println(data);
        writer.flush();

    }

    public static String getAddressAsJson(String criteria, String clientName) {
        String lowerPrefix = "";
        if (criteria != null && criteria.length() > 0) {
            lowerPrefix = criteria.toLowerCase();
        }

        TreeMap<String, AddressDTO> stateData = getStateData(clientName);
        JSONArray json = new JSONArray();

        // Iterate through state data
        Set<String> keySet = stateData.keySet();
        Iterator<String> addressIter = keySet.iterator();

        while (addressIter.hasNext()) {
            // Get current state
            String curKey = addressIter.next();
            AddressDTO curState = stateData.get(curKey);
            if (curState != null) {
                String curName = curState.getM_address().toLowerCase();

                if (curName.startsWith(lowerPrefix)) {
                    json.put(curState.getM_address());
                }
            }
        }
        return json.toString();
    }


    /**
     * Returns a TreeMap of StateDTO objects
     *
     * @return TreeMap contains all state data
     */
    public static TreeMap<String, AddressDTO> getStateData(String clientName) {

        TreeMap<String, AddressDTO> stateData = new TreeMap<String, AddressDTO>();

        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        String sValuePhone = (clientName.substring(clientName.indexOf("[") + 1, clientName.indexOf("]"))).trim();

        Query query = session.createQuery("from ClientEntity ce where ce.phone=:iphone");
        query.setParameter("iphone", sValuePhone);

        ArrayList<ClientEntity> clientList = (ArrayList<ClientEntity>) query.getResultList();

        ArrayList<DeliveryAddressEntity> driverList;

        if (clientList.size() > 0) {
            query = session.createQuery("from DeliveryAddressEntity dae where dae.clientByClientId = :client");
            query.setParameter("client", clientList.get(0));

            driverList = (ArrayList<DeliveryAddressEntity>) query.getResultList();

        } else {
            driverList = new ArrayList<DeliveryAddressEntity>();
        }

        session.close();

        if (driverList == null) return stateData;

        for (DeliveryAddressEntity oDeliveryAddressEntity : driverList) {
            AddressDTO addressDTO = new AddressDTO(oDeliveryAddressEntity.getAddress(), String.valueOf(oDeliveryAddressEntity.getClientByClientId().getIdclient()));
            stateData.put(String.valueOf(String.valueOf(oDeliveryAddressEntity.getClientByClientId().getIdclient())), addressDTO);
        }

        return stateData;
    }

}
