package com.vnexos.sema.module.authentication.controllers;

import com.vnexos.sema.ApiResponse;
import com.vnexos.sema.loader.annotations.AutoWired;
import com.vnexos.sema.loader.annotations.Controller;
import com.vnexos.sema.loader.annotations.FromBody;
import com.vnexos.sema.loader.annotations.FromQuery;
import com.vnexos.sema.loader.annotations.HttpGet;
import com.vnexos.sema.loader.annotations.HttpPost;
import com.vnexos.sema.loader.interfaces.ControllerBase;
import com.vnexos.sema.module.authentication.AuthenticationModuleMain;
import com.vnexos.sema.module.authentication.data.User;
import com.vnexos.sema.module.authentication.dtos.LoginDto;
import com.vnexos.sema.module.authentication.dtos.RegisterDto;
import com.vnexos.sema.module.authentication.repositories.IUserRepository;
import com.vnexos.sema.module.authentication.services.PasswordService;
import com.vnexos.sema.util.Mapper;

@Controller("auth")
public class AuthController extends ControllerBase {
  @AutoWired
  private PasswordService passwordService;
  @AutoWired
  private IUserRepository userRepository;

  @HttpPost("sign-up")
  public ApiResponse<?> register(@FromBody RegisterDto request) {
    try {
      User user = Mapper.map(request, User.class);
      String hashedPassword = passwordService.hashPassword(request.getPassword());

      user.setHashedPassword(hashedPassword);
      user.setRoleId("0b70667c-95e4-40d0-b817-676d49a033e5");

      return createOk(userRepository.create(user));
    } catch (Exception ex) {
      AuthenticationModuleMain.context.log(ex);
      return createBadRequest("Xãy ra lỗi trong quá trình đăng ký!");
    }
  }

  @HttpGet("sign-in")
  public ApiResponse<?> login(@FromQuery LoginDto request) {
    try {
      User user = userRepository.getByEmail(request.getAccount());
      if (user == null)
        user = userRepository.getByUsername(request.getAccount());

      if (user == null)
        return createBadRequest("SYSTEM_ACCOUNT_NOT_FOUND");
      String hashedPassword = user.getHashedPassword();
      if (!passwordService.verifyPassword(request.getPassword(), hashedPassword)) {
        return createBadRequest("SYSTEM_ACCOUNT_WRONG_PASSWORD");
      }
      return createOk("SYSTEM_ACCOUNT_SUCCESS");
    } catch (Exception ex) {
      AuthenticationModuleMain.context.log(ex);
      return createBadRequest("Xãy ra lỗi trong quá trình đăng nhập!");
    }
  }
}
