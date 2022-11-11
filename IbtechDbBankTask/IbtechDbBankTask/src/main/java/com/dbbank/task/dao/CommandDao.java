package com.dbbank.task.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dbbank.task.cmd.CommandExecuter;
import com.dbbank.task.model.Command;
import com.dbbank.task.util.HibernateUtil;

public class CommandDao {

	public Command getCommand(String commandName) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			try {
				transaction = session.beginTransaction();
				transaction.commit();
				List<Command> commands = session.createQuery("FROM Command").list();

				for (Command commandItem : commands) {
					if (commandItem.getCommandName().equals(commandName)) {
						return commandItem;
					}
				}

			} finally {
				session.close();
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return null;
	}
	
//	public Command runCommands() {
//		Transaction transaction = null;
//		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//			try {
//				transaction = session.beginTransaction();
//				transaction.commit();
//				
//				CommandExecuter cmdExecuter = new CommandExecuter();
//				
//				List<Command> commands = session.createQuery("FROM Command").list();
//				for (Command commandItem : commands) {
//					cmdExecuter.execute(commandItem);
//					System.out.println("---> " + commandItem.getCommandName());
//				}
//
//			} finally {
//				session.close();
//			}
//		} catch (Exception e) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
//			e.printStackTrace();
//		}
//		return null;
//	}

}
