package biblivro

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index = {
        if(session.user)
        redirect(action: "home")
        else
        redirect(uri:"/")
    }

    def home = {

        if(session.user) {
            def userInstance = User.get(session.getAttribute("user").id)
            def booksList = Book.findAllByUserAndBookStatus(userInstance,BookStatus.get(BookStatus."NEW"))
            def friendsList = userInstance.friends
            def atualizationsList = []
            
            def friendsListAux = []
            friendsList.each {
                friendsListAux.add(it.id)
            }
            if(!friendsListAux.isEmpty())  
            atualizationsList = Atualization.findAll("from Atualization as a where a.user.id in (:friends) order by a.date desc", [friends:friendsListAux])
              
            params.max = Math.min(params.max ? params.int('max') : 10, 100)
            [userInstance: userInstance, 
            booksList: booksList, booksTotal: booksList.size(),
            friendsList: friendsList, friendsTotal: friendsList.size(),
            atualizationsList: atualizationsList, attsTotal: atualizationsList.size()]
        } else {
            redirect(uri:"/")
        }

    }

    def show(User user) {

        def userInstance = user

        def friendInstance
        def listFriends = []
        def listProducts = []
        def atualizationsList = []
                
        if(session.user) {        
            friendInstance =  User.get(session.getAttribute("user").id)
            listFriends = friendInstance.friends        
        }
        
        listProducts = Book.findAllByUserAndBookStatus(userInstance,BookStatus.get(BookStatus."NEW"))
        
        atualizationsList = Atualization.findAll("from Atualization as a where a.user.id = ${userInstance.id} order by a.date desc")
        
        if (!userInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'user'), params.id])}"
            redirect(action: "list")
        }
        else {
            
            [friendInstance: friendInstance, listFriends: listFriends, userInstance: userInstance, listProducts: listProducts,
            attsList: atualizationsList, attsTotal: atualizationsList.size()]
        }
    }

    def create() {
        def userInstance = new User()
        userInstance.properties = params
        return [userInstance: userInstance]
    }

    @Transactional
    def save(User user) {

        def userInstance = user
        userInstance.setPassword(params.password)

        def address = new Address()
        address.street = params.street
        address.city = params.city
        address.neighbourhood = params.neighbourhood
        address.state = params.state
        address.cep = params.cep
        
        def bankAccount = new BankAccount()
        bankAccount.bank = params.bank
        bankAccount.agency = Integer.parseInt(params.agency)
        bankAccount.account = Integer.parseInt(params.account)
        bankAccount.type = params.type
        
        if(address.save(flush: true)){
            if(bankAccount.save(flush: true)){                
                userInstance.address = address
                userInstance.bankAccount = bankAccount
                if (userInstance.save(flush: true)) {

                    flash.message = "Usuário Salvo com Sucesso!"
                    redirect(action: "show", id: userInstance.id)
                }
                else {
                    print userInstance.errors
                    render(view: "create", model: [userInstance: userInstance])
                }
            }
        }

   }

    def edit(User user) {
        respond user
    }

    @Transactional
    def update(User user) {
        if (user == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (user.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond user.errors, view:'edit'
            return
        }

        user.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*'{ respond user, [status: OK] }
        }
    }

    @Transactional
    def delete(User user) {

        if (user == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        user.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
