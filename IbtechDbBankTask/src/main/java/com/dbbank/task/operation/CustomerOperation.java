package com.dbbank.task.operation;

import com.dbbank.task.dao.CustomerDao;
import com.dbbank.task.model.Customer;

public class CustomerOperation implements CrudOperations {

	private CustomerDao customerDao;

	public CustomerOperation() {
		this.customerDao = new CustomerDao();
	}

	@Override
	public void add() {
		Customer customer = new Customer("Ebru", "Vural");
		customerDao.create(customer);
	}

	@Override
	public void update() {
		Customer customer = new Customer("Name", "Surname");
		customerDao.create(customer);
		customerDao.update(customer.getId(), "Updated Name", "Updated Surname");
	}

	@Override
	public void list() {
		customerDao.listCustomers();
	}

	@Override
	public void delete() {
		Customer customer = new Customer("Delete", "Delete");
		customerDao.create(customer);
		customerDao.delete(customer.getId());
	}

}
