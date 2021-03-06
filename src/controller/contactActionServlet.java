package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Contact;
import model.Phone;

/**
 * Servlet implementation class contactActionServlet
 */
@WebServlet("/contactActionServlet")
public class contactActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public contactActionServlet() {
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
		// TODO Auto-generated method stub
		String act = request.getParameter("doThis");
		
		if (act == null) {
			getServletContext().getRequestDispatcher("/viewAllServlet").forward(request, response);
		}
		else if (act.equals("Edit Item")) {
			ContactHelper nch = new ContactHelper();
			int thisContactID = Integer.parseInt(request.getParameter("cID"));
			Contact thisContact = nch.findContactByID(thisContactID);
			PhoneHelper pch = new PhoneHelper();
			int thisPhoneID = Integer.parseInt(request.getParameter("pID"));
			Phone thisPhone = pch.findPhoneByID(thisPhoneID);
			request.setAttribute("ContactInfo", thisContact);
			request.setAttribute("thisPhone", thisPhone);
			getServletContext().getRequestDispatcher("/addPhone.jsp").forward(request, response);
		}
		else if (act.equals("Delete Item")) {
			ContactHelper nch = new ContactHelper();
			int thisContactID = Integer.parseInt(request.getParameter("cID"));
			Contact thisContact = nch.findContactByID(thisContactID);
			Integer phoneId = Integer.parseInt(request.getParameter("pID"));
			nch.deletePhone(thisContact, phoneId);
			getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}
		else if (act.equals("Add Item")) {
			ContactHelper nch = new ContactHelper();
			int thisContactID = Integer.parseInt(request.getParameter("cID"));
			Contact thisContact = nch.findContactByID(thisContactID);
			request.setAttribute("ContactInfo", thisContact);
			Phone thisPhone = new Phone();
			request.setAttribute("thisPhone", thisPhone);
			getServletContext().getRequestDispatcher("/addPhone.jsp").forward(request, response);
			
		}
	}

}
