package biblivro

class DealStatus {

    static final Long SALE_AVALIABLE = 1
    static final Long SALE_FINISHED = 2
    
    static final Long LOAN_PENDING = 3
    static final Long LOAN_CONFIRMED = 4
    
    static final Long EXCHANGE_PENDING = 5
    static final Long EXCHANGE_CONFIRMED = 6  
    
    String description

    static constraints = {
    }
    
    String toString() { this.description }
}
