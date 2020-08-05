package biblivro

class Exchange extends Deal {

    Product productInExchange
    
    static constraints = {
        productInExchange(blank:false, nullable:false)
    }
    
    String toString() { return "Trocar por ${this.productInExchange} de ${this.dealer}" }
}
