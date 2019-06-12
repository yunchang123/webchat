package com.project.demo.dal;

import com.project.demo.dal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface UserRepo extends JpaRepository<User,Integer> {
    User findByEmailAndPassword(String email,String password);
    User findByEmail(String email);
    ArrayList<User> findByVerifiedNot(boolean verified);
}
