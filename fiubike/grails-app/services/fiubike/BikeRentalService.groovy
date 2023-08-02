package fiubike

import grails.gorm.services.Service

@Service(BikeRental)
interface BikeRentalService {

    BikeRental get(Serializable id)

    List<BikeRental> list(Map args)

    Long count()

    void delete(Serializable id)

    BikeRental save(BikeRental bikeRental)

}