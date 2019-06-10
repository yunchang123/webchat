package com.project.demo.dal;

import com.project.demo.dal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo  extends JpaRepository<User,Integer> {
    User findByNicknameAndPassword(String nickname,String password);
}