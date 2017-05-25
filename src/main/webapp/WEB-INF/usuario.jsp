<%@ page import="javaweb.persistencia.entidade.Usuario" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Usuario</title>
</head>
<body>

	<%
		Usuario usuAlt = (Usuario)request.getAttribute("usu");
	%>
	
	<form method="post" action="usuario.do">
		<input  type="hidden" name="acao" value="salvar">
		ID: <input size="5" type="text" name="id" value="<%=usuAlt.getId()%>" readonly><br><br>
		Nome: <input  type="text" name="nome" value="<%=usuAlt.getNome()%>"><br><br>
		Login: <input  type="text" name="login" value="<%=usuAlt.getLogin()%>"><br><br>
		Senha: <input  type="password" name="senha" value="<%=usuAlt.getSenha()%>"><br><br>
		<input type="submit" name="enviar" value="enviar"> 
	</form>

</body>
</html>