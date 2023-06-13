package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import model.Spedizione;

public class SpedizioneControl implements IBeanDAO<Spedizione> {
	
private static final String TABLE_NAME = "spedizione";
	
	
	@Override
	public synchronized void doSave(Spedizione bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + SpedizioneControl.TABLE_NAME
				+ " (dataspedizione, idmetodospedizione, idordine) VALUES (?, ?, ?)";

		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, bean.getDataSpedizione());
			preparedStatement.setInt(2, bean.getIdMetodoSpedizione());
			preparedStatement.setInt(3, bean.getIdOrdine());

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
	public synchronized Spedizione doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Spedizione item = new Spedizione();

		String selectSQL = "SELECT * FROM " + SpedizioneControl.TABLE_NAME + " WHERE IDSpedizione = ?";
		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				item.setIdSpedizione(rs.getInt("idSpedizione"));
				item.setDataSpedizione(rs.getString("dataSpedizione"));
				item.setIdMetodoSpedizione(rs.getInt("idMetodoSpedizione"));
				item.setIdOrdine(rs.getInt("idOrdine"));
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

		String deleteSQL = "DELETE FROM " + SpedizioneControl.TABLE_NAME + " WHERE IDSpedizione = ?";

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
	public synchronized Collection<Spedizione> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Spedizione> s = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + SpedizioneControl.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Spedizione item = new Spedizione();
				item.setIdSpedizione(rs.getInt("idSpedizione"));
				item.setDataSpedizione(rs.getString("dataSpedizione"));
				item.setIdMetodoSpedizione(rs.getInt("idMetodoSpedizione"));
				item.setIdOrdine(rs.getInt("idOrdine"));
				s.add(item);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DBConnectionPool.releaseConnection(connection);
			}
		}
		return s;
	}

}
