package com.exemplo.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class MensagemServlet
 */
@WebServlet("/MensagemServlet")
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
        response.setContentType("text/html");
       
        String nome = request.getParameter("nome");
        
        
        if (nome == null || nome.isEmpty()) {
            nome = "";
        }
        
        response.getWriter().append("<html><body>")
        .append("<h1>Olá, ").append(nome).append("!</h1>")
        .append("</body></html>");
        
        // http://localhost:8080/Ex01/welcome?nome=Joao URL Teste
	}



}
