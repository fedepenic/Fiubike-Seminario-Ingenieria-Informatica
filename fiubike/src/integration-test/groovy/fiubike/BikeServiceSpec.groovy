package fiubike

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class BikeServiceSpec extends Specification {

    BikeService bikeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Bike(...).save(flush: true, failOnError: true)
        //new Bike(...).save(flush: true, failOnError: true)
        //Bike bike = new Bike(...).save(flush: true, failOnError: true)
        //new Bike(...).save(flush: true, failOnError: true)
        //new Bike(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //bike.id
    }

    void "test get"() {
        setupData()

        expect:
        bikeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Bike> bikeList = bikeService.list(max: 2, offset: 2)

        then:
        bikeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        bikeService.count() == 5
    }

    void "test delete"() {
        Long bikeId = setupData()

        expect:
        bikeService.count() == 5

        when:
        bikeService.delete(bikeId)
        sessionFactory.currentSession.flush()

        then:
        bikeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Bike bike = new Bike()
        bikeService.save(bike)

        then:
        bike.id != null
    }
}
