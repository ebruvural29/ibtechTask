package com.dbbank.task.operation;

import com.dbbank.task.bag.Bag;
import com.dbbank.task.dao.CustomerDao;
import com.dbbank.task.dao.PhoneDao;
import com.dbbank.task.model.Customer;
import com.dbbank.task.model.Phone;

public class PhoneOperation implements CrudOperations {

	private PhoneDao phoneDao;
	private CustomerDao customerDao;

	public PhoneOperation() {
		this.customerDao = new CustomerDao();
		this.phoneDao = new PhoneDao();
	}

	@Override
	public Bag add(Bag bag) {	
		Phone phone = new Phone(addCustomer(), "90", "45545444");
		phoneDao.create(phone);
		return null;
	}

	@Override
	public Bag update(Bag bag) {
		Phone phone = new Phone(addCustomer(), "90", "45545444");
		phoneDao.create(phone);
		phoneDao.update(phone.getId(), "44", "4444444444");
		return null;
	}

	@Override
	public Bag list() {
		phoneDao.list();
		return null;
	}

	@Override
	public Bag delete(Bag bag) {
		Phone phone = new Phone(addCustomer(), "88", "888888");
		phoneDao.create(phone);
		phoneDao.delete(phone.getId());
		return null;
	}
	
	long addCustomer() {
		Customer customer = new Customer("Ebru", "Vural");
		customerDao.create(customer);
		return customer.getId();
	}

}
