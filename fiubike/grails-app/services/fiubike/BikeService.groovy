package fiubike

import grails.gorm.services.Service

@Service(Bike)
interface BikeService {

    Bike get(Serializable id)

    List<Bike> list(Map args)

    Long count()

    void delete(Serializable id)

    Bike save(Bike bike)

}