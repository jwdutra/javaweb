package javaweb;

import java.util.List;

import javaweb.persistencia.entidade.Usuario;
import javaweb.persistencia.jdbc.UsuarioDAO;

public class TesteUsuarioDAO {

	public static void main(String[] args) {
		//testCadastrar();
		//testAlterar();
		//testExcluir();
		//testSalvar();
		//testBuscarPorId();
		//testBuscarTodos();
		testAutenticar();
	}
	
	public static void testCadastrar() {
		// Cria usuário
		Usuario usu = new Usuario();
		usu.setNome("Jao 2");
		usu.setLogin("sjao");
		usu.setSenha("1234");
		
		//Cadastrando usuario 
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.cadastrar(usu);
		
		System.out.println("Cadastrado com sucesso");
	}

	public static void testAlterar() {
		// Cria usuário
		Usuario usu = new Usuario();
		usu.setId(1);
		usu.setNome("Jao de Oliveira");
		usu.setLogin("sjaooliv");
		usu.setSenha("123456");
		
		//Cadastrando usuario 
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.alterar(usu);
		
		System.out.println("Alterado com sucesso");
	}

	public static void testExcluir() {
		// Cria usuário
		Usuario usu = new Usuario();
		usu.setId(2);
		
		//Cadastrando usuario 
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.excluir(usu);
		
		System.out.println("Excluido com sucesso");
	}
	
	public static void testSalvar() {
		// Cria usuário
		Usuario usu = new Usuario();
		//usu.setId(3);
		usu.setNome("Jao de Oliveira xxxx");
		usu.setLogin("sjaooliv xxxx");
		usu.setSenha("123456");
		
		//Cadastrando usuario 
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.salvar(usu);
		
		System.out.println("Salvo com sucesso");
	}
	
	private static void testBuscarPorId() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscaPorId(1);
		System.out.println(usuario);
	}	

	private static void testBuscarTodos() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> lista = usuarioDAO.buscaTodos();
		
		for (Usuario u : lista) {
			System.out.println(u);
		}
		
	}
	
	private static void testAutenticar() {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		Usuario usu = new Usuario();
		usu.setLogin("jao");
		usu.setSenha("123456");
		
		Usuario usuRetorno = usuarioDAO.autenticar(usu);
		if(usuRetorno!=null) 
			System.out.println(usuRetorno);
		else 
			System.out.println("Usuário não encontrado");
	}	
	
	

}
