package com.jdbc.registrationform;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Delete")
public class Delete extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// By using getwriter() we can print output in the Browser.
		PrintWriter pw = resp.getWriter();
		//set content type
		resp.setContentType("text/html");
		//bootstrap
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		//Based on ID Record will be Deleted
		int id = Integer.parseInt(req.getParameter("id"));
        
        String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
        String query = "delete from usermanagement.user where id=?"; 
        
        try 
        {
        	Class.forName("com.mysql.jdbc.Driver");
        	//Establish the Connection between java application to database application
			Connection connection = DriverManager.getConnection(url);
			//Create a Platform
			PreparedStatement ps = connection.prepareStatement(query);
			// set the id value for the placeholder
			ps.setInt(1, id);
			// Execute the query
            int count = ps.executeUpdate();
            if(count==1)
            {
            	pw.println("<h2 class='bg-danger  text-center'>Record Deleted </h2>");
            }
            else
            {
            	pw.println("<h2 class='bg-danger  text-center'>Record not Deleted</h2>");
            }
		} 
        catch (Exception e)
        {
        	pw.println("<h2 class='bg-danger  text-center'>"+e.getMessage()+"</h2>");
			e.printStackTrace();
		}
        pw.println("<center>");
        pw.println("<a href='home.html'><button class='btn btn-outline-success'>Home</button></a>");
        pw.println("&nbsp; &nbsp");
        pw.println("<a href='ShowUsers'><button class='btn btn-outline-success'>Show User</button></a>");
        pw.println("</center>");
        pw.close();
      	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		doGet(req, resp);
	}

}
