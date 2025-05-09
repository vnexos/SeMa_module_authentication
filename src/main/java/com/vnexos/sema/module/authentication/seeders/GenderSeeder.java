package com.vnexos.sema.module.authentication.seeders;

import com.vnexos.sema.loader.annotations.AutoWired;
import com.vnexos.sema.loader.annotations.Service;
import com.vnexos.sema.module.authentication.AuthenticationModuleMain;
import com.vnexos.sema.module.authentication.data.Gender;
import com.vnexos.sema.module.authentication.repositories.IGenderRepository;

@Service
public class GenderSeeder {
  @AutoWired
  private IGenderRepository genderRepository;

  private static Gender[] genders = {
      new Gender("SYSTEM_GENDER_MALE"),
      new Gender("SYSTEM_GENDER_FEMALE"),
      new Gender("SYSTEM_GENDER_OTHER"),
      new Gender("SYSTEM_GENDER_HIDDEN")
  };

  public void seed() {
    try {
      if (genderRepository.count() == 0) {
        for (Gender gender : genders) {
          genderRepository.create(gender);
        }
        AuthenticationModuleMain.context.info("Seed gender successful!");
      }
    } catch (Exception e) {
      AuthenticationModuleMain.context.error("Seed gender failed!");
    }
  }
}
