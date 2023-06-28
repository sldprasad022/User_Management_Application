package com.jdbc.registrationform;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Insert")
public class Insert extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// to print output on the Browser
		PrintWriter pw = resp.getWriter();
		//set content type
		resp.setContentType("text/html");
		// bootstrap 
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		
		//get the values from the Html form
		String name = req.getParameter("userName");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");
        String dob = req.getParameter("dob");
        String city = req.getParameter("city");
        String gender = req.getParameter("gender");
        
        String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
        String query = "insert into usermanagement.user(name,email,mobile,dob,city,gender)values(?,?,?,?,?,?)"; 
        
        try 
        {
        	Class.forName("com.mysql.jdbc.Driver");
        	
        	//Step 1: Establish the Connection between java application to database application
			Connection connection = DriverManager.getConnection(url);
			//Step 2: Create a Platform
			PreparedStatement ps = connection.prepareStatement(query);
			//set the values the placeholder
			ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, mobile);
            ps.setString(4, dob);
            ps.setString(5, city);
            ps.setString(6, gender);
            //Step 3: Execute the Query
            int count = ps.executeUpdate();
            if(count==1)
            {
            	pw.println("<h2 class='bg-success  text-center'>Registration Successful....</h2>");
            }
            else
            {
            	pw.println("<h2 class='bg-danger  text-center'>Registration is not Successful....</h2>");
            }
		} 
        catch (Exception e)
        {
        	pw.println("<h2 class='bg-danger  text-center'>"+e.getMessage()+"</h2>");
			e.printStackTrace();
		}
        pw.println("<center>");
        pw.println("<a href='home.html'><button class='btn btn-outline-success'>Home</button></a>");
        pw.println("</center>");
        pw.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		doGet(req, resp);
	}
}
