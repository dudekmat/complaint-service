package com.github.dudekmat.complaintservice.complaint.infrastructure;

import com.github.dudekmat.complaintservice.complaint.domain.Complaint;
import com.github.dudekmat.complaintservice.complaint.domain.ComplaintId;
import com.github.dudekmat.complaintservice.complaint.domain.ComplaintRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class MongoComplaintRepository implements ComplaintRepository {

  private final SpringDataMongoComplaintRepository repository;

  @Override
  public void save(Complaint complaint) {
    repository.save(complaint);
  }

  @Override
  public Optional<Complaint> findById(ComplaintId complaintId) {
    return repository.findById(complaintId);
  }

  @Override
  public List<Complaint> findAll() {
    return repository.findAll();
  }
}
