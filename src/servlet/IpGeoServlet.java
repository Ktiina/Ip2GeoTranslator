package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ipGeoClient.IpGeoClient;

/**
 * Servlet implementation class IpGeoServlet
 */
@WebServlet("/IpGeoServlet")
public class IpGeoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int IPINFO_EXPECTED_COUNT = 11;

    /**
     * Default constructor. 
     */
    public IpGeoServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		IpGeoClient ipGeoClient = new IpGeoClient();
		
		String ip = request.getParameter("ip").toString();
		String type = request.getParameter("type").toString();
		PrintWriter out = response.getWriter();
		
		if (ip.equals(""))
		{
			out.write("error: Please, provide a IP!");  
		}
		else if (!ipGeoClient.checkIpValidity(ip))
		{
			out.write("error: Please, provide a valid IPv4 address!");  
		}
		else
		{	
			List<String> resArr = ipGeoClient.ipToGeo(ip);
			
			// checker for expected count of IPInfo values
			if (resArr.size() == IPINFO_EXPECTED_COUNT)
			{
				String arrElement = "";
				out.write(type); // for callback to know where to print output
				for (int i = 0; i < resArr.size(); i++)
				{
					out.write("#"); // delimiter for callback string
					arrElement = resArr.get(i);
					if (arrElement == null)
						out.write("unknown");
					else
						out.write(arrElement);
				}	
			}
			else
			{
				out.write("error: Unexpected error! Please, provide another address!"); 
			}
		}
					 
		out.flush();
	    out.close();		
	}

}
