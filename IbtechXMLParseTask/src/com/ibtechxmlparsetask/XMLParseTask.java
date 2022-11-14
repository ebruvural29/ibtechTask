package com.ibtechxmlparsetask;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.dbbank.task.bag.Bag;
import com.dbbank.task.bag.BagKey;
import com.dbbank.task.cmd.CommandExecuter;
import com.dbbank.task.model.Customer;
import com.ibtechxmlparsetask.xmlhelper.XMLParse;

/**
 * Servlet implementation class XMLParseTask
 */
@WebServlet("/XMLParseTask")
public class XMLParseTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public XMLParseTask() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		XMLParse xmlParse = new XMLParse();
		try {
//			Bag bag = xmlParse.XMLParseFileAndCommandRun("customer");
			Bag bag = xmlParse.XMLParseAndCommandRun(request.getReader().lines().collect(Collectors.joining()));
			PrintWriter writer = response.getWriter();
			writer.append("<EXT>");
			writer.append("<id>" + bag.getValue(BagKey.ID).toString() + "</id>");
			writer.append("<name>" + bag.getValue(BagKey.NAME).toString() + "</name>");
			writer.append("<surname>" + bag.getValue(BagKey.SURNAME).toString() + "</surname>");
			writer.append("</EXT>");
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//doGet(request, response);
	}

}
