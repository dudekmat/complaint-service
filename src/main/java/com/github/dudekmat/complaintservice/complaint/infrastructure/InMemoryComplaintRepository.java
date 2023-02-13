package com.github.dudekmat.complaintservice.complaint.infrastructure;

import com.github.dudekmat.complaintservice.complaint.domain.Complaint;
import com.github.dudekmat.complaintservice.complaint.domain.ComplaintId;
import com.github.dudekmat.complaintservice.complaint.domain.ComplaintRepository;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryComplaintRepository implements ComplaintRepository {

  private final Map<ComplaintId, Complaint> complaints = new ConcurrentHashMap<>();

  @Override
  public void save(Complaint complaint) {
    complaints.put(complaint.getId(), complaint);
  }

  @Override
  public Optional<Complaint> findById(ComplaintId complaintId) {
    return complaints
        .values()
        .stream()
        .filter(complaint -> complaint.getId().equals(complaintId))
        .findAny();
  }
}
