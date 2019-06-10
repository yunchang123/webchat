package com.project.demo.dal.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepo extends JpaRepository<Message,Integer> {
}
