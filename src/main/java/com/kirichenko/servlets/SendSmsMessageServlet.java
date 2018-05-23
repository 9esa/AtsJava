package com.kirichenko.servlets;

import com.kirichenko.dto.SmsDTO;
import com.kirichenko.utils.SmsConnectionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 17.05.2018.
 */
public class SendSmsMessageServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //http://localhost:8080/SendTryLogin?Receiver="+79818926024"&Message=Завтра доставка
        SmsDTO smsDTO = new SmsDTO();
        smsDTO.setSender("Atg-gaz");
        smsDTO.setReceiver(request.getParameter("Receiver"));
        smsDTO.setText(request.getParameter("Message"));
        // "Доставка газа на сегодня"
        SmsConnectionService.getInterfaceRegistrationCode().smsNotification(smsDTO).execute();

    }
}