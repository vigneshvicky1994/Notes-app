package com.example.demo.serviceinterface;

import com.example.demo.entity.User;

public interface UserService {
  void save(User user);

  User findByUsername(String username);
}
