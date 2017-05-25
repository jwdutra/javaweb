package controller;

import java.io.IOException;
import java.util.List;

import javax.print.attribute.standard.PrinterLocation;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaweb.persistencia.entidade.Usuario;
import javaweb.persistencia.jdbc.UsuarioDAO;

@WebServlet("/usuario.do") // o que será chamado na URL
public class UsuarioController extends HttpServlet {
	
	public UsuarioController() {
		
		//System.out.println("Novo Servlet");
		
	}
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		//System.out.println("init...");
		super.init();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);

		String acao = req.getParameter("acao");
		String id = req.getParameter("id");
		Usuario usu = new Usuario();
		
		System.out.println(acao);
		
		
		if(id != null && !id.isEmpty() && acao != null && acao.equals("alterar")) {

			//Pesquisar usuario 
			UsuarioDAO usuDAO = new UsuarioDAO();
			usu = usuDAO.buscaPorId(Integer.parseInt(id)); 

			req.setAttribute("usu", usu);
			req.getRequestDispatcher("WEB-INF/usuario.jsp").forward(req,resp);
		} else 
		if(acao != null && acao.equals("novo")) {

				usu.setId(0); 
				usu.setNome("");
				usu.setLogin("");
				usu.setSenha(""); 
				req.setAttribute("usu", usu);
				req.getRequestDispatcher("WEB-INF/usuario.jsp").forward(req,resp);
		} else 
		if(id!=null && !id.isEmpty()) {
			usu.setId(Integer.parseInt(id));

			UsuarioDAO usuDAO = new UsuarioDAO();
			usuDAO.excluir(usu); 

			resp.sendRedirect("usuario.do");
		} else {
			
			UsuarioDAO  usuarioDAO = new UsuarioDAO();
			List<Usuario> lista = usuarioDAO.buscaTodos();
			
			req.setAttribute("lista",lista);
			//encaminha o request e o response para o JSP
			req.getRequestDispatcher("WEB-INF/listausuarios.jsp").forward(req,resp);
			
		}

	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub 

		String enviar = req.getParameter("enviar");

		String id = req.getParameter("id");
		String nome = req.getParameter("nome");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		
		// Cria usuário
		Usuario usu = new Usuario();
		usu.setNome(nome);
		usu.setLogin(login);
		usu.setSenha(senha); 
		if(id!=null && !id.isEmpty())
			usu.setId(Integer.parseInt(id));
		
		//Cadastrando usuario 
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.salvar(usu); 
		
		resp.sendRedirect("usuario.do");
		//resp.getWriter().print("Gravado com sucesso");
		//resp.getWriter().print(enviar);
	
	}
	
	public void destroy() {
		System.out.println("Finalizando o Servlet");
		super.destroy();

	}

}
