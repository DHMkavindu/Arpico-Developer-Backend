package com.arpico.ticket.controllers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.arpico.ticket.models.Job;
import com.arpico.ticket.security.services.JobDetailsService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/job")
public class JobController {

	@Autowired
	private JobDetailsService JobDetailsService;

	@GetMapping("/")
	public String test() {
		return "Its work";
	}

	@PostMapping("/save")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody Job job, BindingResult result) {
		try {
			if (result.hasErrors()) {
				Map<String, String> errormap = new HashMap<String, String>();
				for (FieldError error : result.getFieldErrors()) {
					errormap.put(error.getField(), error.getDefaultMessage());
				}
				return new ResponseEntity<Map<String, String>>(errormap, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Object>(JobDetailsService.createJob(job), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(JobDetailsService.createJob(job), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/saveByAdmin")
	public ResponseEntity<?> authenticateUserByAdmin(@Valid @RequestBody Job job, BindingResult result) {
		try {
			if (result.hasErrors()) {
				Map<String, String> errormap = new HashMap<String, String>();
				for (FieldError error : result.getFieldErrors()) {
					errormap.put(error.getField(), error.getDefaultMessage());
				}
				return new ResponseEntity<Map<String, String>>(errormap, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Object>(JobDetailsService.createJobByAdmin(job), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(JobDetailsService.createJobByAdmin(job),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/saveAdmin")
	public ResponseEntity<?> authenticateAdmin(@Valid @RequestBody Job job, BindingResult result) {
		try {
			if (result.hasErrors()) {
				Map<String, String> errormap = new HashMap<String, String>();
				for (FieldError error : result.getFieldErrors()) {
					errormap.put(error.getField(), error.getDefaultMessage());
				}
				return new ResponseEntity<Map<String, String>>(errormap, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Object>(JobDetailsService.createJobAdminpart(job), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(JobDetailsService.createJobAdminpart(job),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/jobStart/{job_id}")
	public ResponseEntity<?> updateforStartProcess(@PathVariable(value = "job_id") UUID id, @RequestBody Job job,
			BindingResult result) {
		try {
			if (result.hasErrors()) {
				Map<String, String> errormap = new HashMap<>();
				for (FieldError error : result.getFieldErrors()) {
					errormap.put(error.getField(), error.getDefaultMessage());
				}
				return new ResponseEntity<Map<String, String>>(errormap, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Object>(JobDetailsService.updateEndJob(id, job), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(JobDetailsService.updateEndJob(id, job),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/jobStarts/{job_id}")
	public ResponseEntity<?> updateforStartProcessToday(@PathVariable(value = "job_id") UUID id, @RequestBody Job job,
			BindingResult result) {
		try {
			if (result.hasErrors()) {
				Map<String, String> errormap = new HashMap<>();
				for (FieldError error : result.getFieldErrors()) {
					errormap.put(error.getField(), error.getDefaultMessage());
				}
				return new ResponseEntity<Map<String, String>>(errormap, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Object>(JobDetailsService.updateJobStart(id, job), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(JobDetailsService.updateJobStart(id, job),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/jobEnd/{job_id}")
	public ResponseEntity<?> updateforEndProcess(@PathVariable(value = "job_id") UUID id, @RequestBody Job job,
			BindingResult result) {
		try {
			if (result.hasErrors()) {
				Map<String, String> errormap = new HashMap<>();
				for (FieldError error : result.getFieldErrors()) {
					errormap.put(error.getField(), error.getDefaultMessage());
				}
				return new ResponseEntity<Map<String, String>>(errormap, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Object>(JobDetailsService.updateJobEnd(job), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(JobDetailsService.updateJobEnd(job), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(path = "/getJobPending/{user_id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPendingJob(@PathVariable String user_id) {
		try {
			return new ResponseEntity<Object>(JobDetailsService.getJobByUserAndStatusPending(user_id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(JobDetailsService.getJobByUserAndStatusPending(user_id),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(path = "/getcompleteJobs/{user_id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCompleteJobs(@PathVariable String user_id) {
		try {
			return new ResponseEntity<Object>(JobDetailsService.getJobByUserAndStatusEndJobs(user_id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(JobDetailsService.getJobByUserAndStatusEndJobs(user_id),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(path = "/getJobDone/{user_id}", method = RequestMethod.GET)
	public ResponseEntity<?> getDoneJob(@PathVariable String user_id) {
		try {
			return new ResponseEntity<Object>(JobDetailsService.getJobByUserAndStatusStart(user_id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(JobDetailsService.getJobByUserAndStatusStart(user_id),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(path = "/getPendingAll", method = RequestMethod.GET)
	public ResponseEntity<?> getPendingAll() {
		try {
			return new ResponseEntity<Object>(JobDetailsService.getAllPendingJobs('0'), HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<Object>(JobDetailsService.getAllPendingJobs('0'),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(path = "/getEndAll", method = RequestMethod.GET)
	public ResponseEntity<?> getEndAll() {
		try {
			return new ResponseEntity<Object>(JobDetailsService.getAllEndJobs('1'), HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<Object>(JobDetailsService.getAllEndJobs('1'), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(path = "/getEndAllWithUserId/{user_id}", method = RequestMethod.GET)
	public ResponseEntity<?> getEndAllWithUser(@PathVariable String user_id) {
		try {
			return new ResponseEntity<Object>(JobDetailsService.findByUserAndStatus(user_id, '1'), HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<Object>(JobDetailsService.findByUserAndStatus(user_id, '1'),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(path = "/getTodayJobs/{user_id}/{date}", method = RequestMethod.GET)
	public ResponseEntity<?> getJobWithDate(@PathVariable String user_id, String cre_dt) {
		try {
			return new ResponseEntity<Object>(JobDetailsService.findByUserAndDate(user_id, cre_dt), HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<Object>(JobDetailsService.findByUserAndDate(user_id, cre_dt),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(path = "/getPendingJobCount/{user_id}", method = RequestMethod.GET)
	public Integer getJoCount(@PathVariable String user_id) {
		Integer jobCount = 0;
		try {
			List<Job> jobList = JobDetailsService.findByUserAndStatus(user_id, '0');
			jobCount = jobList.size();

			return jobCount;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@RequestMapping(path = "/getCompleteJobCount/{user_id}", method = RequestMethod.GET)
	public Integer getJoCountComplete(@PathVariable String user_id) {
		Integer jobCount = 0;
		try {
			List<Job> jobList = JobDetailsService.findByUserAndStatus(user_id, '1');
			jobCount = jobList.size();

			return jobCount;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@GetMapping("/report/product/{user_name}/{date}")
	public ResponseEntity<?> generateExcelReport(@PathVariable("user_name") String user_name,
			@PathVariable("date") String date) throws IOException, DocumentException, ParseException {

		List<Job> jobList = JobDetailsService.findByUserAndDate(user_name, date);

		if (jobList == null) {
			return null;
		} else {
			Document document = new Document(PageSize.A4, 25, 25, 25, 25);

			ByteArrayOutputStream os = new ByteArrayOutputStream();

			PdfWriter.getInstance(document, os);

			document.open();

			Paragraph title = new Paragraph("Daily Report",
					FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD, new BaseColor(13, 11, 11)));
			title.setAlignment(Element.ALIGN_CENTER);

			Paragraph title1 = new Paragraph("Developer Name : " + user_name + " \nDate : " + date + "",
					FontFactory.getFont(FontFactory.HELVETICA, 15, Font.NORMAL, new BaseColor(13, 11, 11)));

			document.add(title);
			document.add(title1);

			PdfPTable table = new PdfPTable(5);
			table.setWidthPercentage(100);
			table.setSpacingBefore(50);
			table.setSpacingAfter(50);

			PdfPCell c1 = new PdfPCell(new Phrase("Create User"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);

			// padding
			c1.setPaddingTop(0f);
			c1.setPaddingBottom(7f);

			// background color
			c1.setBackgroundColor(new BaseColor(98, 167, 253));

			// border
			c1.setBorder(0);
			c1.setBorderWidthBottom(2f);
			table.addCell(c1);

			PdfPCell c2 = new PdfPCell(new Phrase("Department"));
			c2.setHorizontalAlignment(Element.ALIGN_CENTER);

			// padding
			c2.setPaddingTop(0f);
			c2.setPaddingBottom(7f);

			// background color
			c2.setBackgroundColor(new BaseColor(98, 167, 253));

			// border
			c2.setBorder(0);
			c2.setBorderWidthBottom(2f);
			table.addCell(c2);

			PdfPCell c3 = new PdfPCell(new Phrase("Sbu"));
			c3.setHorizontalAlignment(Element.ALIGN_CENTER);

			// padding
			c3.setPaddingTop(0f);
			c3.setPaddingBottom(7f);

			// background color
			c3.setBackgroundColor(new BaseColor(98, 167, 253));

			// border
			c3.setBorder(0);
			c3.setBorderWidthBottom(2f);
			table.addCell(c3);

			PdfPCell c4 = new PdfPCell(new Phrase("Descriptrion"));
			c4.setHorizontalAlignment(Element.ALIGN_CENTER);

			// padding
			c4.setPaddingTop(0f);
			c4.setPaddingBottom(7f);

			// background color
			c4.setBackgroundColor(new BaseColor(98, 167, 253));

			// border
			c4.setBorder(0);
			c4.setBorderWidthBottom(2f);
			table.addCell(c4);

			PdfPCell c5 = new PdfPCell(new Phrase("Time"));
			c5.setHorizontalAlignment(Element.ALIGN_CENTER);

			// padding
			c5.setPaddingTop(0f);
			c5.setPaddingBottom(7f);

			// background color
			c5.setBackgroundColor(new BaseColor(98, 167, 253));

			// border
			c5.setBorder(0);
			c5.setBorderWidthBottom(2f);
			table.addCell(c5);

			for (Job jobs : jobList) {
				table.addCell(jobs.getCre_by());
				table.addCell(jobs.getDepartment());
				table.addCell(jobs.getSbu());
				table.addCell(jobs.getDecription());
				table.addCell(jobs.getTime_period());
			}

			System.out.println(table);

			document.add(table);

			document.close();

			ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
			headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ProductPdfReport.pdf");

			ResponseEntity<?> response = new ResponseEntity<Resource>(new InputStreamResource(is), headers,
					HttpStatus.OK);

			return response;
		}

	}
}
