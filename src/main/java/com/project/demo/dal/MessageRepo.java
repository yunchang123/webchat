package com.project.demo.dal;

import com.project.demo.dal.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface MessageRepo extends JpaRepository<Message,Integer> {
    List<Message> findByGroupIdOrderByTimeDesc(Integer groupId);
    ArrayList<Message> findByGroupId(Integer groupId);
}
