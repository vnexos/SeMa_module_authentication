package com.vnexos.sema.module.authentication.seeders;

import com.vnexos.sema.loader.annotations.AutoWired;
import com.vnexos.sema.loader.annotations.Service;
import com.vnexos.sema.module.authentication.AuthenticationModuleMain;
import com.vnexos.sema.module.authentication.data.Role;
import com.vnexos.sema.module.authentication.repositories.IRoleRepository;

@Service
public class RoleSeeder {
  @AutoWired
  private IRoleRepository roleRepository;

  private static Role[] roles = {
      new Role("3a0ee0a6-a094-4617-8d8d-d5f96d3b6466", "SYSTEM_ROLE_ADMIN"),
      new Role("6ef803a7-0a0e-4270-b3e7-4bc27c3c1a5f", "SYSTEM_ROLE_GOVERMENT"),
      new Role("0b70667c-95e4-40d0-b817-676d49a033e5", "SYSTEM_ROLE_USER")
  };

  public void seed() {
    try {
      if (roleRepository.count() == 0) {
        for (Role role : roles) {
          roleRepository.create(role);
        }
        AuthenticationModuleMain.context.info("Seed role successful!");
      }
    } catch (Exception e) {
      AuthenticationModuleMain.context.error("Seed role failed!");
    }
  }
}
