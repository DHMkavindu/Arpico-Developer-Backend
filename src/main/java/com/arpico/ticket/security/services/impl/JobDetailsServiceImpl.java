package com.arpico.ticket.security.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arpico.ticket.models.Job;
import com.arpico.ticket.models.User;
import com.arpico.ticket.repository.JobRepository;
import com.arpico.ticket.repository.UserRepository;
import com.arpico.ticket.security.services.JobDetailsService;

@Service
public class JobDetailsServiceImpl implements JobDetailsService {

	@Autowired
	JobRepository jobRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public boolean createJob(Job job) {
		User user = userRepository.findByUsername(job.getCre_by()).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + job.getCre_by()));

		// Create current date
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		// Add new Job
		Job jobnew = new Job();
		jobnew.setJob_id(UUID.randomUUID().toString());
		jobnew.setCre_by(job.getCre_by());
		jobnew.setCre_dt(date);
		jobnew.setDecription(job.getDecription());
		jobnew.setDepartment(job.getDepartment());
		jobnew.setTime_period(job.getTime_period());
		jobnew.setStatus('1');
		jobnew.setSbu(job.getSbu());
		jobnew.setUser(user);

		jobRepository.save(jobnew);

		return true;
	}

	@Override
	@Transactional
	public boolean createJobByAdmin(Job jobs) {

		User user = userRepository.findByUsername(jobs.getMod_by()).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + jobs.getMod_by()));

		// Create current date
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		// Add new Job
		Job jobnew = new Job();
		jobnew.setJob_id(UUID.randomUUID().toString());
		jobnew.setCre_by(jobs.getCre_by());
		jobnew.setCre_dt(date);
		jobnew.setDiv_date(jobs.getDiv_date());
		jobnew.setDecription(jobs.getDecription());
		jobnew.setDepartment(jobs.getDepartment());
		jobnew.setTime_period(jobs.getTime_period());
		jobnew.setStatus('0');
		jobnew.setSbu(jobs.getSbu());
		jobnew.setUser(user);

		jobRepository.save(jobnew);

		return true;
	}

	@Override
	public boolean createJobAdminpart(Job job) {

		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		// Add new Job
		Job jobnew = new Job();
		jobnew.setJob_id(UUID.randomUUID().toString());
		jobnew.setCre_by(job.getCre_by());
		jobnew.setCre_dt(date);
		jobnew.setDecription(job.getDecription());
		jobnew.setDepartment(job.getDepartment());
		jobnew.setTime_period(job.getTime_period());
		jobnew.setStatus('0');
		jobnew.setSbu(job.getSbu());
		jobnew.setUser(job.getUser());

		jobRepository.save(jobnew);

		return true;

	}

	@Override
	public boolean updateJobStart(UUID id, Job jobs) {

		User user = userRepository.findByUsername(jobs.getCre_by()).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + jobs.getCre_by()));

		// Create current date
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		// Add new Job
		Job jobnew = new Job();
		jobnew.setJob_id(UUID.randomUUID().toString());
		jobnew.setCre_by(jobs.getCre_by());
		jobnew.setCre_dt(date);
		jobnew.setDecription(jobs.getDecription());
		jobnew.setDepartment(jobs.getDepartment());
		jobnew.setTime_period(jobs.getTime_period());
		jobnew.setStatus('1');
		jobnew.setSbu(jobs.getSbu());
		jobnew.setUser(user);

		jobRepository.save(jobnew);

		return true;

		// if (jobRepository.findById(id.toString()).isPresent()) {
		// Job updateJob = jobRepository.findById(id.toString()).get();
		//
		// // Create current date
		// long millis = System.currentTimeMillis();
		// java.sql.Date date = new java.sql.Date(millis);
		//
		// try {
		// updateJob.setSbu(jobs.getSbu());
		// updateJob.setDecription(jobs.getDecription());
		// updateJob.setDepartment(jobs.getDepartment());
		// updateJob.setTime_period(jobs.getTime_period());
		// updateJob.setMod_by(jobs.getMod_by());
		// updateJob.setMod_dt(date);
		// updateJob.setStatus('0');
		//
		// jobRepository.save(updateJob);
		// return true;
		// } catch (Exception e) {
		// return false;
		// }
		// } else {
		// return false;
		// }

	}

	@Override
	public boolean updateEndJob(UUID id, Job jobs) {

		if (jobRepository.findById(id.toString()).isPresent()) {
			Job updateJob = jobRepository.findById(id.toString()).get();

			// Create current date
			long millis = System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(millis);

			try {
				updateJob.setSbu(jobs.getSbu());
				updateJob.setDecription(jobs.getDecription());
				updateJob.setDepartment(jobs.getDepartment());
				updateJob.setTime_period(jobs.getTime_period());
				updateJob.setMod_by(jobs.getMod_by());
				updateJob.setMod_dt(date);
				updateJob.setStatus('1');

				jobRepository.save(updateJob);
				return true;
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}

	}

	@Override
	public boolean updateJobEnd(Job jobs) {
		if (jobRepository.findById(jobs.getJob_id()).isPresent()) {
			Job updateJob = jobRepository.findById(jobs.getJob_id()).get();

			// Create current date
			long millis = System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(millis);

			try {
				updateJob.setMod_by(jobs.getMod_by());
				updateJob.setMod_dt(date);
				updateJob.setStatus('2');

				jobRepository.save(updateJob);
				return true;
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public List<Job> getJobByUserAndStatusPending(String user_Id) {
		try {

			User user = userRepository.findByUsername(user_Id).orElseThrow(
					() -> new UsernameNotFoundException("User Not Found with -> username or email : " + user_Id));

			List<Job> jobList = jobRepository.findByUserAndStatus(user, '0');
			if (jobList != null) {
				return jobList;
			}
			return jobList;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Job> getJobByUserAndStatusStart(String user_Id) {
		try {
			User user = userRepository.findByUsername(user_Id).orElseThrow(
					() -> new UsernameNotFoundException("User Not Found with -> username or email : " + user_Id));
			List<Job> jobList = jobRepository.findByUserAndStatus(user, '1');
			if (jobList != null) {
				return jobList;
			}
			return jobList;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Job> getJobByUserAndStatusEndJobs(String user_Id) {
		try {
			User user = userRepository.findByUsername(user_Id).orElseThrow(
					() -> new UsernameNotFoundException("User Not Found with -> username or email : " + user_Id));
			List<Job> jobList = jobRepository.findByUserAndStatus(user, '1');
			if (jobList != null) {
				return jobList;
			}
			return jobList;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Job> getJobByUserAndStatusEnd(String user_Id) {
		try {
			User user = userRepository.findByUsername(user_Id).orElseThrow(
					() -> new UsernameNotFoundException("User Not Found with -> username or email : " + user_Id));
			List<Job> jobList = jobRepository.findByUserAndStatus(user, '2');
			if (jobList != null) {
				return jobList;
			}
			return jobList;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Job> getAllPendingJobs(char status) {
		try {
			List<Job> jobList = jobRepository.findByStatus('0');
			if (jobList != null) {
				for (int i = 0; i < jobList.size(); i++) {
					User user = userRepository.findByUsername(jobList.get(i).getUser().getUsername()).orElseThrow(
							() -> new UsernameNotFoundException("User Not Found with -> username or email : "));

					jobList.get(i).setCre_by(user.getUsername());
				}

				return jobList;
			}
			return jobList;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	@Override
	public List<Job> findByUserAndStatus(String user_Id, char status) {
		try {
			User user = userRepository.findByUsername(user_Id).orElseThrow(
					() -> new UsernameNotFoundException("User Not Found with -> username or email : " + user_Id));
			// get Today date
			LocalDate today = java.time.LocalDate.now();

			List<Job> jobList = jobRepository.findByUserAndStatusAndCre_dt(user, today);

			return jobList;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	@Override
	public List<Job> getAllEndJobs(char status) {
		try {
			List<Job> jobList = jobRepository.findByStatus('1');
			if (jobList != null) {
				for (int i = 0; i < jobList.size(); i++) {
					User user = userRepository.findByusername(jobList.get(i).getUser().getUsername()).orElseThrow(
							() -> new UsernameNotFoundException("User Not Found with -> username or email : "));

					jobList.get(i).setCre_by(user.getUsername());
				}

				return jobList;
			}
			return jobList;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	@Override
	public List<Job> findByUserAndDate(String name, String cre_dt) {
		try {

			User user = userRepository.findByUsername(name).orElseThrow(
					() -> new UsernameNotFoundException("User Not Found with -> username or email : " + name));

			List<Job> jobList = jobRepository.findByUserAndDate(user.getId(), cre_dt + "");

			System.out.println(jobList.size());

			if (jobList == null || jobList.size() <= 0) {
				return null;
			}
			return jobList;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
