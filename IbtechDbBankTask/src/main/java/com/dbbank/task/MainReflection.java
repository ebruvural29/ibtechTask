package com.dbbank.task;

import java.util.List;

import com.dbbank.task.bag.Bag;
import com.dbbank.task.bag.BagKey;
import com.dbbank.task.cmd.CommandExecuter;
import com.dbbank.task.dao.CommandDao;
import com.dbbank.task.model.Command;
import com.dbbank.task.model.Customer;

public class MainReflection {

	public static void main(String[] args) {
		CommandDao commandDao = new CommandDao();

		// add
		Command cmd = commandDao.getCommand("customer_add");
		if (!isValue(cmd)) {
			return;
		}

		Bag bagAdd = new Bag();
		bagAdd.put(BagKey.NAME, "Elanur");
		bagAdd.put(BagKey.SURNAME, "Ocak");

		CommandExecuter cmdExecuter = new CommandExecuter();
		Bag customerBag = cmdExecuter.execute(cmd, bagAdd);

		// update
		Command cmdUpdate = commandDao.getCommand("customer_update");
		if (!isValue(cmdUpdate)) {
			return;
		}

		Bag bagUpdate = new Bag();
		long id = (long) customerBag.getValue(BagKey.ID);
		bagUpdate.put(BagKey.ID, id);
		bagUpdate.put(BagKey.NAME, "Updated");
		bagUpdate.put(BagKey.SURNAME, "Surname");

		cmdExecuter = new CommandExecuter();
		Bag testBag = cmdExecuter.execute(cmdUpdate, bagUpdate);

		// delete
		Command cmdDelete = commandDao.getCommand("customer_delete");
		if (!isValue(cmdDelete)) {
			return;
		}

		Bag bagDelete = new Bag();
		long customerId = (long) customerBag.getValue(BagKey.ID);
		bagDelete.put(BagKey.ID, customerId);

		cmdExecuter = new CommandExecuter();
		Bag deletedCustomerBag = cmdExecuter.execute(cmdDelete, bagDelete);

		// list
		Command cmdlist = commandDao.getCommand("customer_list");
		if (!isValue(cmdlist)) {
			return;
		}

		cmdExecuter = new CommandExecuter();
		Bag customersBag = cmdExecuter.execute(cmdlist, new Bag());
		List<Customer> customers = (List<Customer>) customersBag.getValue(BagKey.CUSTOMERLÝST);

		for (Customer customerItem : customers) {
			System.out.print("--> Id: " + customerItem.getId());
			System.out.print(" Name: " + customerItem.getName());
			System.out.println(" Surname: " + customerItem.getSurname());
		}

	}

	public static boolean isValue(Command command) {
		if (command == null) {
			System.out.println("*** NOT FOUND ***");
			return false;
		}
		System.out.println("-> Command Information; \n\t" + command.getCommandName() + "\n\t"
				+ command.getCommandDescription() + "\n\t" + command.getClassName() + "\n\t" + command.getMethodName());
		return true;
	}

}
