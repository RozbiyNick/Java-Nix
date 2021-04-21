package debates;

import java.io.PrintWriter;
import classes.Organisation;
import db.DAO.OrganisationDAO;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/organisations")
public class OrganisationsServlet extends HttpServlet {
    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext context = getServletContext();
		OrganisationDAO dao = new OrganisationDAO();
		context.setAttribute("orgs", dao.getAll());
		context.getRequestDispatcher("/organisations.jsp").forward(req, resp);
    }
	
}
