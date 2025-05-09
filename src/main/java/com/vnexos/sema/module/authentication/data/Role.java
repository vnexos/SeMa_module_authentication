package com.vnexos.sema.module.authentication.data;

import java.util.UUID;

import com.vnexos.sema.database.annotations.Column;
import com.vnexos.sema.database.annotations.Entity;
import com.vnexos.sema.database.helpers.DefaultEntity;

@Entity(tableName = "Roles")
public class Role extends DefaultEntity {
  @Column(nullable = false)
  private String name;

  public Role(String id, String name) {
    if (id != null)
      setId(UUID.fromString(id));
    this.name = name;
  }

  public Role(String name) {
    this(null, name);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
