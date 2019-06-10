package com.project.demo.web;

import com.google.gson.JsonObject;
import com.project.demo.comon.AppConstants;
import com.project.demo.dal.entity.User;
import com.project.demo.service.FileService;
import com.project.demo.service.UserService;
import com.project.demo.web.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="login")
    @ResponseBody
    public String Login(LoginForm form,  HttpSession session){
        //登录操作
        //验证参数（用户名非空、长度大于6、不存在于数据库中、是邮箱或者手机号....）
        try {
            User user =  userService.login(form.getNickname(), form.getPassword());
            session.setAttribute(AppConstants.USER_INFO_SK,user);
        }catch (RuntimeException e){
            e.printStackTrace();
            if(e.getMessage().equals("USER_NOT_FOUND")){
                JsonObject json = new JsonObject();
                json.addProperty("msg","用户不存在或者密码不匹配");
                return json.toString();
                /*modelMap.put("msg","用户不存在或者密码不匹配");
                return "login";*/
            }
        }catch (Exception e){
            e.printStackTrace();
            JsonObject json = new JsonObject();
            json.addProperty("msg","系统异常");
            return json.toString();
            /*modelMap.put("msg","系统异常..");
            return "login";*/
        }
        return "success";
    }

}
