package com.github.dudekmat.complaintservice.complaint.domain;

import java.util.List;
import java.util.Optional;

public interface ComplaintRepository {

  void save(Complaint complaint);

  Optional<Complaint> findById(ComplaintId complaintId);

  List<Complaint> findAll();

}
