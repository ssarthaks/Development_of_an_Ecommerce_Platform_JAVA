package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import model.ProductImage;
import model.User;
import service.ProductDao;
import service.UserDao;

/**
 * Servlet implementation class AdminPanel
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/user" })
public class AdminPanel extends HttpServlet {
private static final long serialVersionUID = 1L;
    private UserDao dao;
    private ProductDao pdao;
    public void init(ServletConfig config) throws ServletException {
    super.init(config);
    dao = new UserDao();
    pdao = new ProductDao();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPanel() {
        super();
        // TODO Auto-generated constructor stub
    }

/**
 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
 */
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
	try {
		List<User> listofUser = dao.getAllUser();
		List<Product> productList = pdao.getAllProduct();
		
		
		request.setAttribute("listofUser", listofUser);
		request.setAttribute("productList", productList);
		request.getRequestDispatcher(ViewPages.ADMIN_PANEL).forward(request, response);
	
		
		
	} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}

}

/**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

}

}