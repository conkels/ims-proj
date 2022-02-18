package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {

public static final Logger LOGGER = LogManager.getLogger();
	
	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long orderId = resultSet.getLong("order_id");
		Long itemId = resultSet.getLong("item_id");
		Long custId = resultSet.getLong("id");
		Long qty = resultSet.getLong("qty");
		Double cost = resultSet.getDouble("cost");
		return new Order(orderId, itemId, custId, qty, cost);
	}

	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT order_id, id, 	items.item_id, qty, (qty * items.price) AS cost FROM orders, items WHERE orders.item_id = items.item_id");) {
			List<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	@Override
	public Order read(Long orderId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE order_id = ?");) {
			statement.setLong(1, orderId);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order create(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders(id, item_id, qty, cost) VALUES (?, ?, ?, ?)");) {
			statement.setLong(1, order.getCustId());
			statement.setLong(2, order.getItemId());
			statement.setLong(3, order.getQty());
			statement.setDouble(4, 0.0);
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order update(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE orders SET id = ?, qty = ?, item_id = ? WHERE order_id = ?");) {
			statement.setLong(1, order.getCustId());
			statement.setLong(2, order.getQty());
			statement.setLong(3, order.getItemId());
			statement.setLong(4, order.getOrderId());
			statement.executeUpdate();
			return read(order.getOrderId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public int delete(long orderId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE order_id = ?");) {
			statement.setLong(1, orderId);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	
	public int deleteItem(long order_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from `orders_items` where order_id = " + order_id);
			return statement.executeUpdate("delete from `orders` where order_id = " + order_id);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	// creating an add item functionality to order
//	public Order addItem(Order order) {
//		try (Connection connection = DBUtils.getInstance().getConnection();
//				PreparedStatement statement = connection
//						.prepareStatement("INSERT INTO orders_items (item_id, order_id) VALUES (1, ?)");) {
//			statement.setLong(1, order.getOrderId());
//			statement.executeUpdate();
//			
//		} catch (Exception e) {
//			LOGGER.debug(e);
//			LOGGER.error(e.getMessage());
//		}
//		try (Connection connection = DBUtils.getInstance().getConnection();
//				PreparedStatement statement = connection
//						.prepareStatement("UPDATE orders_items\r\n"
//								+ "SET item_id = (SELECT item_id FROM items where item_id LIKE ?) WHERE order_id = ?"
//								+ "ORDER BY order_item_id DESC LIMIT 1;")) {
//			statement.setLong(1, order.getItemId());
//			statement.setLong(2, order.getOrderId());
//			statement.executeUpdate();
//			return read(order.getOrderId());
//		} catch (Exception e) {
//			LOGGER.debug(e);
//			LOGGER.error(e.getMessage());
//		}
//		return null;
//	}

}
