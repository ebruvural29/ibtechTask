package com.dbbank.task;

import java.util.List;

import com.dbbank.task.dao.AccountDao;
import com.dbbank.task.dao.AddresDao;
import com.dbbank.task.dao.CustomerDao;
import com.dbbank.task.dao.PhoneDao;
import com.dbbank.task.model.Account;
import com.dbbank.task.model.Addres;
import com.dbbank.task.model.Customer;
import com.dbbank.task.model.Phone;

public class Main {
	public static void main(String[] args) {

		CustomerDao customerDao = new CustomerDao();
//		Customer customer = new Customer("test123", "test123");
//		customerDao.create(customer);
//		
		PhoneDao phoneDao = new PhoneDao();	
		List<Phone> phones = phoneDao.getPhones();
		for (Phone phone : phones) {
			System.out.println("phone id: " + phone.getId());
		}
//		Phone phone = new Phone(customer.getId(), "90", "45545444");
//		phoneDao.create(phone);
//		
		AddresDao addresDao = new AddresDao();	
//		Addres addres = new Addres(customer.getId(), "Türkiye", "Kocaeli", "41400");
//		addresDao.create(addres);
//		
		AccountDao accountDao = new AccountDao();	
//		Account account = new Account(customer.getId(), "Vadesiz", 5);
//		accountDao.create(account);
		
		customerDao.listCustomers();
//		phoneDao.list();	// listelemede sorun çýkarýyor
		addresDao.listAddresses();
		accountDao.listAccounts();
		
		// Update
//		Customer customer = new Customer("test123", "test123");
//		customerDao.create(customer);
//		customerDao.update(customer.getId(), "update123", "update123");
		
		//Delete
		Customer customer = new Customer("test123", "test123");
		customerDao.create(customer);
		
		Phone phone = new Phone(customer.getId(), "90", "45545444");
		phoneDao.create(phone);
		
		Addres addres = new Addres(customer.getId(), "Türkiye", "Kocaeli", "41400");
		addresDao.create(addres);
		
		Account account = new Account(customer.getId(), "Vadesiz", 5);
		accountDao.create(account);
		
//		phoneDao.delete(phone.getId());
//		addresDao.delete(addres.getId());
//		accountDao.delete(phone.getId());
//		customerDao.delete(customer.getId());

		customerDao.deleteNew(customer.getId());
		
		System.out.println(customerDao.getCustomers());
		
		for (Customer customere : customerDao.getCustomers()) {
			System.out.println(customere.getId());
		}

	}
}
