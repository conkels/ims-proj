package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {

private final OrderDAO orderDAO = new OrderDAO();
	
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		final Order created = new Order(1L, 1L, 1L);
		assertEquals(created, orderDAO.create(created));
	}
	
	@Test
	public void testReadAll() {
		List<Order> expected = new ArrayList<>();
		expected.add(new Order(1L, 1L, 1L, 1L, 20.99));
		assertEquals(expected, orderDAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(new Order(1L, 1L, 1L, 1L, 20.99), orderDAO.readLatest());
	}
	
	@Test
	public void testRead() {
		final Long orderId = 1L;
		assertEquals(new Order(orderId, 1L, 1L, 1L, 20.99), orderDAO.read(orderId));
	}
	
	@Test
	public void testUpdate() {
		final Order updated = new Order(1L, 1L, 1L, 2L);
		assertEquals(updated, orderDAO.update(updated));
	}
	
	@Test
	public void delete() {
		assertEquals(1, orderDAO.delete(1));
	}
	
	@Test
	public void deleteItem() {
		final Long itemId = 1L;
		assertEquals(new Order(1L, 1L), orderDAO.deleteItem(itemId));
	}
}
