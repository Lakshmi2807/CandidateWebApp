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

@WebServlet("/deleteScreen")
public class DeleteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	final String query = "DELETE FROM PERSONAL_INFO where sno=?";
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
	 int count = ps.executeUpdate();
	 if(count == 1) {
		 pw.println("<h2>Record is Deleted successfully</h2>");
	 }else {
		 pw.println("<h2>OOPS! No record is deleted</h2>");
	 }

} catch (SQLException e) {
	e.printStackTrace();
	pw.println("<h1>"+e.getMessage()+"</h1>");
}
	catch (Exception e) {
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
