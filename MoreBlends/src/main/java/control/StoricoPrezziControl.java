package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;

import model.StoricoPrezzi;

public class StoricoPrezziControl implements IBeanDAO<StoricoPrezzi> {

	private static final String TABLE_NAME = "StoricoPrezzi";
	
	@Override
	public synchronized void doSave(StoricoPrezzi bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		LocalDate date = LocalDate.now();
		
		String insertSQL = "INSERT INTO " + StoricoPrezziControl.TABLE_NAME
				+ " (prezzovendita,costo,datainizio,datafine,idprodotto) VALUES (?, ?, ?, ?, ?)";

		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setFloat(1, bean.getPv());
			preparedStatement.setFloat(2, bean.getCosto());
			preparedStatement.setString(3, date.toString());
			preparedStatement.setString(4, null);
			preparedStatement.setInt(5, bean.getIdProdotto());
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
	public synchronized StoricoPrezzi doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		StoricoPrezzi item = new StoricoPrezzi();

		String selectSQL = "SELECT * FROM " + StoricoPrezziControl.TABLE_NAME + " WHERE IDStoricoPrezzi = ?";
		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				item.setIdStoricoPrezzi(rs.getInt("IdStoricoPrezzi"));
				item.setCosto(rs.getFloat("costo"));
				item.setPv(rs.getFloat("prezzovendita"));
				item.setDataInizio(rs.getString("datainizio"));
				item.setDataFine(rs.getString("datafine"));
				item.setIdProdotto(rs.getInt("idProdotto"));
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

		String deleteSQL = "DELETE FROM " + StoricoPrezziControl.TABLE_NAME + " WHERE IDStoricoPrezzi = ?";

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
	public synchronized Collection<StoricoPrezzi> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<StoricoPrezzi> sp = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + StoricoPrezziControl.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				StoricoPrezzi item = new StoricoPrezzi();

				item.setIdStoricoPrezzi(rs.getInt("IdStoricoPrezzi"));
				item.setCosto(rs.getFloat("costo"));
				item.setPv(rs.getFloat("prezzovendita"));
				item.setDataInizio(rs.getString("datainizio"));
				item.setDataFine(rs.getString("datafine"));
				item.setIdProdotto(rs.getInt("idProdotto"));
				sp.add(item);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DBConnectionPool.releaseConnection(connection);
			}
		}
		return sp;
	}
	
	public static synchronized boolean updateSP(StoricoPrezzi sp) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		String updateSQL="Update "+StoricoPrezziControl.TABLE_NAME
				+" SET datafine = ? WHERE IdStoricoPrezzi= ?";
		
		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, sp.getDataFine());
			preparedStatement.setInt(2,sp.getIdStoricoPrezzi());

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
