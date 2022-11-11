package com.dbbank.task.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dbbank.task.model.Account;
import com.dbbank.task.util.HibernateUtil;

public class AccountDao {
	public void create(Account account) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.save(account);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public List<Account> getAccounts() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Account", Account.class).list();
		}
	}
	
	public void listAccounts() {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			try {
				transaction = session.beginTransaction();
				List customers = session.createQuery("FROM Account").list();
				for (Iterator iterator = customers.iterator(); iterator.hasNext();) {
					Account account = (Account) iterator.next();
					System.out.print("Id: " + account.getId());
					System.out.print(" Customer Id: " + account.getCustomerId());
					System.out.print(" Account Name: " + account.getAccountName());
					System.out.println(" Balance: " + account.getBalance());
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
	   
	public void update(long accountId, String accountName, long balance) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			try {
				transaction = session.beginTransaction();
				Account account = (Account) session.get(Account.class, accountId);
				account.setAccountName(accountName);
				account.setBalance(balance);
				session.update(account);
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
	   
	public void delete(long accountId) {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			try {
				transaction = session.beginTransaction();
				Account account = (Account) session.get(Account.class, accountId);
				session.delete(account);
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

