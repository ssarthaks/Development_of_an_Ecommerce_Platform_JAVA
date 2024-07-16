package controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserDao;

/**
 * Servlet implementation class DeleteUser
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/DeleteUser" })
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDao dao;
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	dao = new UserDao();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String userEmailToDelete = request.getParameter("email");
		 boolean deleted = dao.deleteUser(userEmailToDelete);
		 if (deleted) {
		        response.sendRedirect(request.getContextPath() + "/AdminUser");
		 } else {
		        // If deletion failed, forward to an error page
		        request.setAttribute("errorMessage", "Failed to delete user.");
		        request.getRequestDispatcher(ViewPages.ERROR_PAGE).forward(request, response);
		 }
	}

}
