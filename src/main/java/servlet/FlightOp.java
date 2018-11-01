package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Flight;
import repo.FlightRepo;

public class FlightOp extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public FlightOp() {
    }

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("id");
		System.out.println(id);
		FlightRepo frepo=new FlightRepo();
		List<Flight> f= frepo.viewAllFlights();
		for (int i=0; i<f.size(); i++) {
			if (f.get(i).getFlightNumber()==Integer.parseInt(id)) {
				frepo.deleteFlight(f.get(i));
			}
		}
		resp.sendRedirect("flight");
	}
}
