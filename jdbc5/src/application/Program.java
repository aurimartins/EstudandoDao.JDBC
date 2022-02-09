/* Deletando Dados na base de Dados; 
 * Criando uma mensagem Exception personalizada (DbIntegrityException);
 * */

package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbIntegrityException;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
	
			st = conn.prepareStatement(
					"DELETE FROM department "
					+ "WHERE "
					+ "Id = ?");
					
			//st.setInt(1, 2); // Ao executar vai estourar a Exception
			st.setInt(1, 5 );// Apaga o departamento que tem o Id=5, isso se não tiver nenhum funcionario cadastrado nesse departamento;
			
			int rowsAffected = st.executeUpdate();
			
			System.out.println("Done! Rows affected: " + rowsAffected);
		}
		catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		
		}
	}
}