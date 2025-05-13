package com.vnexos.sema.module.authentication.controllers;

import com.vnexos.sema.ApiResponse;
import com.vnexos.sema.loader.annotations.AutoWired;
import com.vnexos.sema.loader.annotations.Controller;
import com.vnexos.sema.loader.annotations.FromBody;
import com.vnexos.sema.loader.annotations.HttpPost;
import com.vnexos.sema.loader.interfaces.ControllerBase;
import com.vnexos.sema.module.authentication.AuthenticationModuleMain;
import com.vnexos.sema.module.authentication.dtos.RegisterDto;
import com.vnexos.sema.module.authentication.services.PasswordService;

@Controller("auth")
public class AuthController extends ControllerBase {
  @AutoWired
  private PasswordService passwordService;

  @HttpPost("sign-up")
  public ApiResponse<?> register(@FromBody RegisterDto request) {
    try {
      String password = request.getPassword();

      return createOk(passwordService.hashPassword(password));
    } catch (Exception ex) {
      AuthenticationModuleMain.context.log(ex);
      return createBadRequest("Xãy ra lỗi trong quá trình đăng ký!");
    }
  }
}
