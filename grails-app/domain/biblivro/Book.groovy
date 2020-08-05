package biblivro

class Book extends Product {

    String title
	String author
	String edition
	String category

    static constraints = {
		title(blank:false, nullable:false, maxSize:50)
		author(blank:false, nullable:false, maxSize:50)
		edition(maxSize:2)
		category(inList:["Paradidáticos","Ficção","Exatas","Humanas","Saúde","Outra"])
    }
    
    String toString() { this.title }
	
}
