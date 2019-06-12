package com.project.demo.web;

import com.project.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MessageController {

    @Autowired
    MessageService messageService;
    @RequestMapping("/chat/list")
    public String getMessageInfo(HttpServletRequest request)
    {
        int userId = (Integer)request.getSession().getAttribute("userid");
        System.out.println("userid   "+userId);
        System.out.println(messageService.getMessageInfo(userId).toString());
        return "asdf";
//        return messageService.getMessageInfo(userId).toString();
    }


}
