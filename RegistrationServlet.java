package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegServlet
 */
@WebServlet("/reg")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    Connection con;
    public void init()
    {	
    	try {
			ServletContext sc=getServletContext();
			String s1=sc.getInitParameter("driver");
			String s2=sc.getInitParameter("url");
			String s3=sc.getInitParameter("username");
			String s4=sc.getInitParameter("password");
			Class.forName(s1);
			con=DriverManager.getConnection(s2, s3, s4);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String s1=request.getParameter("fname");
			String s2=request.getParameter("lname");
			String s3=request.getParameter("uname");
			String s4=request.getParameter("pword");
			PreparedStatement pstmt=con.prepareStatement("insert into uinfo values(?,?,?,?)");
			pstmt.setString(1, s1);
			pstmt.setString(2, s2);
			pstmt.setString(3, s3);
			pstmt.setString(4, s4);
			pstmt.executeUpdate();
			PrintWriter pw=response.getWriter();
			pw.println("<html><body bgcolor=cyan text=red><center>");
			pw.println("<h1>You Have Registered Succcessfully</h1>");
			pw.println("<a href=login.html>Login</a>");
			pw.println("</center></body></html>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}