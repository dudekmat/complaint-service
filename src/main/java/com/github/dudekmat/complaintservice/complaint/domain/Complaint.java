package com.github.dudekmat.complaintservice.complaint.domain;

import com.github.dudekmat.complaintservice.shared.Money;
import java.time.Instant;
import lombok.Getter;

public class Complaint {

  @Getter
  private ComplaintId id;
  @Getter
  private ComplaintStatus complaintStatus;
  @Getter
  private Money amount;
  @Getter
  private UserId userId;
  @Getter
  private Instant created;
  @Getter
  private Instant lastModified;

  private Complaint() {}

  public Complaint(ComplaintId id, Money amount, UserId userId) {
    this.id = id;
    this.amount = amount;
    this.userId = userId;
    this.complaintStatus = ComplaintStatus.DRAFT;
    this.created = Instant.now();
    setLastModified();
  }

  public void accept() {
    validateStatus();
    this.complaintStatus = ComplaintStatus.ACCEPTED;
    setLastModified();
  }

  public void changeAmount(Money amount, UserId userId) {
    validateStatus();
    validateUser(userId);
    this.amount = amount;
    setLastModified();
  }

  private void setLastModified() {
    this.lastModified = Instant.now();
  }

  private void validateUser(UserId userId) {
    if (!this.userId.equals(userId)) {
      throw new ComplaintException("Cannot access complaint submitted by another user");
    }
  }

  private void validateStatus() {
    if (ComplaintStatus.ACCEPTED.equals(complaintStatus)) {
      throw new ComplaintException("Complaint is in accepted status");
    }
  }
}
