package fiubike

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class BikeRentalController {

    BikeRentalService bikeRentalService

    BikeService bikeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max, Integer startDay, Integer endDay) {
        params.max = Math.min(max ?: 10, 100)

        def overlappingRentals = bikeRentalService.list(params).findAll { bikeRental ->
            bikeRental.rentStartTimestamp > startDay && bikeRental.rentEndTimestamp < endDay
        }
        
        def overlappingRentalIds = overlappingRentals.collect { it.bikeId }
        
        def unavailableBikes = bikeService.list(params).findAll { bike -> 
            overlappingRentalIds.contains(bike.id)
        }

        def availableBikes = bikeService.list(params)
        if(endDay!=1){
            availableBikes = availableBikes - unavailableBikes
        }
        respond availableBikes, model: [
            bikeRentalCount: availableBikes.size(),
            bikeList: availableBikes,
        ]
    }

    def show(Long id) {
        respond bikeRentalService.get(id)
    }

    def saveBikeRentalWithParams(Long bikeId, Integer startDay, Integer endDay) {

        def bikeRental = new BikeRental()

        bikeRental.creationTimestamp = 123
        bikeRental.rentStartTimestamp = startDay
        bikeRental.rentEndTimestamp = endDay
        bikeRental.totalCost = 0
        bikeRental.bikeId = bikeId

        User myUser = User.findByName(session.username)

        bikeRental.renterId = myUser.id

        bikeRentalService.save(bikeRental)

        redirect uri: '/'
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

            Challenge challenge = Challenge.findByType("Number of rentals")
            def bike = bikeService.get(bikeRental.bikeId)

            if(bike){
                def bikeOwnerId = bike.ownerId
                if(challenge){
                    def bikeOwnerRentals = bikeRentalService.list(params).findAll { bikeRentalItem ->
                        bikeService.get(bikeRental.bikeId).ownerId == bikeOwner
                    }

                    if(bikeOwnerRentals.size() >= challenge.target){
                        def badgeId = Badge.findByName("Number of Rentals").id
                        if(badgeId){
                            //Here you should update the field badges, which is a list, from the user whose bike is being rented, i.e from the bikeOwner.
                            System.out.println("The user with id" + bikeOwner + " has obtained the badge of number of rentals.")
                        }
                    }
                }
            }

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
