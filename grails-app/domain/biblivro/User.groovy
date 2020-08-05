package biblivro

class User {

    String name
	String username
    String password
    String hashPassword
	String gender
	String email
  
	Address address = new Address()
	BankAccount bankAccount = new BankAccount()

    static transients = ["password"]
    
	static hasMany = [products:Product, commentaries:Commentary, friends:User, atualizations:Atualization]

	static constraints = {

		name(blank:false, nullable:false, maxSize:50)
        username(size:5..15,blank:false,unique:true)
        gender(inList:["Feminino", "Masculino"])
		email(email:true, black:false, nullable:false)
		
	}
    
    List friends() {
        return friends.collect{it.friends}
    }
   
    void setPassword(String p) {
        this.password = p
        if (password) {
            hashPassword = p.bytes.encodeBase64().toString()
        }
    }
    
    String toString() { this.username }
}
