package biblivro

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ShiroUserController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ShiroUser.list(params), model:[shiroUserCount: ShiroUser.count()]
    }

    def show(ShiroUser shiroUser) {
        respond shiroUser
    }

    def create() {
        respond new ShiroUser(params)
    }

    @Transactional
    def save(ShiroUser shiroUser) {
        if (shiroUser == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (shiroUser.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond shiroUser.errors, view:'create'
            return
        }

        shiroUser.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'shiroUser.label', default: 'ShiroUser'), shiroUser.id])
                redirect shiroUser
            }
            '*' { respond shiroUser, [status: CREATED] }
        }
    }

    def edit(ShiroUser shiroUser) {
        respond shiroUser
    }

    @Transactional
    def update(ShiroUser shiroUser) {
        if (shiroUser == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (shiroUser.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond shiroUser.errors, view:'edit'
            return
        }

        shiroUser.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'shiroUser.label', default: 'ShiroUser'), shiroUser.id])
                redirect shiroUser
            }
            '*'{ respond shiroUser, [status: OK] }
        }
    }

    @Transactional
    def delete(ShiroUser shiroUser) {

        if (shiroUser == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        shiroUser.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'shiroUser.label', default: 'ShiroUser'), shiroUser.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'shiroUser.label', default: 'ShiroUser'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
