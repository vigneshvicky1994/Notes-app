package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.User;

public interface UserDAO extends JpaRepository<User, Long> {

  User findByUsername(String username);

}
