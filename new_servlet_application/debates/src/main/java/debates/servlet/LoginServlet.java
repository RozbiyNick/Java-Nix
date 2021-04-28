package debates.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import debates.auth.*;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		
		context.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        
        writer.println("login: " + login + "<br>");
        writer.println("password: " + password + "<br>");
		try {
			if(Authenticator.s.loginDataIsValid(login, password)) {
				writer.println("Acces allowed");
			}
			else {
				writer.println("Acces denied");
				context.setAttribute("form_wrong", true);
				context.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
        
		
	}

}
