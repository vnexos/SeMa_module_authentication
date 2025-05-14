package com.vnexos.sema.module.authentication.data;

import java.time.LocalDate;
import java.util.UUID;

import com.vnexos.sema.database.annotations.Column;
import com.vnexos.sema.database.annotations.Entity;
import com.vnexos.sema.database.helpers.DefaultEntity;
import com.vnexos.sema.loader.annotations.Hidden;

@Entity(tableName = "Users")
public class User extends DefaultEntity {
  @Column(nullable = false)
  private String fullname;
  @Column(nullable = false)
  private String username;

  @Column(nullable = false)
  private String hashedPassword;

  @Column(nullable = false)
  private String phoneNumber;
  @Column(defaultValue = "0")
  private boolean phoneNumberConfirmed;
  @Column(nullable = false)
  private String email;
  @Column(defaultValue = "0")
  private boolean emailConfirmed;

  @Column(nullable = false, length = 2)
  private String countryCode;
  @Column
  private String address;
  @Column
  private String avatarUrl;
  @Column
  private LocalDate dateOfBirth;
  @Column(foreignKey = Gender.class)
  private UUID genderId;

  @Column(nullable = false, foreignKey = Role.class)
  private UUID roleId;

  @Column
  private String cardID;
  @Column
  private String issueDate;
  @Column
  private String issuePlace;

  @Column
  @Hidden
  private String accessToken;

  @Column
  @Hidden
  private String refreshToken;

  public void setHashedPassword(String hashedPassword) {
    this.hashedPassword = hashedPassword;
  }

  public String getHashedPassword() {
    return hashedPassword;
  }

  public void setRoleId(String roleId) {
    this.roleId = UUID.fromString(roleId);
  }
}
