package javaweb.persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

	public static Connection getConnection() {

		try {
			Class.forName("org.postgresql.Driver"); // Força o carregamento do driver por causa da web
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/javaweb", "mobinove_la", "neia5020");
		} catch (SQLException e) {
			// Relançando a exception
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		
	}

}
