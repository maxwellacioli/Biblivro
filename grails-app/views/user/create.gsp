<%@ page import="biblivro.User" %>
<%@ page import="biblivro.Address" %>
<%@ page import="biblivro.BankAccount" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title>Cadastro de Usuário no Biblivro</title>
    </head> 
    <body>
        <div id="create-user" class="content scaffold-create" role="main">
            
            <h1>Cadastro de Usuário no Biblivro</h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.user}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.user}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>

            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="user.name.label" default="Nome" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" maxlength="50" value="${userInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="username"><g:message code="user.username.label" default="Username" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'username', 'errors')}">
                                    <g:textField name="username" maxlength="15" value="${userInstance?.username}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="password"><g:message code="user.password.label" default="Senha" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'password', 'errors')}">
                                    <g:passwordField name="password" value="${userInstance?.password}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="gender"><g:message code="user.gender.label" default="Sexo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'gender', 'errors')}">
                                    <g:select name="gender" from="${User.constrainedProperties.gender.inList}" value="${userInstance?.gender}" valueMessagePrefix="user.gender"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="email"><g:message code="user.email.label" default="Email" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'email', 'errors')}">
                                    <g:textField name="email" value="${userInstance?.email}" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="street"><g:message code="address.street.label" default="Endereço" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance.address, field: 'street', 'errors')}">
                                    <g:textField name="street" maxlength="200" value="${userInstance.address?.street}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="city"><g:message code="address.city.label" default="Cidade" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance.address, field: 'city', 'errors')}">
                                    <g:textField name="city" maxlength="50" value="${userInstance.address?.city}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="neighbourhood"><g:message code="address.neighbourhood.label" default="Bairro" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance.address, field: 'neighbourhood', 'errors')}">
                                    <g:textField name="neighbourhood" maxlength="50" value="${userInstance.address?.neighbourhood}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="state"><g:message code="address.state.label" default="Estado" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance.address, field: 'state', 'errors')}">
                                    <g:select name="state" from="${Address.constrainedProperties.state.inList}" value="${userInstance.address?.state}" valueMessagePrefix="address.state"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cep"><g:message code="address.cep.label" default="CEP" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance.address, field: 'cep', 'errors')}">
                                    <g:textField name="cep" value="${fieldValue(bean: userInstance.address, field: 'cep')}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="bank"><g:message code="bankAccount.bank.label" default="Banco" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance.bankAccount, field: 'bank', 'errors')}">
                                    <g:select name="bank" from="${BankAccount.constrainedProperties.bank.inList}" value="${userInstance.bankAccount?.bank}" valueMessagePrefix="bankAccount.bank"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="agency"><g:message code="bankAccount.agency.label" default="Agência" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance.bankAccount, field: 'agency', 'errors')}">
                                    <g:textField name="agency" value="${userInstance.bankAccount?.agency}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="account"><g:message code="bankAccount.account.label" default="Conta" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance.bankAccount, field: 'account', 'errors')}">
                                    <g:textField name="account" value="${userInstance.bankAccount?.account}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="type"><g:message code="bankAccount.type.label" default="Tipo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: userInstance.bankAccount, field: 'type', 'errors')}">
                                    <g:select name="type" from="${BankAccount.constrainedProperties.type.inList}" value="${userInstance.bankAccount?.type}" valueMessagePrefix="bankAccount.type"  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="Salvar" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
