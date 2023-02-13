package com.github.dudekmat.complaintservice.complaint.domain

import com.github.dudekmat.complaintservice.shared.Money
import spock.lang.Specification

class ComplaintTest extends Specification {

    ComplaintId complaintId = new ComplaintId(UUID.randomUUID())
    UserId userId = new UserId(UUID.randomUUID())
    Money amount = new Money(BigDecimal.valueOf(10_000))
    Complaint complaint = new Complaint(complaintId, amount, userId)

    def 'cannot accept complaint in accepted status'() {
        given:
        complaint.accept()
        when:
        complaint.accept()
        then:
        thrown(ComplaintException)
    }

    def 'cannot change amount for complaint in accepted status'() {
        given:
        complaint.accept()
        when:
        complaint.changeAmount(new Money(BigDecimal.valueOf(12_000)), userId)
        then:
        thrown(ComplaintException)
    }

    def 'cannot change amount for complaint submitted by another user'() {
        when:
        complaint.changeAmount(new Money(BigDecimal.valueOf(12_000)), new UserId(UUID.randomUUID()))
        then:
        thrown(ComplaintException)
    }

    def 'can accept complaint in draft status'() {
        when:
        complaint.accept()
        then:
        complaint.complaintStatus == ComplaintStatus.ACCEPTED
    }

    def 'can change amount for complaint in draft status by the same user'() {
        given:
        Money changedAmount = new Money(BigDecimal.valueOf(12_000))
        when:
        complaint.changeAmount(changedAmount, userId)
        then:
        complaint.amount == changedAmount
    }
}
