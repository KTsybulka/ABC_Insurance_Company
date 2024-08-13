package controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbHelpers.DAO_93_ReadClaimQuery;
import dbHelpers.DAO_93_ReadProductQuery;
import model.M_93_InsuranceClaim;
import model.M_93_Product;

/**
 * Servlet implementation class C_93_ProductUpdate
 */
@WebServlet("/C_93_ProductUpdate")
public class C_93_ProductUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public C_93_ProductUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int productid = Integer.parseInt(request.getParameter("productid"));
		
		System.out.println("Received productid: " + productid);
		
		try {
			DAO_93_ReadProductQuery productUptateQuery= new DAO_93_ReadProductQuery();
			
			M_93_Product productUpdate = productUptateQuery.doReadProductObjectById(productid);
			
			request.setAttribute("productUpdate", productUpdate);
			
			
			String url = "/V_93_updateProductForm.jsp";				
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);			
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}			

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		
        int productid = Integer.parseInt(request.getParameter("productid"));
        String product_name = request.getParameter("product_name");
        String serial_no = request.getParameter("serial_no");
        String product_model = request.getParameter("product_model");
        String product_description = request.getParameter("product_description");
  
		
        DAO_93_ReadProductQuery productQuery;
		try {
			productQuery = new DAO_93_ReadProductQuery();
			
			productQuery.doUpdateProduct(productid, product_name, serial_no, product_model, product_description);
			
	
			String url = "C_93_ReadProduct";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);	
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
