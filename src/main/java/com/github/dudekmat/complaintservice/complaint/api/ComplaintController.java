package com.github.dudekmat.complaintservice.complaint.api;

import com.github.dudekmat.complaintservice.complaint.application.ComplaintData;
import com.github.dudekmat.complaintservice.complaint.application.ComplaintService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/complaints")
@Validated
@RequiredArgsConstructor
class ComplaintController {

  private final ComplaintService complaintService;

  @PostMapping
  public UUID createComplaint(@RequestBody @Valid CreateComplaintRequest createComplaintRequest) {
    return complaintService.createComplaint(createComplaintRequest.userId(),
        createComplaintRequest.amount());
  }

  @GetMapping("/{id}")
  public ComplaintResponse getComplaint(@PathVariable("id") @NotNull UUID id) {
    ComplaintData complaint = complaintService.getComplaint(id);
    return mapToComplaintResponse(complaint);
  }

  @GetMapping
  public List<ComplaintResponse> getComplaints() {
    return complaintService.getComplaints().stream()
        .map(this::mapToComplaintResponse)
        .toList();
  }

  private ComplaintResponse mapToComplaintResponse(ComplaintData complaintData) {
    return ComplaintResponse.builder()
        .id(complaintData.id())
        .userId(complaintData.userId())
        .amount(complaintData.amount())
        .status(complaintData.status())
        .build();
  }
}
