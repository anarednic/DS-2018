package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Flight;
import entity.User;
import repo.FlightRepo;

public class Passenger extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Passenger() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		FlightRepo frepo = new FlightRepo();
		try {
			out.println("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<title>Flights</title>\r\n"
					+ "<style>\r\n" + "table, th, td {\r\n" + "    border: 1px solid black;\r\n"
					+ "    border-collapse: collapse;\r\n" + "}\r\n" + "th{\r\n" + "    padding: 5px;\r\n"
					+ "    text-align: left;  \r\n" + "	color:red;\r\n" + "}\r\n" + "td {\r\n" + "    padding: 5px;\r\n"
					+ "    text-align: left;  \r\n" + "}\r\n" + "</style>\r\n" + "</head>\r\n" + "<body>\r\n" + "\r\n"
					+ "<h1>Welcome to flights page!</h1>\r\n" + "<b><p>My flights:</p></b>\r\n" + "\r\n"
					+ "<table style=\"width:55%\">\r\n" + "  <tr>\r\n" + "    <th>Flight Number</th>\r\n"
					+ "    <th>Departure city</th>\r\n" + "    <th>Arrival city</th>\r\n" + "	<th>Plane type</th>\r\n"
					+ "	<th>Departure date</th>\r\n" + "	<th>Arrival date</th>\r\n" + "  </tr>\r\n");
			User u = new User();
			u.setId(2);
			List<Flight> flights = frepo.allFlightsOfAUser(u);
			System.out.println(flights.size());
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
		} finally {
			out.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
