package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Flight;
import repo.FlightRepo;

@WebServlet("/update1")
public class FlightUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FlightUpdate() {

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		FlightRepo frepo = new FlightRepo();
		List<Flight> f = frepo.viewAllFlights();
		for (int i = 0; i < f.size(); i++) {
			if (f.get(i).getFlightNumber() == Integer.parseInt(id)) {
				Flight x = f.get(i);
				
				x.setAirplaneType(req.getParameter("planetype"));
				try {
					x.setArrivalDate(new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse(req.getParameter("ddate")));
					x.setDepartureDate(new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse(req.getParameter("adate")));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(x.getAirplaneType()+x.getArrivalDate());
				frepo.updateFlight(x);
			}
		}
		resp.sendRedirect("flight");
	}
}
