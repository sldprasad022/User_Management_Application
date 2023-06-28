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

@WebServlet("/ShowUsers")
public class ShowUsers extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		//By using getwriter() we can print output in the Browser.
		PrintWriter pw = resp.getWriter();
		//set content type
		resp.setContentType("text/html");
		// bootstrap
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
	
        String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
        String query = "select id,name,email,mobile,dob,city,gender from usermanagement.user"; 
        
        try 
        {
        	Class.forName("com.mysql.jdbc.Driver");
        	//Step 1: Establish the Connection between java application to database application
			Connection connection = DriverManager.getConnection(url);
			//Step 2: Create a Platform
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			pw.println("<div style='margin:auto;width:900px;margin-top:100px;'>");
            pw.println("<table class='table table-hover'>");
            pw.println("<tr>");
            pw.println("<th>ID</th>");
            pw.println("<th>Name</th>");
            pw.println("<th>Email</th>");
            pw.println("<th>Mobile No</th>");
            pw.println("<th>DOB</th>");
            pw.println("<th>City</th>");
            pw.println("<th>Gender</th>");
            pw.println("<th>Edit</th>");
            pw.println("<th>Delete</th>");
            pw.println("</tr>");
            while(rs.next())
            {
                pw.println("<tr>");
                pw.println("<td>"+rs.getInt(1)+"</td>");
                pw.println("<td>"+rs.getString(2)+"</td>");
                pw.println("<td>"+rs.getString(3)+"</td>");
                pw.println("<td>"+rs.getString(4)+"</td>");
                pw.println("<td>"+rs.getString(5)+"</td>");
                pw.println("<td>"+rs.getString(6)+"</td>");
                pw.println("<td>"+rs.getString(7)+"</td>");
                //when the user click on edit option the request goes from Edit Class
                pw.println("<td><a href='Edit?id="+rs.getInt(1)+"'>Edit</a></td>");
              //when the user click on delete option the request goes from Delete Class
                pw.println("<td><a href='Delete?id="+rs.getInt(1)+"'>Delete</a></td>");
                pw.println("</tr>");
            }
            pw.println("</table>");
			
		} 
        catch (Exception e)
        {
        	pw.println("<h2 class='bg-danger text-center'>"+e.getMessage()+"</h2>");
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
