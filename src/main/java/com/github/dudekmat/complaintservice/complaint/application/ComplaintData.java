package com.github.dudekmat.complaintservice.complaint.application;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import lombok.Builder;

@Builder
public record ComplaintData(UUID id, UUID userId, BigDecimal amount, String status, Instant created, Instant lastModified) {

}
