package fiubike

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ChallengeServiceSpec extends Specification {

    ChallengeService challengeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Challenge(...).save(flush: true, failOnError: true)
        //new Challenge(...).save(flush: true, failOnError: true)
        //Challenge challenge = new Challenge(...).save(flush: true, failOnError: true)
        //new Challenge(...).save(flush: true, failOnError: true)
        //new Challenge(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //challenge.id
    }

    void "test get"() {
        setupData()

        expect:
        challengeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Challenge> challengeList = challengeService.list(max: 2, offset: 2)

        then:
        challengeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        challengeService.count() == 5
    }

    void "test delete"() {
        Long challengeId = setupData()

        expect:
        challengeService.count() == 5

        when:
        challengeService.delete(challengeId)
        sessionFactory.currentSession.flush()

        then:
        challengeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Challenge challenge = new Challenge()
        challengeService.save(challenge)

        then:
        challenge.id != null
    }
}
