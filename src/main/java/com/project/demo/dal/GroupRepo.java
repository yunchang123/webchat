package com.project.demo.dal;

import com.project.demo.dal.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepo extends JpaRepository<Group,Integer> {

}
