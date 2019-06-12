package com.project.demo.web;

import com.google.gson.JsonObject;
import com.project.demo.comon.AppConstants;
import com.project.demo.dal.UserRepo;
import com.project.demo.dal.entity.User;
import com.project.demo.service.MessageService;
import com.project.demo.service.UserService;
import com.project.demo.web.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;
    @RequestMapping(value = "myinfo")
    public String myinfo(HttpSession session) {
        try {
            Integer userid = (Integer) (session.getAttribute(AppConstants.USER_ID_SK));
            Optional<User> user1 = userRepo.findById(userid);
            JsonObject json = new JsonObject();
            JsonObject json1 = new JsonObject();
            json.addProperty("state", "success");
            json1.addProperty("userId", user1.get().getUserId());
            json1.addProperty("email", user1.get().getEmail());
            json1.addProperty("nickname", user1.get().getNickname());
            json1.addProperty("avatar", user1.get().getAvatar());
            json1.addProperty("sex", user1.get().getSex());
            json1.addProperty("avatarType", user1.get().getAvatarType());
            json.addProperty("data", json1.toString());
            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
            JsonObject json = new JsonObject();
            json.addProperty("state", "fail");
            return json.toString();
        }
    }

    @RequestMapping("logout")
    public String logout(HttpSession session)
    {
        JsonObject object = new JsonObject();
        try{
            session.invalidate();
            object.addProperty("state","success");
            return object.toString();
        }
        catch (Exception ex)
        {
            object.addProperty("state","fail");
            return object.toString();
        }
    }

    @RequestMapping("modifyuserinfo")
    public String modifyUserInfo(HttpSession session, String email, String nickname, String sex, String avatarType, String avatar)
    {
        Integer userid = (Integer) (session.getAttribute(AppConstants.USER_ID_SK));
        return userService.modifyUserInfo( userid,  email,  nickname,  sex,  avatarType,  avatar).toString();
    }



}
