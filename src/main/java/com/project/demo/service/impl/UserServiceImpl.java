package com.project.demo.service.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.project.demo.dal.entity.User;
import com.project.demo.dal.UserRepo;
import com.project.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public void register(String email, String nickname, String password, String checkPassword, String captcha) {
        User user = new User();
        user.setEmail(email);
        user.setNickname(nickname);
        user.setPassword(password);
        try{
            if(password.equals(checkPassword)){
                userRepo.save(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public User login(String email, String password) {
        User user = userRepo.findByEmailAndPassword(email,password);

//        if(user==null){
//            throw new RuntimeException("USER_NOT_FOUND");
//        }
//        if(user.getVerified()==false){
//            throw new RuntimeException("USER_NOT_Verified");
//        }
        return user;
    }

    @Override
    public JsonObject modifyUserInfo(Integer userId, String email, String nickname, String sex, String avatar_type, String avatar) {
        JsonObject object = new JsonObject();
        try{
            Optional<User> user = userRepo.findById(userId);
            User modifyUser = user.get();
            modifyUser.setEmail(email);
            modifyUser.setNickname(nickname);
            modifyUser.setSex(sex);
            modifyUser.setAvatarType(avatar_type);
            modifyUser.setAvatar(avatar);
            userRepo.save(modifyUser);
            object.addProperty("state","success");
            return object;

        }catch (Exception ex)
        {
            object.addProperty("state","fail");
            return object;
        }
    }

    @Override
    public JsonArray userList(Integer userId) {

        ArrayList<User>userList = userRepo.findByVerifiedNotAndUserIdNot(false,userId);
        JsonArray array = new JsonArray();
        for(User user:userList)
        {
            JsonObject object = new JsonObject();
            object.addProperty("userId",user.getUserId());
            object.addProperty("nickname",user.getNickname());
            object.addProperty("avatar",user.getAvatar());
            object.addProperty("avatarType",user.getAvatarType());
            array.add(object);
        }

        return array;
    }
}
