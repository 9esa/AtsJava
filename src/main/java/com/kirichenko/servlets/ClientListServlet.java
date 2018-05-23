package com.kirichenko.servlets;

import com.kirichenko.Entities.ClientEntity;
import com.kirichenko.dto.ClientDTO;
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
 * Created by user on 12.05.2018.
 */
public class ClientListServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String data = null;

        String format = req.getParameter("format");
        String clientName = req.getParameter("clientName");

        // If format is not null and it's 'json'
        if (format != null && format.equalsIgnoreCase("json")) {
            data = getClientAsJson(clientName);
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
        }

        // Запись ответа
        // Получение механизма записи ответа сервлета
        PrintWriter writer = resp.getWriter();
        writer.println(data);
        writer.flush();

    }

    public static String getClientAsJson(String criteria) {
        String lowerPrefix = "";
        if (criteria != null && criteria.length() > 0) {
            lowerPrefix = criteria.toLowerCase();
        }

        TreeMap<String, ClientDTO> stateData = getStateData();
        JSONArray json = new JSONArray();

        Set<String> keySet = stateData.keySet();
        Iterator<String> ClientIter = keySet.iterator();

        while (ClientIter.hasNext()) {

            String curKey = ClientIter.next();
            ClientDTO curState = stateData.get(curKey);
            if (curState != null) {
                String curUserData = curState.getM_userData().toLowerCase();

                if (curUserData.startsWith(lowerPrefix)) {
                    json.put(curState.getM_userData());
                }
            }

        }
        return json.toString();
    }

    /**
     * Returns a TreeMap of StateDTO objects
     * @return TreeMap contains all state data
     */
    public static TreeMap<String, ClientDTO> getStateData() {

        TreeMap<String, ClientDTO> stateData = new TreeMap<String, ClientDTO>();

        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        Query query = session.createQuery("from ClientEntity ");

        ArrayList<ClientEntity> clientList = (ArrayList<ClientEntity>) query.getResultList();

        session.close();

        if (clientList == null) return stateData;

        for(ClientEntity oClientEntity :clientList){
            ClientDTO clientDTO = new ClientDTO(String.valueOf(oClientEntity.getIdclient()),String.valueOf(oClientEntity.getFirstName() + " "
                    + oClientEntity.getSecondName() + " - [ "+ oClientEntity.getPhone() + " ]"));
            stateData.put(String.valueOf(oClientEntity.getIdclient()), clientDTO);
        }

        return stateData;
    }


}
