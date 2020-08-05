package biblivro

class Sale extends Deal {

    Float price
		
    static constraints = {
        price(blank:false, nullable:false)
    }
}
