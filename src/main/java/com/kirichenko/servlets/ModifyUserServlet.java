package com.kirichenko.servlets;

import com.kirichenko.Entities.UsersEntity;
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
import java.util.Random;

/**
 * Created by user on 16.05.2018.
 */
public class ModifyUserServlet extends HttpServlet {

    public static int iId = -1;

    public static int iValueForCheck;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("deleteId") != null) {
            deleteUser(Integer.valueOf(request.getParameter("deleteId")));
            response.sendRedirect(request.getContextPath() + "/UsersList");
        } else {

            if(iValueForCheck == 0)
              iValueForCheck = new Random().nextInt(5000)+ 4999;

            if (request.getParameter("id") != null)
                iId = Integer.valueOf(request.getParameter("id"));

            if (request.getParameter("action") != null) {
                String sFirstName = request.getParameter("j_firstname");
                String sSecondName = request.getParameter("j_secondname");
                String sPhone = request.getParameter("j_phone");
                String sLogin = request.getParameter("j_login");
                String sPass = request.getParameter("j_pass");
                String sPassAgain = request.getParameter("j_pass_again");

                if (request.getParameter("action").equals("create")) {
                    System.out.println("Создан");
                    createUser(sFirstName, sSecondName, sPhone, sLogin, sPass, sPassAgain);
                } else {
                    updateUser(sFirstName, sSecondName, sPhone, sLogin, sPass, sPassAgain);
                    System.out.println("Обновлен");
                }
                response.sendRedirect(request.getContextPath() + "/UsersList");
            } else {
                RequestDispatcher dispatcher =
                        this.getServletContext().getRequestDispatcher("/WEB-INF/views/modification_user.jsp");
                dispatcher.forward(request, response);
            }


        }

    }

    public static ArrayList<UsersEntity> loadUserInformation(int iId) {

        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();

            Query query = session.createQuery("from UsersEntity ue where ue.id =:iUserId ");
            query.setParameter("iUserId", iId);

            List userList = query.getResultList();

            session.close();

            return (ArrayList<UsersEntity>) userList;

        } catch (Exception ex) {
            return new ArrayList();
        }
    }


    private void updateUser(String sFirstName, String sSecondName, String sPhone,
                            String sLogin, String sPass, String sPassAgain) {

        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setIdusers(iId);
        usersEntity.setFirstName(sFirstName);
        usersEntity.setSecondName(sSecondName);
        usersEntity.setPhone(sPhone);
        usersEntity.setLogin(sLogin);
        usersEntity.setPassword(sPass);
        session.update(usersEntity);

        session.getTransaction().commit();
        session.close();

    }

    private void createUser(String sFirstName, String sSecondName, String sPhone,
                            String sLogin, String sPass, String sPassAgain) {

        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setFirstName(sFirstName);
        usersEntity.setSecondName(sSecondName);
        usersEntity.setPhone(sPhone);
        usersEntity.setLogin(sLogin);
        usersEntity.setPassword(sPass);
        session.save(usersEntity);

        session.getTransaction().commit();
        session.close();
    }

    private void deleteUser(int iIdDelete) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setIdusers(iIdDelete);
        session.delete(usersEntity);

        session.getTransaction().commit();
        session.close();
    }

}