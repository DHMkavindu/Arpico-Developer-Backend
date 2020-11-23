package com.arpico.ticket.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sbu")
public class Sbu {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String sbu;

	public Sbu() {
		// TODO Auto-generated constructor stub
	}

	public Sbu(int id, String sbu) {
		super();
		this.id = id;
		this.sbu = sbu;
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

	@Override
	public String toString() {
		return "sbu [id=" + id + ", sbu=" + sbu + "]";
	}
}
