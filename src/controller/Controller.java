package controller;

import java.io.IOException;
import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import service.Service;
import bean.User;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DataSource ds = null;
	Connection conn = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {

		try {
			InitialContext initialContext = new InitialContext();
			Context env = (Context) initialContext.lookup("java:comp/env");
			ds = (DataSource) env.lookup("jdbc/login");
		} catch (NamingException e) {

		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action == null) {
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);

		} else if (action.equals("login")) {
			request.setAttribute("username", "");

			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
		}

	}

	/**
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action == null) {
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);
		} else if (action.equals("dologin")) {

			String username = request.getParameter("username");
			String password = request.getParameter("password");

			User user = new User();
			user.setUsername(username);
			user.setPssword(password);

			request.setAttribute("username", username);

			Service service = new Service();

			if (service.validateLogin(user, conn, ds)) {
				//request.getRequestDispatcher("/billingconsole.jsp").forward(request, response);
				
				HttpSession session = request.getSession();
				session.setAttribute("user", username);
				session.setMaxInactiveInterval(30*60);
				
				Cookie userNameCookie = new Cookie("user", username);
				userNameCookie.setMaxAge(30*60);
				response.addCookie(userNameCookie);
				response.sendRedirect("billingconsole.jsp");

			} else {

				/*RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
				PrintWriter out = response.getWriter();
				out.write("<h3>Invalid username or password</h3>");
				rd.include(request, response);*/
				
				request.getRequestDispatcher("/login.jsp").forward(request,response);

			}

		}
	}

}
