package fiubike

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ChallengeController {

    ChallengeService challengeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond challengeService.list(params), model:[challengeCount: challengeService.count()]
    }

    def show(Long id) {
        respond challengeService.get(id)
    }

    def create() {
        respond new Challenge(params)
    }

    def save(Challenge challenge) {
        if (challenge == null) {
            notFound()
            return
        }

        try {
            challengeService.save(challenge)
        } catch (ValidationException e) {
            respond challenge.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'challenge.label', default: 'Challenge'), challenge.id])
                redirect challenge
            }
            '*' { respond challenge, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond challengeService.get(id)
    }

    def update(Challenge challenge) {
        if (challenge == null) {
            notFound()
            return
        }

        try {
            challengeService.save(challenge)
        } catch (ValidationException e) {
            respond challenge.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'challenge.label', default: 'Challenge'), challenge.id])
                redirect challenge
            }
            '*'{ respond challenge, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        challengeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'challenge.label', default: 'Challenge'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'challenge.label', default: 'Challenge'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
