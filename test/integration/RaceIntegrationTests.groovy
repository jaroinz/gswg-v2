import grails.test.mixin.TestFor
import racetrack.Race
import spock.lang.Specification

@TestFor(Race)
class RaceIntegrationTests  extends Specification
{
    void "test Race Dates Before Today"()
    {
        when:
        def lastWeek = new Date() - 7
        def race = new Race(startDate:lastWeek)

        then: "Validation should not succeed"
        !race.validate()
        then: "There should be errors"
        race.hasErrors()

        println "\nErrors list:"
        println race.errors ?: "no errors found"

        when:
        def badField = race.errors.getFieldError('startDate')

        println "\nBadField detail:"
        println badField ?: "startDate wasn't a bad field"

        then: "Expecting to find an error on the startDate field"
        badField != null

        when:
        def code = badField?.codes.find
        {
            it == 'race.startDate.validator.invalid'
        }

        println "\nCode:"
        println code ?: "the custom validator for startDate wasn't found"
        then: "startDate field should be the culprit"
        code != null
    }
}
