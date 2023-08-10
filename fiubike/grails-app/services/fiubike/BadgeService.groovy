package fiubike

import grails.gorm.services.Service

@Service(Badge)
interface BadgeService {

    Badge get(Serializable id)

    List<Badge> list(Map args)

    Long count()

    void delete(Serializable id)

    Badge save(Badge badge)

}