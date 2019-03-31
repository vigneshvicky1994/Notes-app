package com.example.demo.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Notes;

@Repository
public interface notesDAO extends CrudRepository<Notes, Long> {
  public List<Notes> findByTenant(String tenant);

}
