package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import model.Cliente;

public class ClienteControl implements IBeanDAO<Cliente> {

	private static final String TABLE_NAME = "cliente";
	
	@Override
	public synchronized void doSave(Cliente bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ClienteControl.TABLE_NAME
				+ " (Nome, Cognome, email, password) VALUES (?, ?, ?, ?)";

		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, bean.getNome());
			preparedStatement.setString(2, bean.getCognome());
			preparedStatement.setString(3, bean.getEmail());
			preparedStatement.setString(4, bean.getPassword());

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
	public synchronized Cliente doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Cliente item = new Cliente();

		String selectSQL = "SELECT * FROM " + ClienteControl.TABLE_NAME + " WHERE IDCliente = ?";
		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				item.setId(rs.getInt("idCliente"));
				item.setNome(rs.getString("nome"));
				item.setCognome(rs.getString("cognome"));
				item.setEmail(rs.getString("email"));
				item.setPassword(rs.getString("password"));
				item.setAdmin(rs.getInt("isAdmin"));
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

		String deleteSQL = "DELETE FROM " + ClienteControl.TABLE_NAME + " WHERE IDCliente = ?";

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
	public synchronized Collection<Cliente> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Cliente> c = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + ClienteControl.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Cliente item = new Cliente();

				item.setId(rs.getInt("idCliente"));
				item.setNome(rs.getString("nome"));
				item.setCognome(rs.getString("cognome"));
				item.setEmail(rs.getString("email"));
				item.setPassword(rs.getString("password"));
				item.setAdmin(rs.getInt("isAdmin"));
				c.add(item);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DBConnectionPool.releaseConnection(connection);
			}
		}
		return c;
	}

	public static synchronized int getidCliente(Collection<Cliente> clienti, Cliente beanC) {
		if(clienti.isEmpty())
			return 0;
		Iterator<Cliente> i=clienti.iterator();
		while(i.hasNext())
		{
			Cliente c=i.next();
			if(c.getEmail().compareTo(beanC.getEmail())== 0)
					return c.getId();
		}
		return 0;
	}
	
	public static synchronized boolean checkEmail(Collection<Cliente> clienti, String email)
	{
		if(clienti.isEmpty())
			return false;
		
		Iterator<Cliente> i=clienti.iterator();
		while(i.hasNext())
		{
			Cliente c=i.next();
			if(c.getEmail().compareToIgnoreCase(email)==0)
					return true;
		}
		
		return false;
	}
	
	public static synchronized boolean updateCliente(Cliente c) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		String updateSQL="Update "+ClienteControl.TABLE_NAME+" SET email = ?, password= ? WHERE IdCliente = ?";
		
		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1,c.getEmail());
			preparedStatement.setString(2,c.getPassword());
			preparedStatement.setInt(3, c.getId());
			
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
