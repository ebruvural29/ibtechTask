package com.dbbank.task.cmd;

import com.dbbank.task.model.Command;
import java.lang.reflect.*;

public class CommandExecuter {
	public static void execute(Command command) {
		try {
			Class<?> c = Class.forName("com.dbbank.task.operation." + command.getClassName());
			Object obj = c.newInstance();
			Method method = c.getDeclaredMethod(command.getMethodName(), null);
			method.invoke(obj, null);
			System.out.println("execute run");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("execute faile");
			e.printStackTrace();
		}
	}
}
