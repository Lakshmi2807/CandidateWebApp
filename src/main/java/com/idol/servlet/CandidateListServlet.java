package com.idol.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
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

@WebServlet("/candidate_list")
public class CandidateListServlet extends HttpServlet {

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			final String query = "SELECT SNO,NAME,EMPLOYEE_ID,SKILLS FROM PERSONAL_INFO";
			PrintWriter pw = resp.getWriter();
			resp.setContentType("text/html");
			
		try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			try (Connection cn = DriverManager.getConnection("jdbc:mysql:///candidate_detail","root","mysqlLechu1!");
				PreparedStatement ps = cn.prepareStatement(query);){
				ResultSet rs = ps.executeQuery();
				pw.println("<table border='1' align='center'>");
				pw.println("<tr>");
				pw.println("<th>Candidate Name</th>");
				pw.println("<th>Employee id</th>");
				pw.println("<th>Skills</th>");
				pw.println("<tr>");

				
				while(rs.next()) {
					pw.println("<tr>");
					pw.println("<td>"+rs.getString(2)+"</td>");
					pw.println("<td>"+rs.getString(3)+"</td>");
					pw.println("<td>"+rs.getString(4)+"</td>");
					pw.println("<td><a href='editScreen?id="+rs.getInt(1)+"'>Edit</a></td>");
					pw.println("<td><a href='deleteScreen?id="+rs.getInt(1)+"'>Delete</a></td>");
					pw.println("</tr>");
				}
				
				pw.println("</table>");
				} catch (SQLException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
				pw.println("<h1>"+se.getMessage()+"</h1>");
			}
			 catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					pw.println("<h1>"+e.getMessage()+"</h1>");
				}
			pw.println("<a href='Home.html'><center style='padding:2px;'>Home</center></a>");
		}
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req,resp);
		}

}
