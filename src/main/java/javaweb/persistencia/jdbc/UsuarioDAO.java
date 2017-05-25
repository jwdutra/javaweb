package javaweb.persistencia.jdbc;

import javaweb.persistencia.entidade.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

	private Connection con = ConexaoFactory.getConnection();
	
	public void cadastrar(Usuario usu) {
		String sql = "Insert into usuarios (nome, login, senha) values (?, ?, ?)";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			
			preparador.execute();
			preparador.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void alterar(Usuario usu) {
		String sql = "Update usuarios set nome=?, login=?, senha=? where id=? ";
		
		try (PreparedStatement preparador = con.prepareStatement(sql);) { // Assim não precisa executar close, ele fecha automaticamente
			
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			preparador.setInt(4, usu.getId());
			
			preparador.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public void excluir(Usuario usu) {
		String sql = "DELETE FROM usuarios where id=? ";
		
		try (PreparedStatement preparador = con.prepareStatement(sql);) { // Assim não precisa executar close, ele fecha automaticamente
			
			preparador.setInt(1, usu.getId());
			
			preparador.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	} 

	public void salvar(Usuario usu){
		if(usu.getId()!=null && usu.getId()>0){
			alterar(usu);
		}else{
			cadastrar(usu);
		}
	}	

	/**
	 * Busca de um registro de usuário pelo ID
	 * @param id - inteiro usado para buscar o registro
	 * @return - Null ou um objeto usuário com o registro buscado
	 */
	public Usuario buscaPorId(Integer id) { 
		//Objeto de retorno do método
		Usuario usuRetorno = null;

		String sql = "select * from usuarios where id=?";
		
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setInt(1,id);
			//Retorno da consulta em Resultset
			ResultSet resultado = preparador.executeQuery();
			
			//Se tem registro
			if(resultado.next()){ 
				//instancia o objeto Usuario
				usuRetorno =  new Usuario();
				usuRetorno.setId(resultado.getInt("id"));
				usuRetorno.setNome(resultado.getString("nome"));
				usuRetorno.setLogin(resultado.getString("login"));
				usuRetorno.setSenha(resultado.getString("senha"));

			}
				
			System.out.println("Encontrado com sucesso!");
				
		} catch (SQLException e) {
				System.out.println("Erro de SQL:"+ e.getMessage());
		}
			
		return usuRetorno;
	}
	
	/**
	 * 
	 * @return Lista de objetos de usuários ou lista vazia
	 */
	public List<Usuario> buscaTodos(){
		//Objeto de retorno do método
		List<Usuario> lista = new ArrayList<Usuario>();
		String sql = "select * from usuarios order by id";
		
		try (PreparedStatement preparador = con.prepareStatement(sql)){

			//Retorno da consulta em Resultset
			ResultSet resultado = preparador.executeQuery();
			//Navegada nos registros
			while(resultado.next()){
				//instancia o objeto Usuario
				Usuario usu =  new Usuario();
				//Carga de dados no usuário
				usu.setId(resultado.getInt("id"));
				usu.setNome(resultado.getString("nome"));
				usu.setLogin(resultado.getString("login"));
				usu.setSenha(resultado.getString("senha"));
				//adiciona na lista
				lista.add(usu); 
				
			}
			System.out.println("Busca com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro de SQL:"+ e.getMessage());
		}
		return lista;
	}	

	
	/**
	 * 
	 * @param usuConsulta
	 * @return
	 */
	public Usuario autenticar(Usuario usuConsulta){
		//Objeto de retorno do método
		Usuario usuRetorno = null;
		String sql = "select * from usuarios where login=? and senha=?"; 
		//Object senha = md5(?)";
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			
			preparador.setString(1, usuConsulta.getLogin()); 
			preparador.setString(2, usuConsulta.getSenha());
			
			//Retorno da consulta em Resultset
			ResultSet resultado = preparador.executeQuery();
			
			//Se tem registro
			if(resultado.next())  {
				//instancia o objeto Usuario
				usuRetorno =  new Usuario();
				usuRetorno.setId(resultado.getInt("id"));
				usuRetorno.setNome(resultado.getString("nome"));
				usuRetorno.setLogin(resultado.getString("login"));
				usuRetorno.setSenha(resultado.getString("senha"));
				
				System.out.println("Usuário Autenticado");
			}   
			
		} catch (SQLException e) {
			System.out.println("Erro de SQL:"+ e.getMessage());
		}
		
		return usuRetorno;		
		
	}
	
	
	
	
}
 