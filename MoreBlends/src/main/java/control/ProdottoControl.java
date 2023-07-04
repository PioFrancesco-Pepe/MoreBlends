package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Prodotto;
import model.Report;

public class ProdottoControl implements IBeanDAO<Prodotto> {

	private static final String TABLE_NAME = "prodotto";
	private static Logger l= Logger.getLogger("ProdottoControlLogger");
	private static int lastID;
	
	public static synchronized List<Prodotto> load() {

		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		LocalDate date = LocalDate.now();
		
		List<Prodotto> model = new ArrayList<>();

		try {
			connection = DBConnectionPool.getConnection();
			String sql = "SELECT * FROM prodotto AS p,locazione AS l, magazzino AS m , StoricoPrezzi AS sp "
			+"WHERE p.idProdotto=l.idProdotto AND l.idMagazzino=m.idMagazzino "
			+"AND sp.idprodotto=p.idprodotto AND sp.DataInizio <= '"+date.toString()
			+ "' AND (sp.datafine >= '"+date.toString()+"' OR sp.datafine is null) "		
			+"ORDER BY p.idcategoria,p.idsottocategoria,p.idprodotto ASC";
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
				item.setStato(rs.getInt("stato"));
				item.setIdCategoria(rs.getInt("idcategoria"));
				item.setIdSottoCategoria(rs.getInt("idSottoCategoria"));
				model.add(item);
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
		return model;
	}
	public static synchronized List<Prodotto> loadNewProduct() {

		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		LocalDate date = LocalDate.now();
		
		List<Prodotto> model = new ArrayList<>();

		try {
			connection = DBConnectionPool.getConnection();
			String sql = "SELECT * FROM prodotto AS p,locazione AS l, magazzino AS m, storicoPrezzi AS sp "
			+"WHERE p.idProdotto=l.idProdotto AND l.idMagazzino=m.idMagazzino AND sp.idprodotto=p.idprodotto "
			+"AND l.disponibilita > 0 AND sp.DataInizio <= '"+date.toString()
			+"' AND (sp.datafine >= '"+date.toString()+"' OR sp.datafine is null) AND sp.datafine is null AND p.stato=1 "
			+"ORDER BY p.dataInserimento desc LIMIT 4";
			
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
				item.setStato(rs.getInt("stato"));
				item.setIdCategoria(rs.getInt("idcategoria"));
				item.setIdSottoCategoria(rs.getInt("idSottoCategoria"));
				model.add(item);
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
		return model;
	}
	
	public static synchronized Collection<Prodotto> loadSearchProduct(String search, String marca,String sistema) {

		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		LocalDate date = LocalDate.now();
		
		Collection<Prodotto> model = new ArrayList<>();

		try {
			connection = DBConnectionPool.getConnection();
			String sql="SELECT * FROM prodotto AS p,locazione AS l, magazzino AS m, categoria AS c,sottocategoria AS st, storicoprezzi AS sp"
					+" WHERE sp.idprodotto=p.idprodotto AND p.idProdotto=l.idProdotto "
					+"AND l.idMagazzino=m.idMagazzino AND c.idcategoria=p.idcategoria"
					+" AND st.idsottocategoria=p.idsottocategoria "
					+" AND sp.DataInizio <= '"+date.toString()
					+"' AND (sp.datafine >= '"+date.toString()+"' OR sp.datafine is null) AND sp.datafine is null "; 
			
			boolean b1=false;
			boolean b2=false;
			int i=1;
			
			if(!marca.equals("0"))
			{
				sql+=" AND c.idcategoria=? ";
				b1=true;
			}	
			if(!sistema.equals("0"))
			{
				sql+=" AND st.idsottocategoria=? ";
				b2=true;
			}
			if(!search.equals("")) {
				sql+=" AND p.nomeprodotto LIKE '%"+search+"%'";
			}
				
			sql+=" ORDER BY p.idcategoria,p.idsottocategoria,p.idprodotto ASC";
			
			stmt = connection.prepareStatement(sql);
			
			if(b1)
				stmt.setInt(i++, Integer.parseInt(marca));
			if(b2)
				stmt.setInt(i++, Integer.parseInt(sistema));
			
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
				item.setStato(rs.getInt("stato"));
				item.setIdCategoria(rs.getInt("idcategoria"));
				item.setIdSottoCategoria(rs.getInt("idSottoCategoria"));
				model.add(item);
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
		return model;
	}

	@Override
	public synchronized void doSave(Prodotto product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ProdottoControl.TABLE_NAME
				+ " (nomeprodotto, descrizione, descrizioneampia, "
				+"datainserimento, idcategoria, idsottocategoria)"
				+"VALUES (?, ?, ?, ?, ?, ?)";

		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, product.getNome());
			preparedStatement.setString(2, product.getDescrizione());
			preparedStatement.setString(3, product.getDescrizioneAmpia());
			preparedStatement.setString(4, product.getDatainserimento());
			preparedStatement.setInt(5, product.getIdCategoria());
			preparedStatement.setInt(6, product.getIdSottoCategoria());
			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();
			if(rs.next())
				lastID=rs.getInt(1);
			
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

		LocalDate date = LocalDate.now();
		
		Prodotto item = new Prodotto();

		String selectSQL = "SELECT * FROM prodotto AS p,locazione AS l, magazzino AS m, StoricoPrezzi AS sp "
				+"WHERE p.idProdotto=l.idProdotto AND l.idMagazzino=m.idMagazzino AND sp.idProdotto=p.idprodotto "
				+"AND sp.datainizio <= '"+date.toString()+"' AND (sp.datafine >= '"+date.toString()+"' OR sp.datafine is null) "
				+"AND p.IDProdotto = ? AND sp.datafine is null";
		
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
				item.setStato(rs.getInt("stato"));
				item.setIdCategoria(rs.getInt("idcategoria"));
				item.setIdSottoCategoria(rs.getInt("idSottoCategoria"));
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

		String deleteSQL = "UPDATE " + ProdottoControl.TABLE_NAME + " SET Stato=0 WHERE IDProdotto = ?";

		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);

			result = preparedStatement.executeUpdate();
			
			connection.commit();
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
		LocalDate date = LocalDate.now();
		
		
		Collection<Prodotto> products = new LinkedList<>();

		String selectSQL = "SELECT * FROM prodotto AS p,locazione AS l, magazzino AS m, StoricoPrezzi as sp "+
		" WHERE p.idProdotto=l.idProdotto AND l.idMagazzino=m.idMagazzino "+
		" AND sp.idprodotto=p.idprodotto AND sp.DataInizio <= '"+date.toString()+
		"' AND (sp.datafine >= '"+date.toString()+"' OR sp.datafine is null) AND sp.datafine is null ";

		
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
				item.setQuantita(rs.getInt("disponibilita"));
				item.setStato(rs.getInt("stato"));
				item.setIdCategoria(rs.getInt("idcategoria"));
				item.setIdSottoCategoria(rs.getInt("idSottoCategoria"));
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

			connection.commit();
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
	
	public static int getLastID() {
		return lastID;
	}
	
	public static synchronized Report getReport(int report,String date) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Report r= new Report();
		
		String selectSQL="SELECT SUM(C.Quantita*(PrezzoVendita-Costo)) AS Guadagno, SUM(Quantita) AS ProdottiVenduti"
				+", SUM(C.Quantita*PrezzoVendita) AS Ricavo, SUM(C.Quantita*Costo) AS CostoTOT "
				+ "FROM Composizione as C, Prodotto as P, Ordine as O, StoricoPrezzi as sp "
				+ "WHERE C.IDProdotto=P.IDProdotto AND C.IDOrdine=O.IDOrdine"
				+" AND sp.idProdotto=p.idprodotto AND sp.DataInizio <= O.datadiinserimento "
				+" AND (sp.datafine >= O.datadiinserimento OR sp.datafine is null) ";
		
		if(report==1)
			selectSQL += " AND O.datadiinserimento ='"+date+"'";
		else if(report==2) {
			LocalDate myLocalDate = LocalDate.of(LocalDate.now().getYear(),Integer.parseInt(date),1);
			selectSQL += " AND O.datadiinserimento BETWEEN '"+myLocalDate.getYear()+"-"+date+"-01' AND '"+myLocalDate.getYear()+"-"+date+"-"+myLocalDate.lengthOfMonth()+"'";
		}
		else if(report==3)
			selectSQL += " AND O.datadiinserimento BETWEEN '"+date+"-"+"01"+"-01' AND '"+date+"-"+"12"+"-"+"31"+"'";
		
		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				r.setProdottiVenduti(rs.getInt("ProdottiVenduti"));
				r.setCosto(rs.getFloat("CostoTOT"));
				r.setGuadagno(rs.getFloat("Guadagno"));
				r.setRicavo(rs.getFloat("Ricavo"));
			}
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DBConnectionPool.releaseConnection(connection);
			}
		}
		return r;
	}
	
	public static synchronized boolean updateProdotto(Prodotto p) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		String updateSQL="Update "+ProdottoControl.TABLE_NAME+" SET nomeprodotto = ?, descrizione = ?,descrizioneAmpia=?, idcategoria=?,idsottocategoria=? WHERE IdProdotto = ?";
		
		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1,p.getNome());
			preparedStatement.setString(2, p.getDescrizione());
			preparedStatement.setString(3, p.getDescrizioneAmpia());
			preparedStatement.setInt(4, p.getIdCategoria());
			preparedStatement.setInt(5, p.getIdSottoCategoria());
			preparedStatement.setInt(6, p.getId());
			
			result = preparedStatement.executeUpdate();
			
			connection.commit();
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
	
	public static synchronized Prodotto getProdottoOrdine(int code,String dataOrdine) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Prodotto item= new Prodotto();
		
		String selectSQL="SELECT *"
				+ "FROM Composizione as C, Prodotto as P, Ordine as O, StoricoPrezzi as sp "
				+ "WHERE C.IDProdotto=P.IDProdotto AND C.IDOrdine=O.IDOrdine"
				+" AND sp.idProdotto=p.idprodotto AND sp.DataInizio <= ? "
				+" AND (sp.datafine >= ? OR sp.datafine is null) AND p.idprodotto = ?";
		
		
		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			preparedStatement.setString(1, dataOrdine);
			preparedStatement.setString(2, dataOrdine);
			preparedStatement.setInt(3,code);	
			
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				item.setId(rs.getInt("idprodotto"));
				item.setNome(rs.getString("nomeProdotto"));
				item.setDescrizione(rs.getString("descrizione"));
				item.setDescrizioneAmpia(rs.getString("descrizioneAmpia"));
				item.setDatainserimento(rs.getString("dataInserimento"));
				item.setPrezzoVendita(rs.getFloat("prezzovendita"));
				item.setCosto(rs.getFloat("costo"));
				item.setStato(rs.getInt("stato"));
				item.setIdCategoria(rs.getInt("idcategoria"));
				item.setIdSottoCategoria(rs.getInt("idSottoCategoria"));
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
}
