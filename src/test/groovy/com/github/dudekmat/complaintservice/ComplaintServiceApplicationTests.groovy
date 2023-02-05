package com.github.dudekmat.complaintservice

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class ComplaintServiceApplicationTests extends Specification {

    def 'context loads'() {
        expect:
        1 == 1
    }
}
