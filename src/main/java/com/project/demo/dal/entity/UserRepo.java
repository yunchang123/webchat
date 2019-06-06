package com.project.demo.dal.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo  extends JpaRepository<User,Integer> {
    User findByNicknameAndPassword(String nickname,String password);
}