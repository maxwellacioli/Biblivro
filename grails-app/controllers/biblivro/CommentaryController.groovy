package biblivro

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CommentaryController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Commentary.list(params), model:[commentaryCount: Commentary.count()]
    }

    def show(Commentary commentary) {
        respond commentary
    }

    def create() {
        respond new Commentary(params)
    }

    @Transactional
    def save(Commentary commentary) {
        if (commentary == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (commentary.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond commentary.errors, view:'create'
            return
        }

        commentary.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'commentary.label', default: 'Commentary'), commentary.id])
                redirect commentary
            }
            '*' { respond commentary, [status: CREATED] }
        }
    }

    def edit(Commentary commentary) {
        respond commentary
    }

    @Transactional
    def update(Commentary commentary) {
        if (commentary == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (commentary.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond commentary.errors, view:'edit'
            return
        }

        commentary.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'commentary.label', default: 'Commentary'), commentary.id])
                redirect commentary
            }
            '*'{ respond commentary, [status: OK] }
        }
    }

    @Transactional
    def delete(Commentary commentary) {

        if (commentary == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        commentary.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'commentary.label', default: 'Commentary'), commentary.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'commentary.label', default: 'Commentary'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
