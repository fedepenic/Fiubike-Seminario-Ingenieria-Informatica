package fiubike

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class BikeRentalController {

    BikeRentalService bikeRentalService

    BikeService bikeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, Integer startDay) {
        params.max = Math.min(max ?: 10, 100)
        def filteredBikes = bikeService.list(params).findAll { bike ->
            bike.ownerId > startDay
        }
        respond filteredBikes, model: [
            bikeRentalCount: filteredBikes.size(),
            bikeList: filteredBikes,
        ]
    }

    def show(Long id) {
        respond bikeRentalService.get(id)
    }

    def create() {
        respond new BikeRental(params)
    }

    def save(BikeRental bikeRental) {
        if (bikeRental == null) {
            notFound()
            return
        }

        try {
            bikeRentalService.save(bikeRental)
        } catch (ValidationException e) {
            respond bikeRental.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'bikeRental.label', default: 'BikeRental'), bikeRental.id])
                redirect bikeRental
            }
            '*' { respond bikeRental, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond bikeRentalService.get(id)
    }

    def update(BikeRental bikeRental) {
        if (bikeRental == null) {
            notFound()
            return
        }

        try {
            bikeRentalService.save(bikeRental)
        } catch (ValidationException e) {
            respond bikeRental.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'bikeRental.label', default: 'BikeRental'), bikeRental.id])
                redirect bikeRental
            }
            '*'{ respond bikeRental, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        bikeRentalService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'bikeRental.label', default: 'BikeRental'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'bikeRental.label', default: 'BikeRental'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
