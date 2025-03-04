package com.datdev.utils;

import javax.servlet.http.HttpServletRequest;

public class MessageUtil {
    public  static  void showMessage(HttpServletRequest req){
        if (req.getParameter("message") != null) {
            String messageResponse = "";
            String alert = "";
            String message = req.getParameter("message");
            if (message.equals("insert_success")) {
                messageResponse = "insertSuccess";
                alert = "success";
            } else if (message.equals("update_success")) {
                messageResponse = "updateSuccess";
                alert = "success";
            } else if (message.equals("delete_success")) {
                messageResponse = "deleteSuccess";
                alert = "success";
            } else if (message.equals("error_success")) {
                messageResponse = "errorSuccess";
                alert = "success";
            }
            req.setAttribute("message", messageResponse);
            req.setAttribute("alert", alert);
        }


    }
}
