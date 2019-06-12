package com.project.demo.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.project.demo.dal.entity.User;

public interface UserService {
    void  register(String email, String nickname, String password, String checkPassword, String captcha);
    User login(String email, String password);
    JsonObject modifyUserInfo(Integer userId, String email, String nickname, String sex, String avatar_type, String avatar);
    JsonArray userList();
}
