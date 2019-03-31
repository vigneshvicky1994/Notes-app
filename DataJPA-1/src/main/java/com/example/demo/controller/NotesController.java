package com.example.demo.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.dao.notesDAO;
import com.example.demo.entity.Notes;

@Controller
public class NotesController {

  @Autowired
  private notesDAO noteDAO;

  private String tenant;

  @RequestMapping("/add")
  public String add(Model model) {
    model.addAttribute("note", new Notes());
    return "add-note";
  }

  @RequestMapping("/")
  public String refresh(Model model) {
    this.tenant = SecurityContextHolder.getContext().getAuthentication().getName();
    model.addAttribute("Notes", noteDAO.findByTenant(this.tenant));
    return "index";
  }

  @RequestMapping("/createNote")
  public String save(@Valid Notes note, BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "error";
    }
    this.tenant = SecurityContextHolder.getContext().getAuthentication().getName();
    note.setTenant(this.tenant);
    noteDAO.save(note);
    model.addAttribute("Notes", noteDAO.findByTenant(this.tenant));
    return "index";
  }

  @GetMapping("/editNote/{id}")
  public String showUpdateForm(@PathVariable("id") long id, Model model) {
    Notes note = noteDAO.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid note Id:" + id));
    model.addAttribute("note", note);
    return "update-note";
  }

  @RequestMapping("/updateNote/{id}")
  public String update(@PathVariable long id, @Valid Notes note, Model model,
      BindingResult result) {
    if (result.hasErrors()) {
      note.setId(id);
      return "update-note";
    }
    this.tenant = SecurityContextHolder.getContext().getAuthentication().getName();
    note.setTenant(this.tenant);
    noteDAO.save(note);
    model.addAttribute("Notes", noteDAO.findByTenant(this.tenant));
    return "index";
  }

  @RequestMapping("/deleteNote/{id}")
  public String delete(@PathVariable long id, Model model) {
    Notes note = noteDAO.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid note Id:" + id));
    noteDAO.delete(note);
    this.tenant = SecurityContextHolder.getContext().getAuthentication().getName();
    model.addAttribute("Notes", noteDAO.findByTenant(this.tenant));
    return "index";
  }

}
