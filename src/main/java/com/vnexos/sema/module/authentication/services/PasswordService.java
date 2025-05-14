package com.vnexos.sema.module.authentication.services;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;

import com.vnexos.sema.loader.annotations.Service;

@Service
public class PasswordService {
  private final int iterations = 2;
  private final int memLimit = 65536; // corrected from 66536
  private final int hashLength = 96; // usually in bytes, not bits
  private final int parallelism = 1;

  private static byte[] generateSaltByte() {
    SecureRandom secureRandom = new SecureRandom();
    byte[] salt = new byte[31];
    secureRandom.nextBytes(salt);
    return salt;
  }

  private Argon2Parameters.Builder getDefaultBuilder(byte[] salt) {
    return new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
        .withVersion(Argon2Parameters.ARGON2_VERSION_13)
        .withIterations(iterations)
        .withMemoryAsKB(memLimit)
        .withParallelism(parallelism)
        .withSalt(salt);
  }

  public String hashPassword(String password) {
    byte[] salt = generateSaltByte();
    Argon2BytesGenerator generator = new Argon2BytesGenerator();
    generator.init(getDefaultBuilder(salt).build());

    byte[] hash = new byte[hashLength];
    generator.generateBytes(password.getBytes(StandardCharsets.UTF_8), hash, 0, hash.length);

    String saltB64 = Base64.getEncoder().encodeToString(salt);
    String hashB64 = Base64.getEncoder().encodeToString(hash);

    return saltB64 + "@" + hashB64;
  }

  public boolean verifyPassword(String password, String stored) {
    String[] parts = stored.split("@");
    if (parts.length != 2)
      return false;

    byte[] salt = Base64.getDecoder().decode(parts[0]);
    byte[] expectedHash = Base64.getDecoder().decode(parts[1]);

    Argon2BytesGenerator generator = new Argon2BytesGenerator();
    generator.init(getDefaultBuilder(salt).build());

    byte[] actualHash = new byte[hashLength];
    generator.generateBytes(password.getBytes(StandardCharsets.UTF_8), actualHash, 0, actualHash.length);

    return constantTimeArrayEquals(expectedHash, actualHash);
  }

  private boolean constantTimeArrayEquals(byte[] a, byte[] b) {
    if (a.length != b.length)
      return false;
    int result = 0;
    for (int i = 0; i < a.length; i++) {
      result |= a[i] ^ b[i];
    }
    return result == 0;
  }
}