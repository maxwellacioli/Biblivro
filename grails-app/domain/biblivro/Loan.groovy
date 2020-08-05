package biblivro

class Loan extends Deal {

    Date loanDate
    Date devolutionDate
    
    static constraints = {
        loanDate(validator: {return (it >= new Date())})
        devolutionDate(validator: {return (it >= new Date())})
    }
    
    String toString() {

        return "Emprestar a ${this.dealer} de ${loanDate.format('dd/MMM')} até ${devolutionDate.format('dd/MMM')}"
        
    }
}
