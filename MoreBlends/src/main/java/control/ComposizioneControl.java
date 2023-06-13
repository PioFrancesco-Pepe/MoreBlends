package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import model.Composizione;

public class ComposizioneControl implements IBeanDAO<Composizione> {

	private static final String TABLE_NAME = "Composizione";

	@Override
	public synchronized void doSave(Composizione comp) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ComposizioneControl.TABLE_NAME
				+ " (idOrdine, IDProdotto, quantita) VALUES (?, ?, ?)";

		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, comp.getIdOrdine());
			preparedStatement.setInt(2, comp.getIdProdotto());
			preparedStatement.setInt(3, comp.getQuantita());

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
	public synchronized Composizione doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Composizione item = new Composizione();

		String selectSQL = "SELECT * FROM " + ComposizioneControl.TABLE_NAME + " WHERE IDOrdine = ?";
		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				item.setIdOrdine(rs.getInt("idOrdine"));
				item.setIdProdotto(rs.getInt("idProdotto"));
				item.setQuantita(rs.getInt("quantita"));
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

		String deleteSQL = "DELETE FROM " + ComposizioneControl.TABLE_NAME + " WHERE IDOrdine = ?";

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
	public synchronized Collection<Composizione> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Composizione> composizioni = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + ComposizioneControl.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Composizione item = new Composizione();

				item.setIdOrdine(rs.getInt("idOrdine"));
				item.setIdProdotto(rs.getInt("idProdotto"));
				item.setQuantita(rs.getInt("quantita"));
				composizioni.add(item);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DBConnectionPool.releaseConnection(connection);
			}
		}
		return composizioni;
	}
}
