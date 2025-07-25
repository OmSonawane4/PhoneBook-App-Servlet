package com.tka;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/viewcontacts")
public class ContactListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Contact> contactList = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tka", "root", "root");

            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT name, phoneNo FROM studentinfo");

            while (rs.next()) {
            	
            	 String name = rs.getString("name");
            	 String phone = rs.getString("phoneNo");
            	 System.out.println("Found contact: " + name + ", " + phone);
            	
                Contact con = new Contact();
                con.setName(rs.getString("name"));
                con.setPhone(rs.getString("phoneNo"));
                contactList.add(con);
            }

            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("contacts", contactList);
        RequestDispatcher rd = req.getRequestDispatcher("contactlist.jsp");
        rd.forward(req, resp);
    }
}
