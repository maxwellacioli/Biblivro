package biblivro

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DealStatusController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DealStatus.list(params), model:[dealStatusCount: DealStatus.count()]
    }

    def show(DealStatus dealStatus) {
        respond dealStatus
    }

    def create() {
        respond new DealStatus(params)
    }

    @Transactional
    def save(DealStatus dealStatus) {
        if (dealStatus == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dealStatus.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dealStatus.errors, view:'create'
            return
        }

        dealStatus.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dealStatus.label', default: 'DealStatus'), dealStatus.id])
                redirect dealStatus
            }
            '*' { respond dealStatus, [status: CREATED] }
        }
    }

    def edit(DealStatus dealStatus) {
        respond dealStatus
    }

    @Transactional
    def update(DealStatus dealStatus) {
        if (dealStatus == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dealStatus.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dealStatus.errors, view:'edit'
            return
        }

        dealStatus.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'dealStatus.label', default: 'DealStatus'), dealStatus.id])
                redirect dealStatus
            }
            '*'{ respond dealStatus, [status: OK] }
        }
    }

    @Transactional
    def delete(DealStatus dealStatus) {

        if (dealStatus == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        dealStatus.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'dealStatus.label', default: 'DealStatus'), dealStatus.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dealStatus.label', default: 'DealStatus'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
