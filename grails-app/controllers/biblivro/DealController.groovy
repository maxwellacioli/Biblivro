package biblivro

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DealController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Deal.list(params), model:[dealCount: Deal.count()]
    }

    def show(Deal deal) {
        respond deal
    }

    def create() {
        respond new Deal(params)
    }

    @Transactional
    def save(Deal deal) {
        if (deal == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (deal.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond deal.errors, view:'create'
            return
        }

        deal.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'deal.label', default: 'Deal'), deal.id])
                redirect deal
            }
            '*' { respond deal, [status: CREATED] }
        }
    }

    def edit(Deal deal) {
        respond deal
    }

    @Transactional
    def update(Deal deal) {
        if (deal == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (deal.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond deal.errors, view:'edit'
            return
        }

        deal.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'deal.label', default: 'Deal'), deal.id])
                redirect deal
            }
            '*'{ respond deal, [status: OK] }
        }
    }

    @Transactional
    def delete(Deal deal) {

        if (deal == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        deal.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'deal.label', default: 'Deal'), deal.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'deal.label', default: 'Deal'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
