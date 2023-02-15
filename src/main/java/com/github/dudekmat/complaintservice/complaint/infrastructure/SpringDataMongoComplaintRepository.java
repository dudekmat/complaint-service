package com.github.dudekmat.complaintservice.complaint.infrastructure;

import com.github.dudekmat.complaintservice.complaint.domain.Complaint;
import com.github.dudekmat.complaintservice.complaint.domain.ComplaintId;
import org.springframework.data.mongodb.repository.MongoRepository;

interface SpringDataMongoComplaintRepository extends MongoRepository<Complaint, ComplaintId> {

}
