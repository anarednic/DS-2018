package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
@Entity
public class User implements Serializable {

	private static final long serialVersionUID = -7161318957659791704L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column
	private String username;
	@Column
	private String name;
	@Column
	private String password;
	@Column
	private String role;
	@ManyToMany(mappedBy = "users")
    private List<Flight> flights;
	
	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public User() {

	}

	public User(String username, String name, String password,String role) {
		super();
		this.username = username;
		this.name = name;
		this.password = password;
		this.role=role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
