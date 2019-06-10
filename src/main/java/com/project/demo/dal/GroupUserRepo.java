package com.project.demo.dal;

import com.project.demo.dal.entity.GroupUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface GroupUserRepo extends JpaRepository<GroupUser,Integer> {
    ArrayList<GroupUser> findByUserId(Integer userId);
}
