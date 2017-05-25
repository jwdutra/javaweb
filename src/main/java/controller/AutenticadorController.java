package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javaweb.persistencia.entidade.Usuario;
import javaweb.persistencia.jdbc.UsuarioDAO;

@WebServlet("/autenticador.do") // o que ser� chamado na URL
public class AutenticadorController  extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession sessao =  req.getSession(false); // O par�metro false faz com que n�o crie sess�o nova 
		
		if (sessao != null ) {
			sessao.invalidate();
		}
		
		resp.sendRedirect("index.html");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		
		Usuario usu = new Usuario();
		usu.setLogin(login);
		usu.setSenha(senha); 

		UsuarioDAO usuDAO = new UsuarioDAO();
		Usuario usuAutenticado = usuDAO.autenticar(usu); 
		
		if(usuAutenticado != null){
			//5) Criar Sess�o
			HttpSession sessao =  req.getSession();
			//6) Adicionar objeto como atributo da sess�o
			sessao.setAttribute("usuAutenticado", usuAutenticado);
			//Definindo um tempo para a Sess�o expirar
			sessao.setMaxInactiveInterval(3000);
			//7)Encaminhar para a tela de bem vindo
			req.getRequestDispatcher("usuario.do").forward(req,resp);
		}else {
			req.getRequestDispatcher("index.html").forward(req,resp);
		}
	
	}
		
		
}
