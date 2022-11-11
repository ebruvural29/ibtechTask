package com.dbbank.task.operation;

import com.dbbank.task.bag.Bag;

public interface CrudOperations {
	
	Bag add(Bag bag);
	
	Bag update(Bag bag);
	
	Bag list();
	
	Bag delete(Bag bag);

}
