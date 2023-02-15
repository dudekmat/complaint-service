package com.github.dudekmat.complaintservice.complaint.api;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;

@Builder
record ComplaintResponse(UUID id, UUID userId, BigDecimal amount, String status) {

}
