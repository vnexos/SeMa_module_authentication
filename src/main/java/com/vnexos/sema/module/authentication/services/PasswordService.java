package com.vnexos.sema.module.authentication.services;

import java.nio.charset.Charset;

import com.vnexos.sema.loader.annotations.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCryptFormatter;
import at.favre.lib.crypto.bcrypt.BCryptParser;
import at.favre.lib.crypto.bcrypt.LongPasswordStrategies;
import at.favre.lib.crypto.bcrypt.Radix64Encoder;

@Service
public class PasswordService {
  private static BCrypt.Hasher getHasher() {
    BCryptFormatter formatter = new BCryptFormatter.Default(new Radix64Encoder.Default(), Charset.forName("UTF-8"));
    BCryptParser parser = BCrypt.Version.VERSION_2B.parser;

    BCrypt.Version version = new BCrypt.Version(new byte[] { 0x76, 0x6e, 0x31 }, false, true, 8, formatter, parser);
    return BCrypt.with(LongPasswordStrategies.hashSha512(version));
  }

  public String hashPassword(String password) {
    BCrypt.Hasher hasher = getHasher();
    return hasher.hashToString(14, password.toCharArray());
  }
}
