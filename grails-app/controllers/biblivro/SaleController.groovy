package biblivro

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SaleController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Sale.list(params), model:[saleCount: Sale.count()]
    }

    def show(Sale sale) {
        respond sale
    }

    def create() {
        respond new Sale(params)
    }

    @Transactional
    def save(Sale sale) {
        if (sale == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (sale.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond sale.errors, view:'create'
            return
        }

        sale.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sale.label', default: 'Sale'), sale.id])
                redirect sale
            }
            '*' { respond sale, [status: CREATED] }
        }
    }

    def edit(Sale sale) {
        respond sale
    }

    @Transactional
    def update(Sale sale) {
        if (sale == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (sale.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond sale.errors, view:'edit'
            return
        }

        sale.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sale.label', default: 'Sale'), sale.id])
                redirect sale
            }
            '*'{ respond sale, [status: OK] }
        }
    }

    @Transactional
    def delete(Sale sale) {

        if (sale == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        sale.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sale.label', default: 'Sale'), sale.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sale.label', default: 'Sale'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
