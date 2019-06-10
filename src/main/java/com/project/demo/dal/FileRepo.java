package com.project.demo.dal;

import com.project.demo.dal.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FileRepo extends JpaRepository<Message,Integer> {
}
