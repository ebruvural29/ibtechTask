package com.dbbank.task;

import com.dbbank.task.cmd.CommandExecuter;
import com.dbbank.task.dao.CommandDao;
import com.dbbank.task.model.Command;

public class MainReflection {

	public static void main(String[] args) {
		CommandDao commandDao = new CommandDao();
		
		commandDao.runCommands();
		
		//Command cmd = commandDao.getCommand("customer_add");
		//Command cmd = commandDao.getCommand("customer_update");
		//Command cmd = commandDao.getCommand("customer_list");
		//Command cmd = commandDao.getCommand("customer_delete");
		
		//Command cmd = commandDao.getCommand("phone_add");
		//Command cmd = commandDao.getCommand("phone_update");
		//Command cmd = commandDao.getCommand("phone_list");
		//Command cmd = commandDao.getCommand("customer_delete");

//		if (cmd == null) {
//			System.out.println("*** NOT FOUND ***");
//			return;
//		}
//
//		System.out.println("Resutl: " + cmd.getCommandName() + " --- " + cmd.getCommandDescription() + " --- "
//				+ cmd.getClassName() + " --- " + cmd.getMethodName());
//		
//		CommandExecuter cmdExecuter = new CommandExecuter();
//		cmdExecuter.execute(cmd);
	}

}
