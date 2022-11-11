package com.dbbank.task.cmd;

import com.dbbank.task.bag.Bag;
import com.dbbank.task.model.Command;
import java.lang.reflect.*;

public class CommandExecuter {
	public static Bag execute(Command command, Bag bag) {
		try {
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
			return null;
		}
	}
}
