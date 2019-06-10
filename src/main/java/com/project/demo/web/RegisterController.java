package com.project.demo.web;

import com.project.demo.dal.entity.User;
import com.project.demo.dal.UserRepo;
import com.project.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.JsonObject;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;

    @RequestMapping(value = "register")
    @ResponseBody
    public String doRegister(String email, String nickname, String password, String checkPassword, String captcha)
    {
        /*userService.register(email,nickname,password,checkPassword,captcha);
        return "register";*/

        try {
            userService.register(email,nickname,password,checkPassword,captcha);
            JsonObject json = new JsonObject();
            json.addProperty("state","success");
            return json.toString();

        }catch (Exception e){
            e.printStackTrace();
            JsonObject json = new JsonObject();
            json.addProperty("state","fail");
            return json.toString();
        }
    }

    @RequestMapping(value = "checkemail")
    @ResponseBody
    public String checkemail(String email){
        System.out.println(email);
        Optional<User>optional = Optional.ofNullable(userRepo.findByEmail(email));
        try {
           // System.out.println(userRepo.findByEmail(email));
            if(!optional.isPresent()){
                JsonObject json = new JsonObject();
                json.addProperty("msg","have not been registered");
                return json.toString();
            }else{
                JsonObject json = new JsonObject();
                json.addProperty("msg","have been registered");
                return json.toString();
            }
        }catch (Exception e){
            e.printStackTrace();
            JsonObject json = new JsonObject();
            json.addProperty("state","fail");
            return json.toString();
        }
    }
    @RequestMapping(value = "myinfo")
    @ResponseBody
    public String myinfo(HttpSession session){
        try {
            Integer userid= (Integer)(session.getAttribute("userid"));
            Optional<User> user1 = userRepo.findById(userid);
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


