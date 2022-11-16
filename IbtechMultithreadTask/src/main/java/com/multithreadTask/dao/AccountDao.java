package com.multithreadTask.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.multithreadTask.model.Account;
import com.multithreadTask.util.HibernateUtil;

public class AccountDao {

	public void create(Account account) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(account);
			transaction.commit();
			System.out.println("--> Account: " + account.getId());
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	public Account getAccount(long accountId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Account account = (Account) session.get(Account.class, accountId);
			return account;
		}
	}
	
	public void updateBalance(long accountId, double balance) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			try {
				transaction = session.beginTransaction();
				Account account = (Account) session.get(Account.class, accountId);
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
	
}
