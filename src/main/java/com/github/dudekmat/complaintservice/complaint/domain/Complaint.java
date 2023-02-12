package com.github.dudekmat.complaintservice.complaint.domain;

import com.github.dudekmat.complaintservice.shared.Money;
import java.time.Instant;
import lombok.Getter;

class Complaint {

  @Getter
  private ComplaintId id;
  @Getter
  private ComplaintStatus complaintStatus;
  @Getter
  private Money amount;
  private UserId userId;
  private Instant created;
  private Instant lastModified;

  Complaint(ComplaintId id, Money amount, UserId userId) {
    this.id = id;
    this.amount = amount;
    this.userId = userId;
    this.complaintStatus = ComplaintStatus.DRAFT;
    this.created = Instant.now();
    this.lastModified = Instant.now();
  }

  void accept() {
    validateStatus();
    this.complaintStatus = ComplaintStatus.ACCEPTED;
    this.lastModified = Instant.now();
  }

  void update(Money amount, UserId userId) {
    validateStatus();
    if (!this.userId.equals(userId)) {
      throw new ComplaintException("Cannot access complaint submitted by another user");
    }
    this.amount = amount;
  }

  private void validateStatus() {
    if (ComplaintStatus.ACCEPTED.equals(complaintStatus)) {
      throw new ComplaintException("Complaint is in accepted status");
    }
  }
}
