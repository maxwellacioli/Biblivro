package biblivro

class Atualization {

    User user
    String atualization
    Date date

    static constraints = {
    
        user(blank:false, nullable:false)
        atualization(blank:false, nullable:false)

    }
}
