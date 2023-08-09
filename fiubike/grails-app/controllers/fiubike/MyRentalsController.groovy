package fiubike

class MyRentalsController{

    BikeRentalService bikeRentalService

    def index(){
       [
            bikeRentals: bikeRentalService.list(params)
        ] 
    }
}