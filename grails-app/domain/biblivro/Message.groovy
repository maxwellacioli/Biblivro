package biblivro

class Message {

    User user
    User sender    
    String message
    Date date
    
    static constraints = {
        user(nullable:false)
        sender(nullable:false)
        message(minSize:1, maxSize:2000, blank:false, nullable:false)
    }
}
