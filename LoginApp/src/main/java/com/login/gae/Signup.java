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
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public Signup() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		HttpSession session1=request.getSession();
		
		
		String uname=request.getParameter("email");
		System.out.println("Username: "+uname);
		String pass=request.getParameter("password");
		System.out.println("password: "+pass);
		String name=request.getParameter("name");
		System.out.println("FullName: "+name);
		String mobile=request.getParameter("mobile");
		System.out.println("MobileNumber: "+mobile);
		
		Entity person= new Entity("Person",uname);
		
		person.setProperty("Password", pass);
		person.setProperty("Name", name);
		person.setProperty("MobileNumber", mobile);
	
		
		
        Key key = KeyFactory.createKey("Person", uname);
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		

		
		try 
		
		{
			person = datastore.get(key);
		String 	personName = (String) person.getKey().getName();// To get the primary key from the datastore.

		} 
		catch (EntityNotFoundException e) {
			System.out.println("Exception Occurred");
		}
		
		
		
		
	
				datastore.put(person);
				
				pw.println("Account Created sucessfulyy");
				System.out.println("Login now");
		      
				
				
				String message = "Account Created sucessfuly, You can Login now";
				session1.setAttribute("message", message);
				
				String s=session1.getId();
				
				response.sendRedirect("login.jsp");	
		
	}
}
