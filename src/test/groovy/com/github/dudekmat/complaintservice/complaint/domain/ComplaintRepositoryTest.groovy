package com.github.dudekmat.complaintservice.complaint.domain

import com.github.dudekmat.complaintservice.complaint.infrastructure.InMemoryComplaintRepository
import com.github.dudekmat.complaintservice.shared.Money
import spock.lang.Specification

class ComplaintRepositoryTest extends Specification {

    ComplaintRepository repository = new InMemoryComplaintRepository()

    def 'should be able to save and load complaint'() {
        given:
        ComplaintId id = new ComplaintId(UUID.randomUUID())
        and:
        Money amount = new Money(BigDecimal.valueOf(10_000))
        and:
        UserId userId = new UserId(UUID.randomUUID())
        and:
        Complaint complaint = new Complaint(id, amount, userId)
        when:
        repository.save(complaint)
        and:
        Optional<Complaint> foundComplaint = repository.findById(id)
        then:
        foundComplaint.isPresent()
        foundComplaint.get().amount == amount
    }
}
