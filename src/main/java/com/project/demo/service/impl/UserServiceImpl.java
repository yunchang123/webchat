package com.project.demo.service.impl;

import com.project.demo.dal.entity.User;
import com.project.demo.dal.UserRepo;
import com.project.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if(user==null){
            throw new RuntimeException("USER_NOT_FOUND");
        }
        return user;
    }
}
