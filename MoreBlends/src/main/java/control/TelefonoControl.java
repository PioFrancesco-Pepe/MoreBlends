package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import model.Telefono;

public class TelefonoControl implements IBeanDAO<Telefono> {

	
	private static final String TABLE_NAME = "telefono";

	@Override
	public synchronized void doSave(Telefono t) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + TelefonoControl.TABLE_NAME
				+ " (NumeroTelefono, IDCliente) VALUES (?, ?)";

		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, t.getNumTelefono());
			preparedStatement.setInt(2, t.getIdCliente());

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
	public synchronized Telefono doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Telefono item = new Telefono();

		String selectSQL = "SELECT * FROM " + TelefonoControl.TABLE_NAME + " WHERE IDTelefono = ?";
		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				item.setIdTelefono(rs.getInt("idtelefono"));
				item.setNumTelefono(rs.getString("numerotelefono"));
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

		String deleteSQL = "DELETE FROM " + TelefonoControl.TABLE_NAME + " WHERE IDTelefono = ?";

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
	public synchronized Collection<Telefono> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Telefono> t = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + TelefonoControl.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Telefono item = new Telefono();

				item.setIdTelefono(rs.getInt("idtelefono"));
				item.setNumTelefono(rs.getString("numerotelefono"));
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
