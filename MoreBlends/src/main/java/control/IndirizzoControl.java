package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import model.Indirizzo;

public class IndirizzoControl implements IBeanDAO<Indirizzo> {
	
	private static final String TABLE_NAME = "indirizzo";
	
	
	@Override
	public synchronized void doSave(Indirizzo bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + IndirizzoControl.TABLE_NAME
				+ " (via, civico, cap, localita, siglaprovincia,idcliente) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, bean.getVia());
			preparedStatement.setString(2, bean.getCivico());
			preparedStatement.setString(3, bean.getCAP());
			preparedStatement.setString(4, bean.getLocalita());
			preparedStatement.setString(5, bean.getSiglaProvincia());
			preparedStatement.setInt(6, bean.getIdCliente());

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
	public synchronized Indirizzo doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Indirizzo item = new Indirizzo();

		String selectSQL = "SELECT * FROM " + IndirizzoControl.TABLE_NAME + " WHERE IDIndirizzo = ?";
		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				item.setIdIndirizzo(rs.getInt("idindirizzo"));
				item.setVia(rs.getString("Via"));
				item.setCivico(rs.getString("civico"));
				item.setCAP(rs.getString("CAP"));
				item.setLocalita(rs.getString("localita"));
				item.setSiglaProvincia(rs.getString("SiglaProvinca"));
				item.setIdCliente(rs.getInt("idcliente"));
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

		String deleteSQL = "DELETE FROM " + IndirizzoControl.TABLE_NAME + " WHERE IDIndirizzo = ?";

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
	public synchronized Collection<Indirizzo> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Indirizzo> i = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + IndirizzoControl.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Indirizzo item = new Indirizzo();

				item.setIdIndirizzo(rs.getInt("idindirizzo"));
				item.setVia(rs.getString("Via"));
				item.setCivico(rs.getString("civico"));
				item.setCAP(rs.getString("CAP"));
				item.setLocalita(rs.getString("localita"));
				item.setSiglaProvincia(rs.getString("SiglaProvinca"));
				item.setIdCliente(rs.getInt("idcliente"));
				i.add(item);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DBConnectionPool.releaseConnection(connection);
			}
		}
		return i;
	}

	public static synchronized List<Indirizzo> getAllIndirizzi(int code) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		List<Indirizzo> t = new ArrayList<>();

		String selectSQL = "SELECT * FROM " + IndirizzoControl.TABLE_NAME +" WHERE idCliente = ?";
		

		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Indirizzo item = new Indirizzo();

				item.setIdIndirizzo(rs.getInt("idindirizzo"));
				item.setVia(rs.getString("Via"));
				item.setCivico(rs.getString("civico"));
				item.setCAP(rs.getString("CAP"));
				item.setLocalita(rs.getString("localita"));
				item.setSiglaProvincia(rs.getString(6));
				item.setIdCliente(rs.getInt("idcliente"));
				t.add(item);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DBConnectionPool.releaseConnection(connection);
			}
		}
		return t;
	}
	
}
