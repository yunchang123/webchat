package com.project.demo.web;

import com.project.demo.comon.AppConstants;
import com.project.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class MessageController {
    @Autowired
    MessageService messageService;
    @RequestMapping("chat/list")
    public String getMessageInfo(HttpSession session)
    {
        int userId = (Integer)session.getAttribute(AppConstants.USER_ID_SK);
        return messageService.getMessageInfo(userId).toString();
    }
}
