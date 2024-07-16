package controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.UserDao;

/**
 * Servlet implementation class UserProfile
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Profile" })
public class UserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDao userDao;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDao = new UserDao();
    }
    public UserProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userEmail = (String) request.getSession().getAttribute("email"); // Assuming user email is stored in session
        User user = userDao.getUserByEmail(userEmail);
        if (userEmail == null) {
            // Redirect to the login page if the userEmail is null
            request.getRequestDispatcher(ViewPages.LOGIN_PAGE).forward(request, response);
            return; // Stop further execution
        }

        request.setAttribute("user", user);

        request.getRequestDispatcher(ViewPages.PROFILE).forward(request, response);
    }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
