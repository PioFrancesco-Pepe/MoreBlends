package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Prodotto;

public class ProdottoControl {

	public synchronized static List<Prodotto> load() {

		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Prodotto> model = new ArrayList<>();

		try {
			connection = DBConnectionPool.getConnection();
			String sql = "SELECT * FROM prodotto";
			stmt = connection.prepareStatement(sql);

			rs = stmt.executeQuery();

			while (rs.next()) {
				Prodotto item = new Prodotto();
				item.setId(rs.getInt("idprodotto"));
				item.setNome(rs.getString("nomeProdotto"));
				item.setDescrizione(rs.getString("descrizione"));
				item.setDescrizioneAmpia(rs.getString("descrizioneAmpia"));
				item.setDatainserimento(rs.getString("dataInserimento"));
				item.setPrezzoVendita(rs.getFloat("prezzovendita"));
				item.setCosto(rs.getFloat("costo"));
				model.add(item);
			}

		} catch (SQLException sqlException) {
			System.out.println(sqlException);
		} 
			finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException sqlException) {
				System.out.println(sqlException);
			} finally {
				if (connection != null) 
					DBConnectionPool.releaseConnection(connection);
			}
		}
		return model;
	}
	public synchronized static List<Prodotto> loadNewProduct() {

		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Prodotto> model = new ArrayList<>();

		try {
			connection = DBConnectionPool.getConnection();
			String sql = "SELECT * FROM prodotto order by dataInserimento desc LIMIT 4";
			
			stmt = connection.prepareStatement(sql);
			
			rs = stmt.executeQuery();

			while (rs.next()) {
				Prodotto item = new Prodotto();
				item.setId(rs.getInt("idprodotto"));
				item.setNome(rs.getString("nomeProdotto"));
				item.setDescrizione(rs.getString("descrizione"));
				item.setDatainserimento(rs.getString("dataInserimento"));
				item.setPrezzoVendita(rs.getFloat("prezzovendita"));
				item.setCosto(rs.getFloat("costo"));
				model.add(item);
			}

		} catch (SQLException sqlException) {
			System.out.println(sqlException);
		} 
			finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException sqlException) {
				System.out.println(sqlException);
			} finally {
				if (connection != null) 
					DBConnectionPool.releaseConnection(connection);
			}
		}
		return model;
	}
}
