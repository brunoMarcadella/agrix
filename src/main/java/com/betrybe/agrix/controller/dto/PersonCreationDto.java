package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Person;
import com.betrybe.agrix.security.Role;

/**
 * The type Person creation dto.
 */
public record PersonCreationDto(
    String username,
    String password,
    String role
) {

  /**
   * To entity person.
   *
   * @return the person
   */
  public Person toEntity() {
    Person person = new Person();
    person.setUsername(username);
    person.setPassword(password);
    Role roleEnum = Role.valueOf(role.toUpperCase());
    person.setRole(roleEnum);

    return person;
  }
}
