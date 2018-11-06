package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.City;
import entity.Flight;
import entity.User;
import repo.CityRepo;
import repo.FlightRepo;
import repo.UserRepo;

@WebServlet("/flight")
public class Flights extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserRepo urepo = new UserRepo();
		for (Cookie c : request.getCookies()) {
			if (c.getName().equals("USERID")) {
				if (urepo.getUserById(Integer.parseInt(c.getValue())) != null
						&& urepo.getUserById(Integer.parseInt(c.getValue())).getRole().equals("admin")) {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					FlightRepo frepo = new FlightRepo();
					try {
						out.println("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<title>Flights</title>\r\n"
								+ "<style>\r\n" + "table, th, td {\r\n" + "    border: 1px solid black;\r\n"
								+ "    border-collapse: collapse;\r\n" + "}\r\n" + "th{\r\n" + "    padding: 5px;\r\n"
								+ "    text-align: left;  \r\n" + "	color:red;\r\n" + "}\r\n" + "td {\r\n"
								+ "    padding: 5px;\r\n" + "    text-align: left;  \r\n" + "}\r\n" + "</style>\r\n"
								+ "</head>\r\n" + "<body>\r\n" + "\r\n" + "<h1>Welcome to flights page!</h1>\r\n"
								+ "<b><p>All flights:</p></b>\r\n" + "\r\n" + "<table style=\"width:55%\">\r\n"
								+ "  <tr>\r\n" + "    <th>Flight Number</th>\r\n" + "    <th>Departure city</th>\r\n"
								+ "    <th>Arrival city</th>\r\n" + "	<th>Plane type</th>\r\n"
								+ "	<th>Departure date</th>\r\n" + "	<th>Arrival date</th>\r\n" + "  </tr>\r\n");
						List<Flight> flights = frepo.viewAllFlights();
						for (int i = 0; i < flights.size(); i++) {
							out.println("<tr>\r\n" + "    <td>");
							out.println(flights.get(i).getFlightNumber());
							out.println("</td>\r\n" + "    <td>");
							out.println(flights.get(i).getDepartureCity().getName());
							out.println("</td>\r\n" + "    <td>");
							out.println(flights.get(i).getArrivalCity().getName());
							out.println("</td>\r\n" + "    <td>");
							out.println(flights.get(i).getAirplaneType());
							out.println("</td>\r\n" + "    <td>");
							out.println(flights.get(i).getDepartureDate());
							out.println("</td>\r\n" + "    <td>");
							out.println(flights.get(i).getArrivalDate());
							out.println("</td>\r\n" + "  </tr>");
						}
						out.println("</table>\r\n" + "\r\n" + "<div>\r\n" + "\r\n" + "<form method=\"POST\">\r\n"
								+ "<b><p>Create a new flight</p></b>\r\n"
								+ "Departure city: <input type=\"text\" name=\"dcity\"><br>\r\n"
								+ "Arrival city: <input type=\"text\" name=\"acity\"><br>\r\n"
								+ "Plane type: <input type=\"text\" name=\"plane\"><br>\r\n"
								+ "Departure date: <input type=\"text\" name=\"ddate\"><br>\r\n"
								+ "Arrival date: <input type=\"text\" name=\"adate\"><br>\r\n"
								+ "  <input type=\"submit\" value=\"Add\">\r\n" + "</form>\r\n" + "\r\n" + "</div>\r\n"
								+ "\r\n" + "<div>\r\n" + "<form action=\"flightOp1\" method=\"post\">\r\n"
								+ "<b><p>Delete a flight</p></b>\r\n"
								+ "Flight number: <input type=\"text\" name=\"id\"  placeholder=\"Enter flight ID\"><br>\r\n"
								+ "  <input type=\"submit\" value=\"Delete\" >\r\n" + "</form>\r\n" + "</div>\r\n"
								+ "\r\n" + "</body>\r\n" + "</html>");
						out.println("<form action=\"update1\" method=\"post\">\r\n"
								+ "<b><p>Update a flight</p></b>\r\n"
								+ "Flight number: <input type=\"text\" name=\"id\"  placeholder=\"Enter flight ID\"><br>\r\n"
								+ "Plane type <input type=\"text\" name=\"planetype\"  placeholder=\"\"><br>"
								+ "Departure date <input type=\"text\" name=\"ddate\"  placeholder=\"\"><br>"
								+ "Arrival date <input type=\"text\" name=\"adate\"  placeholder=\"\"><br>"
								+ "  <input type=\"submit\" value=\"Update\" >\r\n" + "</form>\r\n" + "</div>\r\n"
								+ "\r\n" + "</body>\r\n" + "</html>");
					} finally {
						out.close();
					}
					break;
				} else {
					response.sendRedirect("/Tema1");
				}
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dcity = request.getParameter("dcity");
		String acity = request.getParameter("acity");
		String plane = request.getParameter("plane");
		String ddate = request.getParameter("ddate");
		String adate = request.getParameter("adate");

		FlightRepo frepo = new FlightRepo();
		CityRepo crepo = new CityRepo();
		Flight f = new Flight();
		City dc = crepo.viewAllCitys().stream().filter(p -> p.getId() == Integer.parseInt(dcity)).findFirst().get();
		City ac = crepo.viewAllCitys().stream().filter(p -> p.getId() == Integer.parseInt(acity)).findFirst().get();
		f.setDepartureCity(dc);
		f.setArrivalCity(ac);
		try {
			f.setArrivalDate(new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse(adate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			f.setDepartureDate(new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse(ddate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		f.setAirplaneType(plane);
		frepo.insertFlight(f);
		doGet(request, response);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
