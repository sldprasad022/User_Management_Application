package com.jdbc.registrationform;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Edit")
public class Edit extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// to print output on the Browser
		PrintWriter pw = resp.getWriter();
		//set content type
		resp.setContentType("text/html");
		//Based on Id data will be edit.
		int id = Integer.parseInt(req.getParameter("id"));
		// bootstrap
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		
        String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
        String query = "select name,email,mobile,dob,city,gender from usermanagement.user where id=?"; 
        
        try 
        {
        	Class.forName("com.mysql.jdbc.Driver");
        	//Establish the Connection between java application to database application
			Connection connection = DriverManager.getConnection(url);
			//Create a Platform
			PreparedStatement ps = connection.prepareStatement(query);
			//Set the values the place holder
			ps.setInt(1, id);
			//Execute the query
			ResultSet rs = ps.executeQuery();
			rs.next();
			pw.println("<div style='margin:auto;width:500px;margin-top:100px;'>"); 
			pw.println("<form action='EditRecord?id="+id+"' method='post'>");
			pw.println("<table class='table table-hover'>");
            pw.println("<tr>");
            pw.println("<td>Name</td>");
            pw.println("<td><input type='text' name='name' value='"+rs.getString(1)+"'></td>");
            pw.println("</tr>");
            pw.println("<tr>");
            pw.println("<td>Email</td>");
            pw.println("<td><input type='email' name='email' value='"+rs.getString(2)+"'></td>");
            pw.println("</tr>");
            pw.println("<tr>");
            pw.println("<td>Mobile</td>");
            pw.println("<td><input type='text' name='mobile' value='"+rs.getString(3)+"'></td>");
            pw.println("</tr>");
            pw.println("<tr>");
            pw.println("<td>DOB</td>");
            pw.println("<td><input type='date' name='dob' value='"+rs.getString(4)+"'></td>");
            pw.println("</tr>");
            pw.println("<tr>");
            pw.println("<td>City</td>");
            pw.println("<td><input type='text' name='city' value='"+rs.getString(5)+"'></td>");
            pw.println("</tr>");
            pw.println("<tr>");
            pw.println("<td>Gender</td>");
            pw.println("<td><input type='text' name='gender' value='"+rs.getString(6)+"'></td>");
            pw.println("</tr>");
            pw.println("<tr>");
            pw.println("<td><button type='submit' class='btn btn-outline-success'>Edit</button></td>");
            pw.println("<td><button type='reset' class='btn btn-outline-danger'>Cancel</button></td>");
            pw.println("</tr>");
            pw.println("</table>");
            pw.println("</form>");
            	
		} 
        catch (Exception e)
        {
        	pw.println("<h2 class='bg-danger  text-center'>"+e.getMessage()+"</h2>");
			e.printStackTrace();
		}
        pw.println("<a href='home.html'><button class='btn btn-outline-success'>Home</button></a>");
        pw.println("</div>");
        pw.close();
        
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		doGet(req, resp);
	}

}
