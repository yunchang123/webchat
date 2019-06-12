package com.project.demo.web;

import com.project.demo.comon.AppConstants;
import com.project.demo.dal.entity.User;
import com.project.demo.dal.UserRepo;
import com.project.demo.service.UserService;
import com.project.demo.web.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.JsonObject;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class SignUpInController {

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

    @RequestMapping(value="login")
    @ResponseBody
    public String Login(LoginForm form, HttpSession session){
        //登录操作
        //验证参数（用户名非空、长度大于6、不存在于数据库中、是邮箱或者手机号....）
        try {
            User user =  userService.login(form.getEmail(), form.getPassword());
            if (user==null){
                JsonObject json = new JsonObject();
                json.addProperty("state","fail");
                json.addProperty("msg","用户不存在或密码不正确");
                return json.toString();
            }
            if(user.getVerified()==false){
                JsonObject json = new JsonObject();
                json.addProperty("state","fail");
                json.addProperty("msg","用户未通过验证");
                return json.toString();
            }
            session.setAttribute(AppConstants.USER_ID_SK,user.getUserId());
            JsonObject json = new JsonObject();
            json.addProperty("state","success");
            json.addProperty("msg","登录成功");
            return json.toString();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}


