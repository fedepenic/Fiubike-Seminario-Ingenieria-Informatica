package fiubike

class LoginController{

    BikeService bikeService

    def index(){

        def bikes = bikeService.list(params)

        def filteredBikes = bikes.findAll { bike ->
            bike.ownerId > 2
        }

        respond filteredBikes, model: [bikeList: filteredBikes, bikeCount: filteredBikes.size()]
        
    }

    def redirect() {
        session.username = params.username

        redirect uri: '/'
    }
}