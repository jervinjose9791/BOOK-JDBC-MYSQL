package com.ju;

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

@WebServlet("/register")
public class register extends HttpServlet {
	private static final String query = "INSERT INTO BOOK(BOOKNAME,BOOKEDITION,BOOKPRICE) VALUES(?,?,?)";
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		String bookName = request.getParameter("bookName");
		String bookEdition = request.getParameter("bookEdition");
		float bookPrice = Float.parseFloat(request.getParameter("bookPrice"));
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		String dburl="jdbc:mysql://localhost:3306/book";
		String user="root";
		String pass="root";
		try(Connection con = DriverManager.getConnection(dburl,user,pass);
				PreparedStatement ps = con.prepareStatement(query);){
			ps.setString(1, bookName);
			ps.setString(2, bookEdition);
			ps.setFloat(3, bookPrice);
			int count = ps.executeUpdate();
			if(count==1) {
				pw.println("<h2>Record Is Registered Sucessfully</h2>");
			}else {
				pw.println("<h2>Record not Registered Sucessfully");
			}
		}catch(SQLException se) {
			se.printStackTrace();
			pw.println("<h1>"+se.getMessage()+"</h2>");
		}catch(Exception e) {
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h2>");
		}
		

		
	}

}
