package com.vnexos.sema.module.authentication.repositories;

import java.util.UUID;

import com.vnexos.sema.database.helpers.DatabaseContext;
import com.vnexos.sema.module.authentication.data.User;

public interface IUserRepository extends DatabaseContext<User, UUID> {
  public User getByUsername(String username);

  public User getByEmail(String email);
}
