package biblivro

class ShiroUser {

    User user
    String username
    String password
    
    static hasMany = [ roles: ShiroRole, permissions: String ]

    static constraints = {
        username(nullable: false, blank: false)
    }
	
}
