<html>
    <head>
        <title>Biblivro</title>
		<meta name="layout" content="main" />
    </head>
    <body>
    
        <div id=menu class="dialog" align="right" style="margin-right:0px;width:90%;">
        <g:if test="${session.user}">            
            <g:link action="logout" controller="auth">Logout</g:link>            
        </g:if>
		</div>
        
		<div id="pageBody">
	        <h1>Bem-vindo ao Biblivro</h1>
	        <p>Compartilhe seus livros favoritos, encontre livros para comprar, emprestar ou trocar e faça novos amigos!</p>            
        <html>

	    <div class="container">
		    <div class="row">
			    <div class=".col-6 .col-md-4"></div>

			    <div class=".col-6 .col-md-4">

			    	<g:if test="${!session.user}">
				    	<g:form name="formLogin" action="login" controller="auth">

						    <div class="form-group">
						        <label for="username">Username</label>
						        <input type="text" class="form-control" name="username" id="username"/>
						        <label for="password">Senha</label>
						        <input type="password" class="form-control" name="password" id="password" />
						    </div>
	    		
					    	<button class="btn btn-success">Entrar</button>	
				        
			        	</g:form>

			       		<g:link class="btn btn-info"action="create" controller="user">Cadastre-se</g:link>       
			        </g:if>
				
				</div>

				<div class=".col-6 .col-md-4"></div>

			</div>
		</div>
    </body>
</html>