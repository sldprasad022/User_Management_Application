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

@WebServlet("/EditRecord")
public class EditRecord extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		
		// to print output on the Browser==get Printwriter
		PrintWriter pw = resp.getWriter();
		//the response will be in the form of text or html content.
		resp.setContentType("text/html");
		//link the bootstrap
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		//Based on ID Record will be Edit
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");
        String dob = req.getParameter("dob");
        String city = req.getParameter("city");
        String gender = req.getParameter("gender");
        
        String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
        String query="update usermanagement.user set name=?,email=?,mobile=?,dob=?,city=?,gender=? where id=?";
        try 
        {
        	Class.forName("com.mysql.jdbc.Driver");
        	//Establish the Connection between java application to database application
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps = connection.prepareStatement(query);
			// set the value for the placeholder
			ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, mobile);
            ps.setString(4, dob);
            ps.setString(5, city);
            ps.setString(6, gender);
            ps.setInt(7, id);
            //execute the query
            int count = ps.executeUpdate();
            pw.println("<div class='card' style='margin:auto;width:300px;margin-top:100px'>");
            if(count==1)
            {
            	pw.println("<h2 class='bg-danger  text-center'>Record Edited Succesfully </h2>");
            }
            else
            {
            	pw.println("<h2 class='bg-danger  text-center'>Record is  not Edited</h2>");
            }
		} 
        catch (Exception e)
        {
        	pw.println("<h2 class='bg-danger  text-center'>"+e.getMessage()+"</h2>");
			e.printStackTrace();
		}
        pw.println("<a href='home.html'><button class='btn btn-outline-success'>Home</button></a>");
        pw.println("&nbsp; &nbsp");
        pw.println("<a href='ShowUsers'><button class='btn btn-outline-success'>Show User</button></a>");
        pw.println("</div>");
        pw.close();
     	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		doGet(req, resp);
	}

}
