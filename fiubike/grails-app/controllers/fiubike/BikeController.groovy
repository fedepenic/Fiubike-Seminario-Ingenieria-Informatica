package fiubike

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class BikeController {

    BikeService bikeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    // def index(Integer max) {
    //     params.max = Math.min(max ?: 10, 100)
    //     def bikes = bikeService.list(params)

    //     def filteredBikes = bikes.findAll { bike ->
    //         bike.ownerId > 2
    //     }

    //     respond filteredBikes, model: [bikeList: filteredBikes, bikeCount: filteredBikes.size()]
    // }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond bikeService.list(params), model:[bikeCount: bikeService.count()]
    }

    def show(Long id) {
        respond bikeService.get(id)
    }

    def create() {
        respond new Bike(params)
    }

    def save(Bike bike) {
        if (bike == null) {
            notFound()
            return
        }

        try {
            bikeService.save(bike)
        } catch (ValidationException e) {
            respond bike.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'bike.label', default: 'Bike'), bike.id])
                redirect bike
            }
            '*' { respond bike, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond bikeService.get(id)
    }

    def update(Bike bike) {
        if (bike == null) {
            notFound()
            return
        }

        try {
            bikeService.save(bike)
        } catch (ValidationException e) {
            respond bike.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'bike.label', default: 'Bike'), bike.id])
                redirect bike
            }
            '*'{ respond bike, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        bikeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'bike.label', default: 'Bike'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'bike.label', default: 'Bike'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
