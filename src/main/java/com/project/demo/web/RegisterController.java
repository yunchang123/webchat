package com.project.demo.web;

import com.project.demo.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.JsonObject;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "register")
    @ResponseBody
    public String doRegister(String email, String nickname, String password, String checkPassword, String captcha)
    {
        /*userService.register(email,nickname,password,checkPassword,captcha);
        return "register";*/

        try {
            userService.register(email,nickname,password,checkPassword,captcha);
            JsonObject json = new JsonObject();
            json.addProperty("msg","success");
            return json.toString();

        }catch (Exception e){
            e.printStackTrace();
            JsonObject json = new JsonObject();
            json.addProperty("msg","fail");
            return json.toString();
        }

    }
}


