package biblivro

class Deal {

	User dealingWith
    User dealer
    Product product
    Date date
    
    DealStatus dealStatus
    
    static constraints = {
        dealingWith(nullable:true) 	// aquele que compra, o que empresta, o que aceita troca
        dealer(nullable:false) 		// aquele que vende, o que pede emprestado, o que pede troca
        product(nullable:false)
    }
}
