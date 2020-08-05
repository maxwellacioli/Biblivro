package biblivro

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AtualizationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Atualization.list(params), model:[atualizationCount: Atualization.count()]
    }

    def show(Atualization atualization) {
        respond atualization
    }

    def create() {
        respond new Atualization(params)
    }

    @Transactional
    def save(Atualization atualization) {
        if (atualization == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (atualization.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond atualization.errors, view:'create'
            return
        }

        atualization.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'atualization.label', default: 'Atualization'), atualization.id])
                redirect atualization
            }
            '*' { respond atualization, [status: CREATED] }
        }
    }

    def edit(Atualization atualization) {
        respond atualization
    }

    @Transactional
    def update(Atualization atualization) {
        if (atualization == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (atualization.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond atualization.errors, view:'edit'
            return
        }

        atualization.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'atualization.label', default: 'Atualization'), atualization.id])
                redirect atualization
            }
            '*'{ respond atualization, [status: OK] }
        }
    }

    @Transactional
    def delete(Atualization atualization) {

        if (atualization == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        atualization.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'atualization.label', default: 'Atualization'), atualization.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'atualization.label', default: 'Atualization'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
