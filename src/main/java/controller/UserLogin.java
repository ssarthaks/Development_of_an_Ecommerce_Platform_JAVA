package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import service.UserDao;


/**
 * Servlet implementation class StudentLogin
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final UserDao userDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        this.userDao = new UserDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher(ViewPages.LOGIN_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String clicked=request.getParameter("submit");
		if(clicked!=null)
		{
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		int loginResult = userDao.getUserLoginInfo(email, password);
		if (loginResult == 1) {
		    HttpSession session = request.getSession(); 
		    if (session != null) {
		        session.setAttribute("email", email); // Storing email in session
		        User user = userDao.getUserByEmail(email);
		        if (user != null) {
		            String username = user.getUsername();
		            session.setAttribute("username", username);
		            
		            if ("admin".equals(username)) {
		                // If the username is "admin", forward to the admin dashboard
		                response.sendRedirect(request.getContextPath() + "/AdminDashboard");
		                return; // Exit the method after forwarding
		            }
		        }
		        Cookie userCookie = new Cookie("email", email); 
		        userCookie.setMaxAge(30*60); // Setting the cookie's maximum age to 30 minutes
		        response.sendRedirect(request.getContextPath()+ "/ViewIndex"); // Forwarding to INDEX page
		    }
		}

		else if (loginResult == -2) 
		{
			request.setAttribute("error", "Email not found");
            request.getRequestDispatcher(ViewPages.LOGIN_PAGE).forward(request, response);
		}
		else if(loginResult == 0) 
		{
			request.setAttribute("error", "Inavlid email or password");
            request.getRequestDispatcher(ViewPages.LOGIN_PAGE).forward(request, response);
            request.removeAttribute("error");
		}
		}
		else
		{
			doGet(request, response);
		}
		
		
}
}
