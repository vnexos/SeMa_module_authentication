package com.vnexos.sema.module.authentication;

import com.vnexos.sema.context.ModuleServerContext;
import com.vnexos.sema.loader.annotations.AutoWired;
import com.vnexos.sema.loader.annotations.MainClass;
import com.vnexos.sema.loader.interfaces.AModule;
import com.vnexos.sema.module.authentication.seeders.GenderSeeder;
import com.vnexos.sema.module.authentication.seeders.RoleSeeder;

@MainClass("Authentication")
public class AuthenticationModuleMain extends AModule {
  public static ModuleServerContext context;
  @AutoWired
  private GenderSeeder genderSeeder;
  @AutoWired
  private RoleSeeder roleSeeder;

  @Override
  public void onEnabled(ModuleServerContext context) {
    AuthenticationModuleMain.context = context;
    context.info("Authentication enabled!");
    genderSeeder.seed();
    roleSeeder.seed();
  }

  @Override
  public void onDisabled() {
    context.log("Authentication disabled");
  }
}
