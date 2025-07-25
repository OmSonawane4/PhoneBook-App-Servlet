package com.tka;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/submit_contact")
public class ContactServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String email = req.getParameter("email");
		
		
		System.out.println(name);
		System.out.println(phone);
		System.out.println(email);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tka", "root","root");
			PreparedStatement ps = c.prepareStatement("insert into phonebook(name, phoneNo, email) values(?,?,?)");
			ps.setString(1, name);
			ps.setString(2, phone);
			ps.setString(3, email);
			
			ps.executeUpdate();
			c.close();
			resp.sendRedirect("viewcontacts");
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
}
