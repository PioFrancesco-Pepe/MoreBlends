package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import model.Prodotto;

public class ProdottoControl implements IBeanDAO<Prodotto> {

	private static final String TABLE_NAME = "prodotto";
	
	public static synchronized List<Prodotto> load() {

		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Prodotto> model = new ArrayList<>();

		try {
			connection = DBConnectionPool.getConnection();
			String sql = "SELECT * FROM prodotto AS p,locazione AS l, magazzino AS m WHERE p.idProdotto=l.idProdotto AND l.idMagazzino=m.idMagazzino";
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
				item.setQuantita(rs.getInt("disponibilita"));
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
	public static synchronized List<Prodotto> loadNewProduct() {

		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Prodotto> model = new ArrayList<>();

		try {
			connection = DBConnectionPool.getConnection();
			String sql = "SELECT * FROM prodotto AS p,locazione AS l, magazzino AS m WHERE p.idProdotto=l.idProdotto AND l.idMagazzino=m.idMagazzino AND l.disponibilita > 0 ORDER BY p.dataInserimento desc LIMIT 4";
			
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
				item.setQuantita(rs.getInt("disponibilita"));
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
	
	public static synchronized List<Prodotto> loadSearchProduct(String search) {

		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Prodotto> model = new ArrayList<>();

		try {
			connection = DBConnectionPool.getConnection();
			String sql="SELECT * FROM prodotto AS p,locazione AS l, magazzino AS m WHERE p.idProdotto=l.idProdotto AND l.idMagazzino=m.idMagazzino AND NomeProdotto LIKE '%"+search+"%'";
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
				item.setQuantita(rs.getInt("disponibilita"));
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
	
	public static synchronized  List<String> loadMarca() {

		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<String> model = new ArrayList<>();

		try {
			connection = DBConnectionPool.getConnection();
			String sql = "SELECT * FROM Categoria";
			stmt = connection.prepareStatement(sql);
			
			rs = stmt.executeQuery();

			while (rs.next()) 
				model.add(rs.getString("NomeCategoria"));

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
	
	
	public static List<Prodotto> loadSearchProduct(String search, String marca) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Prodotto> model = new ArrayList<>();

		try {
			connection = DBConnectionPool.getConnection();
			String sql = "SELECT * FROM prodotto AS p,categoria AS c WHERE p.IDCategoria=c.IDCategoria AND p.NomeProdotto LIKE '%"+search.strip()+"%' AND c.NomeCategoria='"+marca+"'";
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
	

	@Override
	public synchronized void doSave(Prodotto product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ProdottoControl.TABLE_NAME
				+ " (NAME, DESCRIPTION, PRICE, QUANTITY) VALUES (?, ?, ?, ?)";

		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, product.getNome());
			preparedStatement.setString(2, product.getDescrizione());
			preparedStatement.setFloat(3, product.getPrezzoVendita());
			preparedStatement.setFloat(4, product.getCosto());

			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DBConnectionPool.releaseConnection(connection);
			}
		}
	}

	@Override
	public synchronized Prodotto doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Prodotto item = new Prodotto();

		String selectSQL = "SELECT * FROM prodotto AS p,locazione AS l, magazzino AS m WHERE p.idProdotto=l.idProdotto AND l.idMagazzino=m.idMagazzino AND p.IDProdotto = ?";
		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				item.setId(rs.getInt("idprodotto"));
				item.setNome(rs.getString("nomeProdotto"));
				item.setDescrizione(rs.getString("descrizione"));
				item.setDescrizioneAmpia(rs.getString("descrizioneAmpia"));
				item.setDatainserimento(rs.getString("dataInserimento"));
				item.setPrezzoVendita(rs.getFloat("prezzovendita"));
				item.setCosto(rs.getFloat("costo"));
				item.setQuantita(rs.getInt("disponibilita"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DBConnectionPool.releaseConnection(connection);
			}
		}
		return item;
	}

	@Override
	public synchronized boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ProdottoControl.TABLE_NAME + " WHERE IDProdotto = ?";

		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DBConnectionPool.releaseConnection(connection);
			}
		}
		return (result != 0);
	}

	@Override
	public synchronized Collection<Prodotto> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Prodotto> products = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + ProdottoControl.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Prodotto item = new Prodotto();

				item.setId(rs.getInt("idprodotto"));
				item.setNome(rs.getString("nomeProdotto"));
				item.setDescrizione(rs.getString("descrizione"));
				item.setDescrizioneAmpia(rs.getString("descrizioneAmpia"));
				item.setDatainserimento(rs.getString("dataInserimento"));
				item.setPrezzoVendita(rs.getFloat("prezzovendita"));
				item.setCosto(rs.getFloat("costo"));
				products.add(item);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DBConnectionPool.releaseConnection(connection);
			}
		}
		return products;
	}
	
	public static synchronized boolean updateQuantita(int code,int quantita,int quantitaOrdinata) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		String updateSQL="Update locazione SET Disponibilita = ? WHERE IdProdotto = ? AND IdMagazzino = ?";
		
		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setInt(1, (quantita-quantitaOrdinata));
			preparedStatement.setInt(2,code);
			preparedStatement.setInt(3, 1);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DBConnectionPool.releaseConnection(connection);
			}
		}
		return (result != 0);
	}
}
