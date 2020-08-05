package biblivro

class Product {

    Indication indication = new Indication()
    
    BookStatus bookStatus
    
    static hasMany = [deals:Deal]
    
    static belongsTo = [user:User]
    
    static constraints = {
    }
}
