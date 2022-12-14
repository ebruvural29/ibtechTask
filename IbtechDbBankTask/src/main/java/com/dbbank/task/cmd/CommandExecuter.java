package com.dbbank.task.cmd;

import com.dbbank.task.bag.Bag;
import com.dbbank.task.dao.CommandDao;
import com.dbbank.task.model.Command;
import java.lang.reflect.*;

public class CommandExecuter {
	public static Bag execute(String commandString, Bag bag) throws Exception {
		try {
			CommandDao commandDao = new CommandDao();
			Command command = commandDao.getCommand(commandString);
			if (!isValue(command)) {
				throw new Exception("CommandString not found!");
			}

			Class<?> c = Class.forName("com.dbbank.task.operation." + command.getClassName());
			Object obj = c.newInstance();
			Method method;
			Bag dbBag;
			if (!bag.getMap().isEmpty()) {
				method = c.getDeclaredMethod(command.getMethodName(), Bag.class);
				dbBag = (Bag) method.invoke(obj, bag);

			} else {
				method = c.getDeclaredMethod(command.getMethodName());
				dbBag = (Bag) method.invoke(obj);
			}
			System.out.println("-> Execute method runed");
			return dbBag;

		} catch (Exception e) {
			System.out.println("-> Execute method failed");
			e.printStackTrace();
			throw e;
		}
	}

	private static boolean isValue(Command command) {
		if (command == null) {
			System.out.println("*** NOT FOUND ***");
			return false;
		}
		System.out.println("-> Command Information; \n\t" + command.getCommandName() + "\n\t"
				+ command.getCommandDescription() + "\n\t" + command.getClassName() + "\n\t" + command.getMethodName());
		return true;
	}
}
