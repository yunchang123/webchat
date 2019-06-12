//package com.project.demo.web;
//
//import com.google.gson.JsonObject;
//import com.project.demo.comon.AppConstants;
//import com.project.demo.service.MessageService;
//import com.project.demo.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpSession;
//import java.util.Date;
//
//@RestController
//public class MessageController {
//
//    @Autowired
//    MessageService messageService;
//    @Autowired
//    UserService userService;
//    @GetMapping("/chat/list")
//    public String getMessageInfo(HttpSession session)
//    {
//        JsonObject object = new JsonObject();
//        try {
//            int userId = (Integer) session.getAttribute(AppConstants.USER_ID_SK);
//            System.out.println("userid   "+userId);
//            System.out.println(messageService.getMessageInfo(userId).toString());
//            return messageService.getMessageInfo(userId).toString();
//        }
//        catch (Exception ex)
//        {
//            object.addProperty("state","fail");
//            return object.toString();
//        }
//
//    }
//
//    @RequestMapping("newchat")
//
//    public String newPrivateChat(Integer userId1,Integer userId2)
//    {
//        return messageService.newPrivateChat(userId1,userId2).toString();
//    }
//
//    @RequestMapping("sendmessage")
//    public String sendMessage(Integer userId,Integer groupId,String content, String type,long time)
//    {
//        Date date = new Date();
//        date.setTime(time);
//
//        return messageService.sendMessage(userId, groupId, content,type,date).toString();
//    }
//
//    @RequestMapping("/chat/record")
//    public String chatRecords(Integer groupId)
//    {
//        return messageService.chatRecords(groupId).toString();
//    }
//
//    @RequestMapping("/newgroup")
//    public String newPublicChat(String groupName, Integer[] users)
//    {
//        return messageService.newPublicChat(groupName,users).toString();
//    }
//
//    @RequestMapping("userlist")
//    public String userList(HttpSession session)
//    {
//        int userId = (Integer) session.getAttribute(AppConstants.USER_ID_SK);
//        return userService.userList(userId).toString();
//    }
//}
