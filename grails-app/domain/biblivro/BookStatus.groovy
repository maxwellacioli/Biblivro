package biblivro

class BookStatus {

    static final Long NEW = 1
    static final Long SOLD = 2
    static final Long LENT = 3
    static final Long CHANGED = 4
    
    String description

    static constraints = {
    }
    
    String toString() { this.description }
}
