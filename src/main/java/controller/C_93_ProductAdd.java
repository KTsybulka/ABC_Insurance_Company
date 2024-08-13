package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbHelpers.DAO_93_ReadProductQuery;

/**
 * Servlet implementation class C_93_ProductAdd
 */
@WebServlet("/C_93_ProductAdd")
public class C_93_ProductAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public C_93_ProductAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	        String product_name = request.getParameter("product_name");
	        String serial_no = request.getParameter("serial_no");
	        String product_model = request.getParameter("product_model");
	        String product_description = request.getParameter("product_description");
	  
			
	        DAO_93_ReadProductQuery productAddQuery;
			try {
				productAddQuery = new DAO_93_ReadProductQuery();
				
				productAddQuery.addProduct(product_name, serial_no, product_model, product_description);
				
		
				String url = "C_93_ReadProduct";
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);	
				
			} catch (ClassNotFoundException | SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
