package biblivro

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class IndicationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Indication.list(params), model:[indicationCount: Indication.count()]
    }

    def show(Indication indication) {
        respond indication
    }

    def create() {
        respond new Indication(params)
    }

    @Transactional
    def save(Indication indication) {
        if (indication == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (indication.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond indication.errors, view:'create'
            return
        }

        indication.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'indication.label', default: 'Indication'), indication.id])
                redirect indication
            }
            '*' { respond indication, [status: CREATED] }
        }
    }

    def edit(Indication indication) {
        respond indication
    }

    @Transactional
    def update(Indication indication) {
        if (indication == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (indication.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond indication.errors, view:'edit'
            return
        }

        indication.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'indication.label', default: 'Indication'), indication.id])
                redirect indication
            }
            '*'{ respond indication, [status: OK] }
        }
    }

    @Transactional
    def delete(Indication indication) {

        if (indication == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        indication.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'indication.label', default: 'Indication'), indication.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'indication.label', default: 'Indication'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
