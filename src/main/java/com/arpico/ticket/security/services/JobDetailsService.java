package com.arpico.ticket.security.services;

import java.util.List;
import java.util.UUID;

import com.arpico.ticket.models.Job;

public interface JobDetailsService {

	public boolean createJob(Job jobs);

	public boolean createJobByAdmin(Job jobs);

	public boolean createJobAdminpart(Job job);

	public boolean updateEndJob(UUID id, Job jobs);

	public boolean updateJobStart(UUID id, Job jobs);

	public boolean updateJobEnd(Job jobs);

	public List<Job> getJobByUserAndStatusPending(String user_id);

	public List<Job> getJobByUserAndStatusStart(String user_Id);

	public List<Job> getJobByUserAndStatusEndJobs(String user_Id);

	public List<Job> getJobByUserAndStatusEnd(String user_Id);

	public List<Job> getAllPendingJobs(char status);

	public List<Job> getAllEndJobs(char status);

	public List<Job> findByUserAndStatus(String user_id, char statsu);

	List<Job> findByUserAndDate(String user_id, String cre_dt);
}
