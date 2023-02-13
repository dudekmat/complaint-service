package com.github.dudekmat.complaintservice.complaint.domain;

import java.util.Optional;

public interface ComplaintRepository {

  void save(Complaint complaint);

  Optional<Complaint> findById(ComplaintId complaintId);

}
