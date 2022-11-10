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
			if (!bag.getMap().isEmpty()) {
				method = c.getDeclaredMethod(command.getMethodName(), Bag.class);
				Bag dbBag = (Bag) method.invoke(obj, bag);
				System.out.println("-> Execute method runed");
				return dbBag;
			} else {
				method = c.getDeclaredMethod(command.getMethodName(), null);
				Bag dbBag = (Bag) method.invoke(obj, null);
				System.out.println("-> Execute method runed");
				return dbBag;
			}
			

		} catch (Exception e) {
			System.out.println("-> Execute method failed");
			e.printStackTrace();
			return null;
		}
	}
}
