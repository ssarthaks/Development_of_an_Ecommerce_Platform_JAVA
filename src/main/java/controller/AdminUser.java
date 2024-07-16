package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.UserDao;

/**
 * Servlet implementation class AdminUser
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AdminUser" })
public class AdminUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private UserDao dao;
	 public void init(ServletConfig config) throws ServletException {
		    super.init(config);
		    dao = new UserDao();
		    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<User> listofUser = dao.getAllUser();
		request.setAttribute("listofUser", listofUser);
		request.getRequestDispatcher(ViewPages.ADMIN_USER).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
