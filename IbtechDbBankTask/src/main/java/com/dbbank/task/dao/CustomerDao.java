package com.dbbank.task.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dbbank.task.model.Account;
import com.dbbank.task.model.Addres;
import com.dbbank.task.model.Customer;
import com.dbbank.task.model.Phone;
import com.dbbank.task.util.HibernateUtil;

public class CustomerDao {
	public void create(Customer customer) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.save(customer);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public List<Customer> getCustomers() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Customer", Customer.class).list();
		}
	}
	
	public void listCustomers() {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			try {
				transaction = session.beginTransaction();
				List customers = session.createQuery("FROM Customer").list();
				for (Iterator iterator = customers.iterator(); iterator.hasNext();) {
					Customer customer = (Customer) iterator.next();
					System.out.print("Id: " + customer.getId());
					System.out.print(" Name: " + customer.getName());
					System.out.println(" Surname: " + customer.getSurname());
				}
				transaction.commit();
			} catch (HibernateException e) {
				if (transaction != null)
					transaction.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	   
	public void update(long customerId, String name, String surname) {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			try {
				transaction = session.beginTransaction();
				Customer customer = (Customer) session.get(Customer.class, customerId);
				customer.setName(name);
				customer.setSurname(surname);
				session.update(customer);
				transaction.commit();
			} catch (HibernateException e) {
				if (transaction != null)
					transaction.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
	}
	   
	public void delete(long customerId) {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			try {
				transaction = session.beginTransaction();
				Customer customer = (Customer) session.get(Customer.class, customerId);
				session.delete(customer);
				transaction.commit();
			} catch (HibernateException e) {
				if (transaction != null)
					transaction.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	public void deleteNew(long customerId) {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			try {
				transaction = session.beginTransaction();
				
//				AddresDao addresDao = new AddresDao();
//				List<Addres> addresses = addresDao.getAddresses();
//				for (Addres addres : addresses) {
//					if (addres.getCustomerId() == customerId)
//					{
//						addresDao.delete(addres.getId());
//						//break;
//					}
//				}
//				
////				PhoneDao phoneDao = new PhoneDao();
////				List<Phone> phones = phoneDao.getPhones();
////				for (Phone phone : phones) {
////					if (phone.getCustomerId() == customerId)
////					{
////						phoneDao.delete(phone.getId());
////						//break;
////					}
////				}
//				
//				AccountDao accountDao = new AccountDao();
//				List<Account> accounts = accountDao.getAccounts();
//				for (Account account : accounts) {
//					if (account.getCustomerId() == customerId)
//					{
//						accountDao.delete(account.getId());
//						//break;
//					}
//				}
				
				Customer customer = (Customer) session.get(Customer.class, customerId);
				session.delete(customer);
				transaction.commit();
			} catch (HibernateException e) {
				if (transaction != null)
					transaction.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
}

