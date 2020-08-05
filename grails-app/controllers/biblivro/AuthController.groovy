package biblivro

class AuthController {

    def login = { 

		def user = User.findByUsernameAndHashPassword(params.username, params.password.bytes.encodeBase64().toString())
        if (user) {
                session.user = user
                session.setAttribute("user",user)
                redirect(action: "home", controller: "user")
                
        } else {
                flash.message = "Falha ao logar !"
                redirect(uri: "/")
        }
	}


	def logout = {
    
        session.invalidate()
        
        // For now, redirect back to the home page.
        redirect(uri: "/")
    }

    def unauthorized = {
        render "You do not have permission to access this page."
    }
}