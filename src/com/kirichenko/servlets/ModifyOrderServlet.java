package com.kirichenko.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 09.05.2018.
 */
public class ModifyOrderServlet extends HttpServlet {

    public static int iId = -1;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        iId = Integer.valueOf(request.getParameter("id"));

        RequestDispatcher dispatcher =
                this.getServletContext().getRequestDispatcher("/WEB-INF/views/modification_order.jsp");
        dispatcher.forward(request, response);
    }
}