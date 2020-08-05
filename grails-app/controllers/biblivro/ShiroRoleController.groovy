package biblivro

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ShiroRoleController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ShiroRole.list(params), model:[shiroRoleCount: ShiroRole.count()]
    }

    def show(ShiroRole shiroRole) {
        respond shiroRole
    }

    def create() {
        respond new ShiroRole(params)
    }

    @Transactional
    def save(ShiroRole shiroRole) {
        if (shiroRole == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (shiroRole.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond shiroRole.errors, view:'create'
            return
        }

        shiroRole.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'shiroRole.label', default: 'ShiroRole'), shiroRole.id])
                redirect shiroRole
            }
            '*' { respond shiroRole, [status: CREATED] }
        }
    }

    def edit(ShiroRole shiroRole) {
        respond shiroRole
    }

    @Transactional
    def update(ShiroRole shiroRole) {
        if (shiroRole == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (shiroRole.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond shiroRole.errors, view:'edit'
            return
        }

        shiroRole.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'shiroRole.label', default: 'ShiroRole'), shiroRole.id])
                redirect shiroRole
            }
            '*'{ respond shiroRole, [status: OK] }
        }
    }

    @Transactional
    def delete(ShiroRole shiroRole) {

        if (shiroRole == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        shiroRole.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'shiroRole.label', default: 'ShiroRole'), shiroRole.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'shiroRole.label', default: 'ShiroRole'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
