package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import model.Pagamento;

public class PagamentoControl implements IBeanDAO<Pagamento> {

	private static final String TABLE_NAME = "pagamento";
	
	
	@Override
	public synchronized void doSave(Pagamento bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + PagamentoControl.TABLE_NAME
				+ " (datapagamento, importopagamento, idmetodopagamento, idordine) VALUES (?, ?, ?,?)";

		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, bean.getDataPagamento());
			preparedStatement.setFloat(2,bean.getTotaleOrdine());
			preparedStatement.setInt(3, bean.getIdMetodoPagamento());
			preparedStatement.setInt(4, bean.getIdOrdine());

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
	public synchronized Pagamento doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Pagamento item = new Pagamento();

		String selectSQL = "SELECT * FROM " + PagamentoControl.TABLE_NAME + " WHERE IDPagamento = ?";
		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				item.setIdPagamento(rs.getInt("idPagamento"));
				item.setDataPagamento(rs.getString("dataPagamento"));
				item.setTotaleOrdine(rs.getFloat("importopagamento"));
				item.setIdMetodoPagamento(rs.getInt("idMetodoSpedizione"));
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

		String deleteSQL = "DELETE FROM " + PagamentoControl.TABLE_NAME + " WHERE IDPagamento = ?";

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
	public synchronized Collection<Pagamento> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Pagamento> p = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + PagamentoControl.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DBConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Pagamento item = new Pagamento();
				item.setIdPagamento(rs.getInt("idPagamento"));
				item.setDataPagamento(rs.getString("dataPagamento"));
				item.setTotaleOrdine(rs.getFloat("importopagamento"));
				item.setIdMetodoPagamento(rs.getInt("idMetodoSpedizione"));
				item.setIdOrdine(rs.getInt("idOrdine"));
				p.add(item);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DBConnectionPool.releaseConnection(connection);
			}
		}
		return p;
	}

}
