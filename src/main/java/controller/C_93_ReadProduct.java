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
 * Servlet implementation class C_93_ReadProduct
 */
@WebServlet("/C_93_ReadProduct")
public class C_93_ReadProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public C_93_ReadProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		DAO_93_ReadProductQuery rq = new DAO_93_ReadProductQuery();
		
		String productid = request.getParameter("productid");
		
				
		String tableAll, tableOne, searchForm = ""; 
		
		if(productid != null && !productid.isEmpty() ) {	
			rq.doReadProductById(productid);			
			tableOne = rq.getHTMLProductTable();
//			System.out.println("Table One : " + tableOne);
			request.setAttribute("tableOne", tableOne);
		}
		
		searchForm = rq.getSerchForm();				
//		System.out.println("Search Form: " + searchForm);		
		request.setAttribute("searchForm", searchForm);
		
		
		rq.doReadAllProduct();		
		tableAll = rq.getHTMLProductTable();				
		request.setAttribute("tableAll", tableAll);
		

		String url = "/V_93_adminReadProduct.jsp";		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);		
		
				
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
