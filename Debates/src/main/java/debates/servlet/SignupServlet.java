package debates.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import debates.auth.Authenticator;

/**
 * Servlet implementation class SignupServlet
 */
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/jsp/signup.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		
		response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        
        writer.println("login: " + login + "<br>");
        writer.println("password: " + password + "<br>");
        writer.println("password2: " + password2 + "<br>");
		try {
			if(Authenticator.s.signupDataErrors(login, password, password2).size()==0) {
				try {
					Authenticator.s.createUser(login, password);
					writer.println("Registration complete");
				}
				catch (Exception e) {
					e.printStackTrace();
				} 
			}
			else {
				request.setAttribute("errors", Authenticator.s.signupDataErrors(login, password, password2));
				request.getRequestDispatcher("/jsp/signup.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
