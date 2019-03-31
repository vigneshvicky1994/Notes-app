package com.example.demo.service;

import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.dao.RoleDAO;
import com.example.demo.dao.UserDAO;
import com.example.demo.entity.User;
import com.example.demo.serviceinterface.UserService;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDAO userDAO;
  @Autowired
  private RoleDAO roleDAO;
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public void save(User user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    user.setRoles(new HashSet<>(roleDAO.findAll()));
    userDAO.save(user);
  }

  @Override
  public User findByUsername(String username) {
    return userDAO.findByUsername(username);
  }

}
