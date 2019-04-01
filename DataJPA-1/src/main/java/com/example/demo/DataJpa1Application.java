package com.example.demo;

import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.demo.dao.RoleDAO;
import com.example.demo.dao.UserDAO;
import com.example.demo.entity.User;

@SpringBootApplication
public class DataJpa1Application extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(DataJpa1Application.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(DataJpa1Application.class, args);
  }

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  private RoleDAO roleDAO;

  @Bean
  CommandLineRunner init(UserDAO userDAO) {
    return args -> {
      User user = new User();
      user.setUsername("default");
      user.setPassword(bCryptPasswordEncoder.encode("Welcome1!"));
      user.setRoles(new HashSet<>(roleDAO.findAll()));
      // userDAO.save(user);
    };
  }

}
