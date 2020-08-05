
<%@ page import="biblivro.User" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'Usuario')}" />
        <title>Usuário</title>
        
    <script type='text/javascript' src='http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js'></script>
    <script type="text/javascript" src="js/organictabs.jquery.js"></script>
    <script type='text/javascript'>
    $(function() {    
    $("#infos").organicTabs();
    });
    </script>
        
    </head>
    <body>
        <div class="nav">
            
            <g:if test="${friendInstance}">
                <span class="menuButton"><a class="home" href="/user/home">Início</a></span>
                <g:if test="${userInstance.id==friendInstance.id}">
                    <span class="menuButton"><a class="home" href="${createLink(action: 'edit')}">Editar Perfil</a></span>
                </g:if> <g:else>
                    <g:if test="${listFriends.accountins(userInstance)}">
                        <span class="menuButton"><a class="create" href="${createLink(action: 'delfriend' , id:userInstance.id)}">Del amigo</a></span>
                    </g:if><g:else>
                        <span class="menuButton"><a class="create" href="${createLink(action: 'addfriend' , id:userInstance.id)}">Add amigo</a></span>
                    </g:else>
                    <span class="menuButton"><a class="create" href="${createLink(action: 'write' , id:userInstance.id)}">Escrever Recado</a></span>
                </g:else>
                <span class="menuButton"><a class="create" href="${createLink(action: 'messages' , id:userInstance.id)}">Ver Recados</a></span>
                <span class="menuButton"><a class="create" href="${createLink(action: 'tosale', id:userInstance.id)  }">Produtos a Venda</a></span>                
            </g:if>

        </div>
        <div class="body">
            <h1>Dados do usuário</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="user.name.label" default="Nome" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: userInstance, field: "name")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="user.username.label" default="Usuário" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: userInstance, field: "username")}</td>
                            
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="user.gender.label" default="Sexo" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: userInstance, field: "gender")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="user.email.label" default="Email" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: userInstance, field: "email")}</td>
                            
                        </tr>
                    </tbody>
                </table>
            </div>
            
        <div id="page-wrap">

        <div id="infos">
                    
            <ul class="nav">
        
            <g:if test="${session.user}">            
                <li class="nav-one"><a href="#more" class="current">Mais</a></li>
                <li class="nav-two"><a href="#books">Livros</a></li>
            </g:if><g:else>
                <li class="nav-two"><a href="#books" class="current">Livros</a></li>
            </g:else>
                <li class="nav-three"><a href="#friends">Amigos</a></li>
                <li class="nav-four last"><a href="#atts">Atualizações</a></li>
            </ul>
            
            <div class="list-wrap">
                <g:if test="${session.user}"> 
                <ul id="more">
                <div class="list">
                    <table>
                    <tbody>
                    <tr class="dialog">
                        <th valign="top" class="name"><g:message code="user.address.label" default="Endereco" /></th>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="address.street.label" default="Endereco" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: userInstance.address, field: "street")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="address.city.label" default="Cidade" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: userInstance.address, field: "city")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="address.neighbourhood.label" default="Bairro" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: userInstance.address, field: "neighbourhood")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="address.state.label" default="Estado" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: userInstance.address, field: "state")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="address.cep.label" default="CEP" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: userInstance.address, field: "cep")}</td>
                            
                        </tr>

                    </tr>
                    <tr class="dialog">
                        <th valign="top" class="name"><g:message code="user.bankAccount.label" default="Conta Bancária" /></th>
                        
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="bankAccount.bank.label" default="Banco" /></td>
                                
                            <td valign="top" class="value">${fieldValue(bean: userInstance.bankAccount, field: "bank")}</td>
                                
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="bankAccount.agency.label" default="Agencia" /></td>
                                
                            <td valign="top" class="value">${fieldValue(bean: userInstance.bankAccount, field: "agency")}</td>
                                
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="bankAccount.account.label" default="Conta" /></td>
                                
                            <td valign="top" class="value">${fieldValue(bean: userInstance.bankAccount, field: "account")}</td>
                                
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="bankAccount.type.label" default="Tipo" /></td>
                                
                            <td valign="top" class="value">${fieldValue(bean: userInstance.bankAccount, field: "type")}</td>
                                
                        </tr>
                    </tr>
                    </tbody>
                    </table>
                </div>
                </ul>
                </g:if> 
                <g:if test="${session.user}"> 
                <ul id="books" class="hide">
                </g:if> <g:else>
                <ul id="books" >
                </g:else>
                
                <div class="list">
                    <table>
                    <tbody>
                        <tr class="prop">
                            <th valign="top" class="name"><g:message code="user.books.label" default="Livros" /></th>
                            
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
                                    <g:each in="${listProducts}" var="p">
                                        <li><g:link controller="book" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
                                    </g:each>
                                </ul>
                            </td>
                            
                            </tr>
                    </tbody>
                    </table>
                </div>
                </ul>
                 
                <ul id="friends" class="hide">
                <div class="list">
                    <table>
                    <tbody>
                        <tr class="prop">
                            <th valign="top" class="name"><g:message code="user.friends.label" default="Amigos" /></th>
                            
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
                                <g:each in="${userInstance.friends}" var="a">
                                    <li><g:link controller="user" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    </tbody>
                    </table>
                </div>
                </ul>
                
                <ul id="atts" class="hide">
                <div class="list">
                                
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="date" title="${message(code: 'atualization.date.label', default: 'Data')}" />

                            <g:sortableColumn property="atualization" title="${message(code: 'atualization.atualization.label', default: 'Atualizacao')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${attsList}" status="i" var="atualizationInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:formatDate format="dd-MMM-yyyy, HH:mm" date="${atualizationInstance.date}" /></td>
                       
                            <td>${fieldValue(bean: atualizationInstance, field: "atualization")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            <div class="paginateButtons">
                <g:paginate total="${attsTotal}" />
            </div>
                </div>
                </ul>
                
             </div>
         
        </div>
         
    </div>

        </div>
    </body>
</html>
