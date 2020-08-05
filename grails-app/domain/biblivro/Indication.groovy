package biblivro

class Indication {

    String indication
	String commentary
    
    static constraints = {
        indication(inList:["Muito Bom","Bom","Regular","Ruim", "Muito Ruim"])
        commentary(blank:false, nullable:false)
    }
}
