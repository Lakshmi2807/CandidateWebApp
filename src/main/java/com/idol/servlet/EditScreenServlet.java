package com.idol.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
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

@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String query = "SELECT SNO,NAME,EMPLOYEE_ID,SKILLS FROM PERSONAL_INFO where sno=?";
		PrintWriter pw = resp.getWriter();
		resp.setContentType("text/html");
		int id= Integer.parseInt(req.getParameter("id"));
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try (Connection cn = DriverManager.getConnection("jdbc:mysql:///candidate_detail","root","mysqlLechu1!");
				PreparedStatement ps = cn.prepareStatement(query);){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			System.out.println("EditScreenServlet");
			pw.println("<form action='editurl?id="+id+"' method='post'>");
			pw.println("<table align='center'>");
			pw.println("<tr> <td> Candidate Name </td> <td><input type='text' name='name' value='"+rs.getString(2)+"'></td>");
			pw.println("<tr> <td> Employee id </td> <td><input type='text' name='employee_id' value='"+rs.getString(3)+"'></td>");
			pw.println("<tr> <td> Skills </td> <td><input type='text' name='skills' value='"+rs.getString(4)+"'></td>");
			pw.println("<tr><td><input type='submit' value='Edit'></td>");
			pw.println("<td><input type='reset' value='cancel'></td></tr>");
			pw.println("</tr></table></form>");
			pw.println("<a href='Home.html'><center style='padding:2px;'>Home</center></a>");

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}