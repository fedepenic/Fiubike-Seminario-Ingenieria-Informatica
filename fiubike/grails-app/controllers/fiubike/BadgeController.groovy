package fiubike

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class BadgeController {

    BadgeService badgeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond badgeService.list(params), model:[badgeCount: badgeService.count()]
    }

    def show(Long id) {
        respond badgeService.get(id)
    }

    def create() {
        respond new Badge(params)
    }

    def save(Badge badge) {
        if (badge == null) {
            notFound()
            return
        }

        try {
            badgeService.save(badge)
        } catch (ValidationException e) {
            respond badge.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'badge.label', default: 'Badge'), badge.id])
                redirect badge
            }
            '*' { respond badge, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond badgeService.get(id)
    }

    def update(Badge badge) {
        if (badge == null) {
            notFound()
            return
        }

        try {
            badgeService.save(badge)
        } catch (ValidationException e) {
            respond badge.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'badge.label', default: 'Badge'), badge.id])
                redirect badge
            }
            '*'{ respond badge, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        badgeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'badge.label', default: 'Badge'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'badge.label', default: 'Badge'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
