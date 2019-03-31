package com.example.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import com.example.demo.dao.notesDAO;
import com.example.demo.entity.Notes;

public class DataInit implements ApplicationRunner {

  private notesDAO notes;

  @Autowired
  public DataInit(notesDAO notes) {
    this.notes = notes;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    // TODO Auto-generated method stub
    long count = notes.count();
    if (count == 0) {
      Notes note1 = new Notes();
      note1.setTitle("DummyTitle1");
      note1.setDescription("DummyDescription1");
      note1.setTenant("vignesh");
      notes.save(note1);

      Notes note2 = new Notes();
      note2.setTitle("DummyTitle2");
      note2.setDescription("DummyDescription2");
      notes.save(note2);

    }
  }

}
