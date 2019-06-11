package com.project.demo.web;

import com.google.gson.JsonObject;
import com.project.demo.comon.AppConstants;
import com.project.demo.dal.UserRepo;
import com.project.demo.dal.entity.User;
import com.project.demo.service.UserService;
import com.project.demo.web.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;

    @RequestMapping(value="login")
    @ResponseBody
    public String Login(LoginForm form,  HttpSession session){
        //登录操作
        //验证参数（用户名非空、长度大于6、不存在于数据库中、是邮箱或者手机号....）
        try {
            User user =  userService.login(form.getEmail(), form.getPassword());
            session.setAttribute(AppConstants.USER_ID_SK,user.getUserId());
            JsonObject json = new JsonObject();
            json.addProperty("state","success");
            json.addProperty("msg","登录成功");
            return json.toString();

        }catch (Exception e){
            e.printStackTrace();
            JsonObject json = new JsonObject();
            json.addProperty("state","fail");
            json.addProperty("msg","用户不存在或者密码不匹配");
            return json.toString();
        }
    }
    @RequestMapping(value = "myinfo")
    @ResponseBody
    public String myinfo(HttpSession session){
        try {
            Integer userid= (Integer)(session.getAttribute(AppConstants.USER_ID_SK));
            Optional<User> user1= userRepo.findById(userid);
            JsonObject json = new JsonObject();
            JsonObject json1 = new JsonObject();
            json.addProperty("state","success");
            json1.addProperty("userId",user1.get().getUserId());
            json1.addProperty("nickname",user1.get().getNickname());
            json1.addProperty("avatar",user1.get().getAvatar());
            json1.addProperty("sex",user1.get().getSex());
            json1.addProperty("avatarType",user1.get().getAvatarType());
            json.addProperty("data", json1.toString());
            return json.toString();
        }catch (Exception e){
            e.printStackTrace();
            JsonObject json = new JsonObject();
            json.addProperty("state","fail");
            return json.toString();
        }
    }

}
