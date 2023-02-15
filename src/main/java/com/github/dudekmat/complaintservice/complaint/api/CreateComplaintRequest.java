package com.github.dudekmat.complaintservice.complaint.api;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

record CreateComplaintRequest(@NotNull UUID userId, @NotNull @Positive BigDecimal amount) {

}
