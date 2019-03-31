package com.example.demo.serviceinterface;

public interface SecurityService {
  String findLoggedInUsername();

  void autoLogin(String username, String password);
}
