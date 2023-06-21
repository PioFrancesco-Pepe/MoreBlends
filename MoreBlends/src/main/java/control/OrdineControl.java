package control;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import model.Ordine;

public class OrdineControl implements IBeanDAO<Ordine> {

	private static final String TABLE_NAME = "ordine";
	private static int lastID;

	@Override
	public synchronized void doSave(Ordine o) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO " + OrdineControl.TABLE_NAME
				+ " (datadiinserimento, IDCliente, IDStatusOrdine) VALUES (?, ?, ?)";

		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, o.getDataInserimento());
			preparedStatement.setInt(2, o.getIdCliente());
			preparedStatement.setInt(3, o.getIdStatusOrdine());
			
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
	public synchronized Ordine doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Ordine item = new Ordine();

		String selectSQL = "SELECT * FROM " + OrdineControl.TABLE_NAME + " WHERE IDOrdine = ?";
		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				item.setIdOrdine(rs.getInt("idOrdine"));
				item.setDataInserimento(rs.getString("datadiinserimento"));
				item.setIdCliente(rs.getInt("idcliente"));
				item.setIdStatusOrdine(rs.getInt("idstatusordine"));
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

		String deleteSQL = "DELETE FROM " + OrdineControl.TABLE_NAME + " WHERE IDOrdine = ?";

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
	public synchronized Collection<Ordine> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Ordine> ordini = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + OrdineControl.TABLE_NAME+" AS O, StatusOrdine AS SO WHERE SO.IdStatusOrdine=O.IdStatusOrdine";

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Ordine item = new Ordine();

				item.setIdOrdine(rs.getInt("idOrdine"));
				item.setDataInserimento(rs.getString("datadiinserimento"));
				item.setIdCliente(rs.getInt("idcliente"));
				item.setIdStatusOrdine(rs.getInt("idstatusordine"));
				item.setStatusOrdine(rs.getString("statusordine"));
				ordini.add(item);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DBConnectionPool.releaseConnection(connection);
			}
		}
		return ordini;
	}

	public static int getLastID() {
		return lastID;
	}

	public Collection<Ordine> findOrder(String datex, String datey, int user) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Ordine> ordini = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + OrdineControl.TABLE_NAME+" AS O, StatusOrdine AS SO WHERE SO.IdStatusOrdine=O.IdStatusOrdine ";
		
		boolean flag=false;
		if(user!=0) {
			selectSQL+="AND o.idcliente=? AND datadiinserimento BETWEEN ? AND ? ORDER BY O.idOrdine DESC";
			flag=true;
		}
		else
			selectSQL+="AND datadiinserimento BETWEEN ? AND ? ORDER BY O.idOrdine DESC";
		
		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			if(flag) {
				preparedStatement.setInt(1, user);
				preparedStatement.setString(2, datex);
				preparedStatement.setString(3, datey);
			}
			else {
				preparedStatement.setString(1, datex);
				preparedStatement.setString(2, datey);
			}
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Ordine item = new Ordine();

				item.setIdOrdine(rs.getInt("idOrdine"));
				item.setDataInserimento(rs.getString("datadiinserimento"));
				item.setIdCliente(rs.getInt("idcliente"));
				item.setIdStatusOrdine(rs.getInt("idstatusordine"));
				item.setStatusOrdine(rs.getString("statusordine"));
				ordini.add(item);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DBConnectionPool.releaseConnection(connection);
			}
		}
		return ordini;
	}
}
