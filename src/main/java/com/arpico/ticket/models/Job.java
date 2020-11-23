package com.arpico.ticket.models;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "jobs")
public class Job implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "job_id")
	private String job_id;

	private String sbu;

	private String department;

	private String decription;

	private Date cre_dt;

	private String cre_by;

	private Date mod_dt;

	private String mod_by;

	private char status;

	private String time_period;

	private Date div_date;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id", nullable = false)
	private User user;

	public Job() {

	}

	public Job(String job_id, String sbu, String department, String decription, Date cre_dt, String cre_by, Date mod_dt,
			String mod_by, char status, String time_period, Date div_date, User user) {
		super();
		this.job_id = job_id;
		this.sbu = sbu;
		this.department = department;
		this.decription = decription;
		this.cre_dt = cre_dt;
		this.cre_by = cre_by;
		this.mod_dt = mod_dt;
		this.mod_by = mod_by;
		this.status = status;
		this.time_period = time_period;
		this.div_date = div_date;
		this.user = user;
	}

	public String getJob_id() {
		return job_id;
	}

	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}

	public String getSbu() {
		return sbu;
	}

	public void setSbu(String sbu) {
		this.sbu = sbu;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}

	public Date getCre_dt() {
		return cre_dt;
	}

	public void setCre_dt(Date cre_dt) {
		this.cre_dt = cre_dt;
	}

	public String getCre_by() {
		return cre_by;
	}

	public void setCre_by(String cre_by) {
		this.cre_by = cre_by;
	}

	public Date getMod_dt() {
		return mod_dt;
	}

	public void setMod_dt(Date mod_dt) {
		this.mod_dt = mod_dt;
	}

	public String getMod_by() {
		return mod_by;
	}

	public void setMod_by(String mod_by) {
		this.mod_by = mod_by;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public String getTime_period() {
		return time_period;
	}

	public void setTime_period(String time_period) {
		this.time_period = time_period;
	}

	public User getUser() {
		return user;
	}

	public Date getDiv_date() {
		return div_date;
	}

	public void setDiv_date(Date div_date) {
		this.div_date = div_date;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Job{" + "job_id='" + job_id + '\'' + ", sbu='" + sbu + '\'' + ", department='" + department + '\''
				+ ", decription='" + decription + '\'' + ", cre_dt=" + cre_dt + ", cre_by='" + cre_by + '\''
				+ ", mod_dt=" + mod_dt + ", mod_by='" + mod_by + '\'' + ", status=" + status + ", time_period='"
				+ time_period + '\'' + ", div_date=" + div_date + ", user=" + user + '}';
	}
}
