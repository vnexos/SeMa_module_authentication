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
  @Column(nullable = false)
  private boolean phoneNumberConfirmed;
  @Column(nullable = false)
  private String email;
  @Column(nullable = false)
  private boolean emailConfirmed;

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
}
