package fiubike

class MyRentalsController{

    BikeRentalService bikeRentalService

    BikeService bikeService

    def index(){

        User myUser = User.findByName(session.username)

        def myRentals = bikeRentalService.list(params)

        def ownedRentals = bikeRentalService.list(params)

        if(myUser){
            myRentals = myRentals.findAll { bikeRental ->
                bikeRental.renterId == myUser.id
            }

            ownedRentals = ownedRentals.findAll { bikeRental ->
                Bike bike = bikeService.get(bikeRental.bikeId)
                bike.ownerId == myUser.id
            }
        }
        

       [
            myRentals: myRentals,
            ownedRentals: ownedRentals 
        ] 
    }
}