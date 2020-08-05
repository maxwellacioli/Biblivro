package biblivro

class BankAccount {

    String bank
	Integer agency
	Integer account
    String type

    static constraints = {
		bank(inList: ["Banco do Brasil", "Caixa", "Santander", "Bradesco", "Itau", "Nordeste", "Outro"], 
		blank:false, nullable:false)
        agency(blank:false, nullable:false)
        account(blank:false, nullable:false)
        type(inList:["Conta corrente","Poupança"])
    }
}
