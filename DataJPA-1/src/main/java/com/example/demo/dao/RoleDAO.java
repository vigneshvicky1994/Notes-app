package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Role;

public interface RoleDAO extends JpaRepository<Role, Long> {

}
