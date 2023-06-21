package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import model.Locazione;

public class LocazioneControl implements IBeanDAO<Locazione> {

private static final String TABLE_NAME = "locazione";
	
	@Override
	public synchronized void doSave(Locazione bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + LocazioneControl.TABLE_NAME
				+ " (idprodotto, idmagazzino, disponibilita) VALUES (?, ?, ?)";

		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, bean.getIdProdotto());
			preparedStatement.setInt(2, bean.getIdMagazzino());
			preparedStatement.setInt(3, bean.getQuantita());
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
	public synchronized Locazione doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Locazione item = new Locazione();

		String selectSQL = "SELECT * FROM " + LocazioneControl.TABLE_NAME + " WHERE IDProdotto = ?";
		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				item.setIdProdotto(rs.getInt("idProdotto"));
				item.setIdMagazzino(rs.getInt("idProdotto"));
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

		String deleteSQL = "DELETE FROM " + LocazioneControl.TABLE_NAME + " WHERE IDProdotto = ?";

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
	public synchronized Collection<Locazione> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Locazione> l = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + LocazioneControl.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Locazione item = new Locazione();

				item.setIdProdotto(rs.getInt("idProdotto"));
				item.setIdMagazzino(rs.getInt("idProdotto"));
				item.setQuantita(rs.getInt("disponibilita"));
				l.add(item);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DBConnectionPool.releaseConnection(connection);
			}
		}
		return l;
	}
	
	public static synchronized boolean updateLocazione(Locazione l) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		String updateSQL="Update locazione SET Disponibilita = ? WHERE IdProdotto = ? AND IdMagazzino = ?";
		
		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setInt(1, l.getQuantita());
			preparedStatement.setInt(2,l.getIdProdotto());
			preparedStatement.setInt(3, l.getIdMagazzino());

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
	
	
}
