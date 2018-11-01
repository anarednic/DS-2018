package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
@Entity
public class Flight implements Serializable {

	private static final long serialVersionUID = -5420842260643953996L;
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer flightNumber;
	@Column
	private String airplaneType;
	
	@OneToOne
	private City departureCity;
	
	@OneToOne
	private City arrivalCity;
	@Column
	private Date departureDate;
	@Column
	private Date arrivalDate;
	
	@ManyToMany
    @JoinTable(name = "user_flight", joinColumns = @JoinColumn(name = "flight_number"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;
	
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public Flight() {

	}
	public Flight(Integer flightNumber, String airplaneType, City departureCity, City arrivalCity, Date departureDate,
			Date arrivalDate) {
		super();
		this.flightNumber = flightNumber;
		this.airplaneType = airplaneType;
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
	}
	
	
	public Integer getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(Integer flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getAirplaneType() {
		return airplaneType;
	}
	public void setAirplaneType(String airplaneType) {
		this.airplaneType = airplaneType;
	}
	public City getDepartureCity() {
		return departureCity;
	}
	public void setDepartureCity(City departureCity) {
		this.departureCity = departureCity;
	}
	public City getArrivalCity() {
		return arrivalCity;
	}
	public void setArrivalCity(City arrivalCity) {
		this.arrivalCity = arrivalCity;
	}
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
