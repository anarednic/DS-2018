package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;
import repo.UserRepo;

@WebServlet("/")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			out.println("<!DOCTYPE html>");
			out.println("<html><head>");
			out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
			out.println("<title>Log-in</title></head>");
			out.println("<body>");
			out.println("<h1>Welcome to our application!</h1>");
			out.println("<b><p>Please enter your credentials</p></b>");
			out.println("<div>\r\n" + "  <div>\r\n" + "    <form method=\"POST\">\r\n"
					+ "      <input type=\"text\" name=\"username\" placeholder=\"Username\"/> </div>\r\n"
					+ "      <input type=\"password\" name=\"password\" placeholder=\"Password\"/></div>\r\n"
					+ "      <button>Login</button></div>    \r\n" + "    </form>\r\n" + "  </div>\r\n" + "</div>");
			out.println("</body>");
			out.println("</html>");
		} finally {
			out.close();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		UserRepo urepo = new UserRepo();
		String redirect = "";
		List<User> users = urepo.viewAllUsers();
		for (int i = 0; i < users.size(); i++) {
			if ((users.get(i).getUsername().equals(username)) && (users.get(i).getPassword().equals(password))) {
				if (users.get(i).getRole().equals("admin")) {
					Cookie cookie = new Cookie("USERID", users.get(i).getId().toString());
					cookie.setMaxAge(900);
					resp.addCookie(cookie);
					redirect = "/Tema1/flight";
					break;
				} else {
					Cookie cookie = new Cookie("USERID", users.get(i).getId().toString());
					cookie.setMaxAge(900);
					resp.addCookie(cookie);
					redirect = "/Tema1/Passenger";
					break;
				}
			} else
				redirect = "/Tema1/";

		}
		resp.sendRedirect(redirect);
	}

}
