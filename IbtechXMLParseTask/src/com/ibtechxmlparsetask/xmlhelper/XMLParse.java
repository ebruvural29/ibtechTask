package com.ibtechxmlparsetask.xmlhelper;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.dbbank.task.bag.Bag;
import com.dbbank.task.bag.BagKey;
import com.dbbank.task.cmd.CommandExecuter;
import com.dbbank.task.model.Customer;

public class XMLParse {

	public Bag XMLParseFileAndCommandRun(String xmlFileName) throws Exception {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File(xmlFileName + ".xml"));

		String commandName = document.getDocumentElement().getElementsByTagName("commandName").item(0).getTextContent();
		String customerName = document.getDocumentElement().getElementsByTagName("customerName").item(0)
				.getTextContent();
		String customerSurname = document.getDocumentElement().getElementsByTagName("customerSurname").item(0)
				.getTextContent();

		Customer customer = new Customer(customerName, customerSurname);
		System.out.println("-----> " + commandName);
		System.out.println("-----> " + customer.getName());
		System.out.println("-----> " + customer.getSurname());
		
		Bag bag = new Bag();
		bag.put(BagKey.NAME, customer.getName());
		bag.put(BagKey.SURNAME, customer.getSurname());
		CommandExecuter asd = new CommandExecuter();
		Bag resultBag = asd.execute(commandName, bag);
		
		return resultBag;	
	}
	
	public Bag XMLParseAndCommandRun(String body) throws Exception {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new InputSource(new StringReader(body)));
		document.getDocumentElement().normalize();

		String commandName = document.getDocumentElement().getElementsByTagName("commandName").item(0).getTextContent();
		String customerName = document.getDocumentElement().getElementsByTagName("customerName").item(0)
				.getTextContent();
		String customerSurname = document.getDocumentElement().getElementsByTagName("customerSurname").item(0)
				.getTextContent();

		Customer customer = new Customer(customerName, customerSurname);
		System.out.println("-----> " + commandName);
		System.out.println("-----> " + customer.getName());
		System.out.println("-----> " + customer.getSurname());
		
		Bag bag = new Bag();
		bag.put(BagKey.NAME, customer.getName());
		bag.put(BagKey.SURNAME, customer.getSurname());
		CommandExecuter asd = new CommandExecuter();
		Bag resultBag = asd.execute(commandName, bag);
		
		return resultBag;	
	}
}
