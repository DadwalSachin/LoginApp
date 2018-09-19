package com.login.gae;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {

	public Login() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter pw = response.getWriter();
		String uname = request.getParameter("username");
		String pass = request.getParameter("password");

		Key key = KeyFactory.createKey("Person", uname);

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity person;

		String personPass = null, personName = null;
		try {
			person = datastore.get(key);

			personPass = (String) person.getProperty("Password");
			// String personName = (String) person.getProperty("Name");
			personName = (String) person.getKey().getName();// To get the primary key from the datastore.

			/*
			 * System.out.println("Passowrd : " + personPass); System.out.println("Name : "
			 * + personName);
			 */

		} catch (EntityNotFoundException e) {
			// Alice or Bob doesn't exist!
		}

		/*
		 * System.out.println("Passowrd : " + personPass); System.out.println("Name : "
		 * + personName);
		 * 
		 * System.out.println("Username:" + uname); System.out.println("password:" +
		 * pass);
		 */

		if (uname.equals(personName) && (pass.equals(personPass))) {
			System.out.println("Pass");
			HttpSession session = request.getSession();
			session.setAttribute("username", uname);
			pw.print("Welcome, login sucess");
			System.out.println("Login sucess");
			response.sendRedirect("welcome.jsp");
		}

		else {
			System.out.println("Fail");
			pw.println("Login failed");

			String message = "Login failed, Please try with correct Username and Password !!!!";
			request.getSession().setAttribute("message", message);

			String s = request.getSession().getId();

			response.sendRedirect("login.jsp");

		}

	}

}
