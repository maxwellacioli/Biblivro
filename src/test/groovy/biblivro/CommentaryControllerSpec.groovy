package biblivro

import grails.test.mixin.*
import spock.lang.*

@TestFor(CommentaryController)
@Mock(Commentary)
class CommentaryControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.commentaryList
            model.commentaryCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.commentary!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def commentary = new Commentary()
            commentary.validate()
            controller.save(commentary)

        then:"The create view is rendered again with the correct model"
            model.commentary!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            commentary = new Commentary(params)

            controller.save(commentary)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/commentary/show/1'
            controller.flash.message != null
            Commentary.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def commentary = new Commentary(params)
            controller.show(commentary)

        then:"A model is populated containing the domain instance"
            model.commentary == commentary
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def commentary = new Commentary(params)
            controller.edit(commentary)

        then:"A model is populated containing the domain instance"
            model.commentary == commentary
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/commentary/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def commentary = new Commentary()
            commentary.validate()
            controller.update(commentary)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.commentary == commentary

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            commentary = new Commentary(params).save(flush: true)
            controller.update(commentary)

        then:"A redirect is issued to the show action"
            commentary != null
            response.redirectedUrl == "/commentary/show/$commentary.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/commentary/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def commentary = new Commentary(params).save(flush: true)

        then:"It exists"
            Commentary.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(commentary)

        then:"The instance is deleted"
            Commentary.count() == 0
            response.redirectedUrl == '/commentary/index'
            flash.message != null
    }
}
