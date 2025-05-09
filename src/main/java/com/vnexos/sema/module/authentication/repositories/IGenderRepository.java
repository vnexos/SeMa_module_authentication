package com.vnexos.sema.module.authentication.repositories;

import java.util.UUID;

import com.vnexos.sema.database.helpers.DatabaseContext;
import com.vnexos.sema.module.authentication.data.Gender;

public interface IGenderRepository extends DatabaseContext<Gender, UUID> {
}
