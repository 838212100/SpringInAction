package my.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("this.getInitParameter()========="+this.getInitParameter("name"));
		System.out.println("this.getInitParameter()========="+this.getInitParameter("sex"));
		System.out.println("==========================================================================================");
		
		ServletContext contextA = getServletConfig().getServletContext();
		System.out.println("getAttribute()========="+contextA.getAttribute("name"));
		System.out.println("getAttribute()========="+contextA.getAttribute("sex"));
		System.out.println("==========================================================================================");
		
		ServletContext context = getServletContext();
		System.out.println("getAttribute()========="+context.getAttribute("name"));
		System.out.println("getAttribute()========="+context.getAttribute("sex"));
		System.out.println("==========================================================================================");
		
//		ServletContext contextConfig = getServletConfig().getServletContext();
//		System.out.println("getServletConfig()========="+contextConfig.getInitParameter("name"));
//		System.out.println("getServletConfig()========="+contextConfig.getInitParameter("sex"));
//		System.out.println("==========================================================================================");
//		ServletContext context = getServletContext();
//		System.out.println("getServletContext()========="+context.getInitParameter("name"));
//		System.out.println("getServletContext()========="+context.getInitParameter("sex"));
		
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
