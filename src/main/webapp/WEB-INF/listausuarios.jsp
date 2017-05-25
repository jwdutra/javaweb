<%@ page import="javaweb.persistencia.entidade.Usuario" %>
<%@ page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de usuarios</title>

<script type="text/javascript">

	function excluir(id) {
		if (confirm('Deseja excluir?')) {
			location.href="usuario.do?id="+id;
		    alert('Excluído');
		} else {
		    alert('Cancelado');
		} 
	}
	
	function alterar(id) {
		location.href="usuario.do?id="+id+"&acao=alterar";
	}

	function novo() {
		location.href="usuario.do?acao=novo";
	}

	function sair() {
		location.href="autenticador.do";
	}

</script>

</head>
<body> 

	<%
		//captuando a lista do request
		List<Usuario> lista = (List<Usuario>) request.getAttribute("lista");
		Usuario usuAutenticado = (Usuario) session.getAttribute("usuAutenticado");
	%>

	<p>
		Seja bem vindo <b><%= usuAutenticado.getNome() + " id:"+ usuAutenticado.getId()%></b>
	</p>

	<a href="#" onClick="novo()">Incluír novo usuário</a>  -  

	<a href="#" onClick="sair()">Sair</a><br><br>
	
	<table border="1">
		<tr bgcolor="#eaeaea">
			<td> ID </td>
			<td> NOME </td>
			<td> LOGIN </td>
			<td> SENHA </td>
			<td> AÇÃO </td>
		</tr>
		<% for (Usuario u: lista) {%>
		<tr>
			<td> <%= u.getId() %> </td>
			<td> <%= u.getNome() %>  </td> 
			<td> <%= u.getLogin() %>  </td>
			<td> <%= u.getSenha() %>  </td>
			<td> 
				<a href="#" onClick="excluir(<%= u.getId() %>)">Excluir</a> -  
				<a href="#" onClick="alterar(<%= u.getId() %>)">Alterar</a>  
			</td>
		</tr>
		<% } %>
	</table>
	
	

</body>
</html>