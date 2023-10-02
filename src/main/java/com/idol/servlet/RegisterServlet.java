package com.idol.servlet;

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
public class RegisterServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String query = "INSERT INTO PERSONAL_INFO(NAME,EMPLOYEE_ID,SKILLS,ASPIRATIONS,ISHOLDINGVISA,ISALLOCATED) VALUES(?,?,?,?,?,?)";
		PrintWriter result = resp.getWriter();
		resp.setContentType("text/html");
		
		String name = req.getParameter("name");
		String employee_id = req.getParameter("employee_id");
		String skills = req.getParameter("skills");
		String aspiration = req.getParameter("aspiration");
		String isholdingvisa = req.getParameter("checkboxVisa");
		String isallocated = req.getParameter("checkboxAllocate");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try (Connection cn = DriverManager.getConnection("jdbc:mysql:///candidate_detail","root","mysqlLechu1!");
			PreparedStatement ps = cn.prepareStatement(query);){
			ps.setString(1, name);
			ps.setString(2, employee_id);
			ps.setString(3, skills);
			ps.setString(4, aspiration);
			ps.setString(5, isholdingvisa);
			ps.setString(6, isallocated);

			int count = ps.executeUpdate();
			if(count == 1) 
				result.println("<h1 style='justify-content:center;display:flex'>Row has inserted successfully! </h1>");
			else
				result.println("<h1>Row not inserted successfully!</h1>");

		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
			result.println("<h1>"+se.getMessage()+"</h1>");
		}
		 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.println("<h1>"+e.getMessage()+"</h1>");
			}
		result.println("<a style='justify-content:center;display:flex' href='Home.html'><h2>Home</h2></a>");
		result.println("</br>");
		result.println("<a style='justify-content:center;display:flex' href='candidate_list'><h3>Candidate Lists</h3></a>");

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}
