package com.multithreadTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import com.multithreadTask.dao.AccountDao;
import com.multithreadTask.dao.BatchDataDao;
import com.multithreadTask.model.Account;
import com.multithreadTask.model.BatchData;

public class Main {

	public static void main(String[] args) {

		AccountDao accountDao = new AccountDao();
		BatchDataDao batchDataDao = new BatchDataDao();

		for (int i = 0; i < 100; i++) {
			Account account = new Account("Vadesiz" + i, i * 1000);
			accountDao.create(account);

			for (int j = 0; j < 8; j++) {
				BatchData batchData = new BatchData(false, account.getId(), j * 111, j % 2 == 0 ? 'A' : 'B');
				batchDataDao.create(batchData);
			}
		}
		
		execute(20);
	}

	public static void execute(int threadcount) {

		BatchDataDao batchDataDao = new BatchDataDao();
		List<BatchData> batchDatas = batchDataDao.getList();
		
		System.out.println("-->> batchDatas Size: " + batchDatas.size());
		System.out.println("-->> batchDatas To Be Processed" + batchDatas.stream().filter(x -> !(x.isStatus())).collect(Collectors.toList()).size());

		int commitCount = batchDatas.size() / threadcount;
		DateFormat dateformat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z");
		ExecutorService executor = Executors.newFixedThreadPool(threadcount);
		for (int i = 0; i < threadcount; i++) {
			Runnable worker = new Operation(i * commitCount, (i + 1) * commitCount, batchDatas);
			executor.execute(worker);
			System.out.println(i + " thread baþladý. " + dateformat.format(new Date(System.currentTimeMillis())));
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
		}
		System.out.println("SON");
	}

}
