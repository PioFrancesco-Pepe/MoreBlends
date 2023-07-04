package control;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PhotoControl {
	
	private static Logger l = Logger.getLogger("ImageLogger");
	
	private PhotoControl(){
			super();
		}
		
	public static synchronized byte[] load(String id) {

		
		
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		byte[] bt = null;
	
		try {
			
			connection = DBConnectionPool.getConnection();
			String sql = "SELECT immagineProdotto FROM prodotto WHERE idprodotto = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				bt = rs.getBytes("immagineProdotto");
			}

		} catch (SQLException sqlException) {
			l.log(Level.WARNING,sqlException, ()-> "Errore: "+sqlException);
		} 
			finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException sqlException) {
				l.log(Level.WARNING,sqlException, ()-> "Errore: "+sqlException);
			} finally {
				if (connection != null) 
					DBConnectionPool.releaseConnection(connection);
			}
		}
		return bt;
	}
	
	public static synchronized void updatePhoto(String idA, InputStream photo) 
			throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBConnectionPool.getConnection();
			stmt = con.prepareStatement("UPDATE prodotto SET immagineprodotto = ? WHERE idprodotto = ?");
			try {
				stmt.setBinaryStream(1, photo, photo.available());
				stmt.setString(2, idA);	
				stmt.executeUpdate();
				con.commit();
			} catch (IOException e) {
				l.log(Level.WARNING,e, ()-> "Errore: "+e);
			}
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException sqlException) {
				l.log(Level.WARNING,sqlException, ()-> "Errore: "+sqlException);
			} finally {
				if (con != null)
					DBConnectionPool.releaseConnection(con);
			}
		}
	}
}
