package biblivro

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BookStatusController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond BookStatus.list(params), model:[bookStatusCount: BookStatus.count()]
    }

    def show(BookStatus bookStatus) {
        respond bookStatus
    }

    def create() {
        respond new BookStatus(params)
    }

    @Transactional
    def save(BookStatus bookStatus) {
        if (bookStatus == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (bookStatus.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond bookStatus.errors, view:'create'
            return
        }

        bookStatus.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'bookStatus.label', default: 'BookStatus'), bookStatus.id])
                redirect bookStatus
            }
            '*' { respond bookStatus, [status: CREATED] }
        }
    }

    def edit(BookStatus bookStatus) {
        respond bookStatus
    }

    @Transactional
    def update(BookStatus bookStatus) {
        if (bookStatus == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (bookStatus.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond bookStatus.errors, view:'edit'
            return
        }

        bookStatus.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'bookStatus.label', default: 'BookStatus'), bookStatus.id])
                redirect bookStatus
            }
            '*'{ respond bookStatus, [status: OK] }
        }
    }

    @Transactional
    def delete(BookStatus bookStatus) {

        if (bookStatus == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        bookStatus.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'bookStatus.label', default: 'BookStatus'), bookStatus.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'bookStatus.label', default: 'BookStatus'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
