package com.idol.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editurl")
public class EditServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String query = "UPDATE PERSONAL_INFO SET NAME=?,EMPLOYEE_ID=?,SKILLS=? where sno=?";
		System.out.println("Output");
		PrintWriter pw = resp.getWriter();
		resp.setContentType("text/html");
		int id= Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String emp = req.getParameter("employee_id");
		String skills = req.getParameter("skills");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try (Connection cn = DriverManager.getConnection("jdbc:mysql:///candidate_detail","root","mysqlLechu1!");
				PreparedStatement ps = cn.prepareStatement(query);){
		 ps.setString(1, name);
		 ps.setString(2, emp);
		 ps.setString(3, skills);
		 ps.setInt(4, id);
		 int count = ps.executeUpdate();
		 if(count == 1) {
			 pw.println("<h2>Record is updated successfully</h2>");
		 }else {
			 pw.println("<h2>OOPS! No record is edited</h2>");
		 }

	} catch (SQLException e) {
		e.printStackTrace();
		pw.println("<h1>"+e.getMessage()+"</h1>");
	}
		catch (Exception e) {
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h1>");
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}
