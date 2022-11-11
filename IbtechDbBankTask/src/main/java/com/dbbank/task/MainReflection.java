package com.dbbank.task;

import java.util.List;

import com.dbbank.task.bag.Bag;
import com.dbbank.task.bag.BagKey;
import com.dbbank.task.cmd.CommandExecuter;
import com.dbbank.task.dao.CommandDao;
import com.dbbank.task.model.Command;
import com.dbbank.task.model.Customer;

public class MainReflection {

	public static void main(String[] args) throws Exception {

		// add
		Bag bagAdd = new Bag();
		bagAdd.put(BagKey.NAME, "Elanur");
		bagAdd.put(BagKey.SURNAME, "Ocak");

		Bag customerBag = CommandExecuter.execute("customer_add", bagAdd);

		
		// update
		Bag bagUpdate = new Bag();
		long id = (long) customerBag.getValue(BagKey.ID);
		bagUpdate.put(BagKey.ID, id);
		bagUpdate.put(BagKey.NAME, "Updated");
		bagUpdate.put(BagKey.SURNAME, "Surname");

		Bag updatedBag = CommandExecuter.execute("customer_update", bagUpdate);

		
		// delete
		Bag bagDelete = new Bag();
		long customerId = (long) customerBag.getValue(BagKey.ID);
		bagDelete.put(BagKey.ID, customerId);

		Bag deletedCustomerBag = CommandExecuter.execute("customer_delete", bagDelete);

		
		// list
		Bag customersBag = CommandExecuter.execute("customer_list", new Bag());
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
