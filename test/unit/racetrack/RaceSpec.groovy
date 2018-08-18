package racetrack

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Race)
class RaceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test In Miles"() {
        when:
        def race = new Race(distance: 5.0)
        then:
        3.107 == race.inMiles()
    }

}
