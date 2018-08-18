package racetrack

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test simple constraints"() {
        when: 'superuser role'
        def user = new User(login:"someone", password: "blah", role:"superuser")

        then: 'role not valid'
        !user.validate()
        then: "There should be errors"
        user.hasErrors()

        println "\nErrors list:"
        println user.errors ?: "no errors found"
    }

    void "unique constraint"(){
        when: 'setting user domain up'
        mockDomain(User, [])
        def jdoe = new User(login:"jdoe", password: "somejdoe")
        def admin = new User(login:"admin", password: "someadmin")
        jdoe.save()
        admin.save()

        then: 'VT user domain size is ok'
        User.count() == 2

        when: 'trying to enter a dupe user'
        def badUser = new User(login:"jdoe", password: "some")
        badUser.save()

        then: 'unique error is set'
        badUser.errors.getFieldError("login").toString().contains("unique.error")

        println "\nErrors list:"
        println badUser.errors ?: "no errors found"

        then: 'a good login gets in'
        assertNotNull User.findByLoginAndPassword("jdoe", "somejdoe")
    }
}
