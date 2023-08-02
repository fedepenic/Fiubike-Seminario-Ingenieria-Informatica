package fiubike

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class BikeRentalServiceSpec extends Specification {

    BikeRentalService bikeRentalService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new BikeRental(...).save(flush: true, failOnError: true)
        //new BikeRental(...).save(flush: true, failOnError: true)
        //BikeRental bikeRental = new BikeRental(...).save(flush: true, failOnError: true)
        //new BikeRental(...).save(flush: true, failOnError: true)
        //new BikeRental(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //bikeRental.id
    }

    void "test get"() {
        setupData()

        expect:
        bikeRentalService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<BikeRental> bikeRentalList = bikeRentalService.list(max: 2, offset: 2)

        then:
        bikeRentalList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        bikeRentalService.count() == 5
    }

    void "test delete"() {
        Long bikeRentalId = setupData()

        expect:
        bikeRentalService.count() == 5

        when:
        bikeRentalService.delete(bikeRentalId)
        sessionFactory.currentSession.flush()

        then:
        bikeRentalService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        BikeRental bikeRental = new BikeRental()
        bikeRentalService.save(bikeRental)

        then:
        bikeRental.id != null
    }
}
