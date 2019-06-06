package com.project.demo.service.impl;

import com.project.demo.dal.entity.User;

public interface UserService {
    public void  register(String email, String nickname, String password, String checkPassword, String captcha);
    User login(String username, String password);
}
