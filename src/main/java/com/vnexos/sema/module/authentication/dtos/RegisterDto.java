package com.vnexos.sema.module.authentication.dtos;

public class RegisterDto {

  private String fullname;
  private String username;
  private String password;
  private String phoneNumber;
  private String email;
  private String countryCode;

  public String getFullname() {
    return fullname;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public String getCountryCode() {
    return countryCode;
  }
}
