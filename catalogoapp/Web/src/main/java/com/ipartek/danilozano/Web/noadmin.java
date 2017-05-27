package com.ipartek.danilozano.Web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.danilozano.DAL.DAL;
import com.ipartek.danilozano.Tipos.Producto;

@WebServlet("/noadmin/productocrud")
public class noadmin extends HttpServlet {
	
	

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(request, response);
		
		
		
		
		
	}

}
