package fiubike

class LoginController{
    def index(){
        
    }

    def redirect() {
        session.username = params.username

        redirect uri: '/'
    }
}