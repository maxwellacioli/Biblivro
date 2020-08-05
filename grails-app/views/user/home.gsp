<%@ page import="biblivro.Book" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'livro.label', default: 'Livro')}" />
        <title>Bem vindo</title>
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
            <span class="menuButton"><a class="home" href="${createLink(action: 'show' , id:userInstance.id)}">Perfil</a></span>
            <span class="menuButton"><a class="create" href="${createLink(action: 'messages' , id:userInstance.id)}">Recados</a></span>
            <span class="menuButton"><a class="create" href="${createLink(uri: '/book/create')}">Novo Livro</a></span>
            <span class="menuButton"><a class="list" href="${createLink(uri: '/sale/list')}">Negócios</a></span>
            <span class="menuButton"><a class="list" href="${createLink(uri: '/book/list')}">Todos os livros</a></span>
            <span class="menuButton"><a class="list" href="${createLink(uri: '/user/list')}">Todos os usuários</a></span>
        </div>
        
        <div class="body">
            
        <div id="page-wrap">

		<h1>Bem Vindo, ${userInstance.username}!</h1>
		<h1></h1>
        
        <div id="infos">
        			
        	<ul class="nav">
                <li class="nav-one"><a href="#books" class="current">Seus Livros</a></li>
                <li class="nav-two"><a href="#friends">Seus Amigos</a></li>
                <li class="nav-three biglast"><a href="#atts">Atualizações dos Amigos</a></li>
            </ul>
        	
        	<div class="list-wrap">        		

        		<ul id="books">
				<div class="list">
					<table>
                        <thead>
                            <tr>
                                <g:sortableColumn property="title" title="${message(code: 'book.title.label', default: 'Título')}" />
                            
                                <g:sortableColumn property="author" title="${message(code: 'book.author.label', default: 'Autor')}" />
                            
                                <g:sortableColumn property="edition" title="${message(code: 'book.edition.label', default: 'Edição')}" />
                            
                                <g:sortableColumn property="category" title="${message(code: 'book.category.label', default: 'Categoria')}" />
                            
                            </tr>
                        </thead>
                        <tbody>
                        <g:each in="${booksList}" status="i" var="bookInstance">
                            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            
                                <td><g:link action="show" controller="book" id="${bookInstance.id}">${fieldValue(bean: bookInstance, field: "title")}</g:link></td>
                            
                                <td>${fieldValue(bean: bookInstance, field: "author")}</td>
                            
                                <td>${fieldValue(bean: bookInstance, field: "edition")}</td>
                            
                                <td>${fieldValue(bean: bookInstance, field: "category")}</td>

                            </tr>
                        </g:each>
                        </tbody>
                    </table>
                    <div class="paginateButtons">
                        <g:paginate total="${booksTotal}" />
                    </div>
				</div>
        		</ul>
        		 
        		<ul id="friends" class="hide">
				<div class="list">
					<table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="name" title="${message(code: 'user.name.label', default: 'Nome')}" />
                        
                            <g:sortableColumn property="username" title="${message(code: 'user.username.label', default: 'Username')}" />
                        
                            <g:sortableColumn property="gender" title="${message(code: 'user.gender.label', default: 'Sexo')}" />
                        
                            <g:sortableColumn property="email" title="${message(code: 'user.email.label', default: 'Email')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${friendsList}" status="i" var="friendInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${friendInstance.id}">${fieldValue(bean: friendInstance, field: "name")}</g:link></td>
                        
                            <td>${fieldValue(bean: friendInstance, field: "username")}</td>
                        
                            <g:if test = "${friendInstance.gender ==  'Feminino'}">
                                <td>F</td>
                            </g:if>  <g:else> <td>M</td> </g:else>
                        
                            <td>${fieldValue(bean: friendInstance, field: "email")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                    </table>
                    <div class="paginateButtons">
                        <g:paginate total="${friendsTotal}" />
                    </div>
				</div>
        		</ul>
        		
                <ul id="atts" class="hide">
				<div class="list">
                    <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="date" title="${message(code: 'atualization.date.label', default: 'Data')}" />
                            
                            <g:sortableColumn property="user" title="${message(code: 'atualization.amigo.label', default: 'Amigo')}" />
                            
                            <g:sortableColumn property="atualization" title="${message(code: 'atualization.atualization.label', default: 'Atualizacao')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${atualizationsList}" status="i" var="atualizationInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:formatDate format="dd-MMM-yyyy, HH:mm" date="${atualizationInstance.date}" /></td>
                            
                            <td><g:link action="show" id="${atualizationInstance.user.id}">${fieldValue(bean: atualizationInstance, field: "user")}</g:link></td>
                       
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
