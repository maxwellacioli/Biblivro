package biblivro

class Address {

    String street
	String city
	String neighbourhood
	String state
	String cep
	
    static constraints = {
		street(blank:false, nullable:false, maxSize:200)
		city(blank:false, nullable:false, maxSize:50)
		neighbourhood(blank:false, nullable:false, maxSize:50)
		state(inList:["AC","AL","AM","AP","BA","CE","DF","ES","GO","MA","MG","MS","MT","PA",
		"PB","PE","PI","PR","RJ","RN","RO","RR","RS","SC","SE","SP","TO"])
		cep(blank:false, nullable:false, maxSize:9, minSize:9)        
    }
}
