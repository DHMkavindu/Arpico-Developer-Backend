package com.arpico.ticket.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "locations")
public class Locations {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String sbu;
	private String location;

	public Locations() {
		// TODO Auto-generated constructor stub
	}

	public Locations(int id, String sbu, String location) {
		super();
		this.id = id;
		this.sbu = sbu;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSbu() {
		return sbu;
	}

	public void setSbu(String sbu) {
		this.sbu = sbu;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "locations [id=" + id + ", sbu=" + sbu + ", location=" + location + "]";
	}
}
