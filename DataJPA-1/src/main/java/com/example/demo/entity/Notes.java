package com.example.demo.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "Notes")
public class Notes {
  @Id
  @GeneratedValue
  @Column(name = "Id", nullable = false)
  private Long id;

  @NotBlank(message = "Title is mandatory")
  @Column
  private String title;

  @NotBlank(message = "Description is mandatory")
  @Column
  private String description;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column
  @CreationTimestamp
  private LocalDateTime createDateTime;

  @Column
  @UpdateTimestamp
  private LocalDateTime updateDateTime;

  @Column
  private String tenant;

  public String getTenant() {
    return tenant;
  }

  public void setTenant(String tenant) {
    this.tenant = tenant;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
