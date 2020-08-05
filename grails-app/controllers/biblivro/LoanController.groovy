package biblivro

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class LoanController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Loan.list(params), model:[loanCount: Loan.count()]
    }

    def show(Loan loan) {
        respond loan
    }

    def create() {
        respond new Loan(params)
    }

    @Transactional
    def save(Loan loan) {
        if (loan == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (loan.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond loan.errors, view:'create'
            return
        }

        loan.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'loan.label', default: 'Loan'), loan.id])
                redirect loan
            }
            '*' { respond loan, [status: CREATED] }
        }
    }

    def edit(Loan loan) {
        respond loan
    }

    @Transactional
    def update(Loan loan) {
        if (loan == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (loan.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond loan.errors, view:'edit'
            return
        }

        loan.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'loan.label', default: 'Loan'), loan.id])
                redirect loan
            }
            '*'{ respond loan, [status: OK] }
        }
    }

    @Transactional
    def delete(Loan loan) {

        if (loan == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        loan.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'loan.label', default: 'Loan'), loan.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'loan.label', default: 'Loan'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
