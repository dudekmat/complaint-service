package com.github.dudekmat.complaintservice.complaint.domain;

import com.github.dudekmat.complaintservice.shared.IdValueObject;
import java.util.UUID;

public record UserId(UUID value) implements IdValueObject {

  @Override
  public UUID getValue() {
    return value;
  }
}
