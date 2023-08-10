package fiubike

import grails.gorm.services.Service

@Service(Challenge)
interface ChallengeService {

    Challenge get(Serializable id)

    List<Challenge> list(Map args)

    Long count()

    void delete(Serializable id)

    Challenge save(Challenge challenge)

}