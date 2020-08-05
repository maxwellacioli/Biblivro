package biblivro

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BankAccountController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond BankAccount.list(params), model:[bankAccountCount: BankAccount.count()]
    }

    def show(BankAccount bankAccount) {
        respond bankAccount
    }

    def create() {
        respond new BankAccount(params)
    }

    @Transactional
    def save(BankAccount bankAccount) {
        if (bankAccount == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (bankAccount.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond bankAccount.errors, view:'create'
            return
        }

        bankAccount.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'bankAccount.label', default: 'BankAccount'), bankAccount.id])
                redirect bankAccount
            }
            '*' { respond bankAccount, [status: CREATED] }
        }
    }

    def edit(BankAccount bankAccount) {
        respond bankAccount
    }

    @Transactional
    def update(BankAccount bankAccount) {
        if (bankAccount == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (bankAccount.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond bankAccount.errors, view:'edit'
            return
        }

        bankAccount.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'bankAccount.label', default: 'BankAccount'), bankAccount.id])
                redirect bankAccount
            }
            '*'{ respond bankAccount, [status: OK] }
        }
    }

    @Transactional
    def delete(BankAccount bankAccount) {

        if (bankAccount == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        bankAccount.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'bankAccount.label', default: 'BankAccount'), bankAccount.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'bankAccount.label', default: 'BankAccount'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
