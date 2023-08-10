package fiubike

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class BadgeServiceSpec extends Specification {

    BadgeService badgeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Badge(...).save(flush: true, failOnError: true)
        //new Badge(...).save(flush: true, failOnError: true)
        //Badge badge = new Badge(...).save(flush: true, failOnError: true)
        //new Badge(...).save(flush: true, failOnError: true)
        //new Badge(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //badge.id
    }

    void "test get"() {
        setupData()

        expect:
        badgeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Badge> badgeList = badgeService.list(max: 2, offset: 2)

        then:
        badgeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        badgeService.count() == 5
    }

    void "test delete"() {
        Long badgeId = setupData()

        expect:
        badgeService.count() == 5

        when:
        badgeService.delete(badgeId)
        sessionFactory.currentSession.flush()

        then:
        badgeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Badge badge = new Badge()
        badgeService.save(badge)

        then:
        badge.id != null
    }
}
