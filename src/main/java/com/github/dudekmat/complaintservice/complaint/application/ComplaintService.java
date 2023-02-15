package com.github.dudekmat.complaintservice.complaint.application;

import com.github.dudekmat.complaintservice.complaint.domain.Complaint;
import com.github.dudekmat.complaintservice.complaint.domain.ComplaintId;
import com.github.dudekmat.complaintservice.complaint.domain.ComplaintRepository;
import com.github.dudekmat.complaintservice.complaint.domain.UserId;
import com.github.dudekmat.complaintservice.shared.Money;
import com.github.dudekmat.complaintservice.shared.NotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComplaintService {

  private final ComplaintRepository complaintRepository;

  public UUID createComplaint(UUID userId, BigDecimal amount) {
    var complaintId = new ComplaintId(UUID.randomUUID());
    var complaintAmount = new Money(amount);
    var complaintUserId = new UserId(userId);
    var complaint = new Complaint(complaintId, complaintAmount, complaintUserId);

    complaintRepository.save(complaint);
    return complaintId.value();
  }

  public ComplaintData getComplaint(UUID id) {
    return complaintRepository.findById(new ComplaintId(id))
        .map(this::mapToComplaintData)
        .orElseThrow(() -> new NotFoundException("Complaint not found with id=" + id));
  }

  public List<ComplaintData> getComplaints() {
    return complaintRepository.findAll().stream()
        .map(this::mapToComplaintData)
        .toList();
  }

  private ComplaintData mapToComplaintData(Complaint complaint) {
    return ComplaintData.builder()
        .id(complaint.getId().getValue())
        .userId(complaint.getUserId().getValue())
        .amount(complaint.getAmount().amount())
        .status(complaint.getComplaintStatus().name())
        .created(complaint.getCreated())
        .lastModified(complaint.getLastModified())
        .build();
  }
}
