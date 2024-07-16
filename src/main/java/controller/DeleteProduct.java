package controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ProductDao;

/**
 * Servlet implementation class DeleteProduct
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/DeleteProduct" })
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDao dao;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new ProductDao();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProduct() {
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
		String productModelNumberToDelete = request.getParameter("productModelNumber");
		boolean deleted = dao.deleteProduct(productModelNumberToDelete);
		System.out.println(deleted);
		if(deleted) {
			response.sendRedirect(request.getContextPath()+ "/AdminProduct");
		}
		else {
			request.setAttribute("errormessage", "failed to delete product");
			request.getRequestDispatcher(ViewPages.ERROR_PAGE).forward(request, response);
		}
	}

}
