package com.example.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import com.example.demo.dao.UserDAO;
import com.example.demo.entity.User;

public class DataInit implements ApplicationRunner {

  private UserDAO userDAO;

  @Autowired
  public DataInit(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    long count = userDAO.count();
    if (count == 0) {
      User user = new User();
      user.setUsername("default");
      user.setPassword("Welcome1!");
      user.setPasswordConfirm("Welcome1!");
      userDAO.save(user);
    }

  }

}
