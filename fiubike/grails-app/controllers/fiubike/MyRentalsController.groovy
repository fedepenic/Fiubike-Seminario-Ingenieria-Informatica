package fiubike

class MyRentalsController{

    BikeRentalService bikeRentalService

    BikeService bikeService

    def index(){

        User myUser = User.findByName(session.username)

        def myActiveRentals = bikeRentalService.list(params)

        def myHistoricRentals = bikeRentalService.list(params)

        def activeOwnedRentals = bikeRentalService.list(params)

        def historicOwnedRentals = bikeRentalService.list(params)

        if(myUser){
            myActiveRentals = myActiveRentals.findAll { bikeRental ->
                bikeRental.renterId == myUser.id && 
                bikeRental.rentEndTimestamp > 5 //Instead, the rentEndTimestamp should be greater than the actual date.
            }

            myHistoricRentals = myHistoricRentals.findAll { bikeRental ->
                bikeRental.renterId == myUser.id && 
                bikeRental.rentEndTimestamp < 5 //Instead, the rentEndTimestamp should be smaller than the actual date.
            }

            activeOwnedRentals = activeOwnedRentals.findAll { bikeRental ->
                Bike bike = bikeService.get(bikeRental.bikeId)
                if(bike){
                    bike.ownerId == myUser.id &&
                    bikeRental.rentEndTimestamp > 5 //Instead, the rentEndTimestamp should be greater than the actual date.
                }
            }

            historicOwnedRentals = historicOwnedRentals.findAll { bikeRental ->
                Bike bike = bikeService.get(bikeRental.bikeId)
                if(bike){
                    bike.ownerId == myUser.id &&
                    bikeRental.rentEndTimestamp < 5 //Instead, the rentEndTimestamp should be smaller than the actual date.
                }
            }
        }
        

       [
            myActiveRentals: myActiveRentals,
            myHistoricRentals: myHistoricRentals,
            activeOwnedRentals: activeOwnedRentals,
            historicOwnedRentals: historicOwnedRentals, 
        ] 
    }
}