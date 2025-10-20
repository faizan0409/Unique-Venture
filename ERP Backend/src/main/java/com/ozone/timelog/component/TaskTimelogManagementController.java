package com.ozone.timelog.component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ozone.application.utils.JSONUtils;
import com.ozone.component.datamodel.Employee;
import com.ozone.component.datamodel.Notification;
import com.ozone.component.datamodel.Project;
import com.ozone.component.datamodel.TaskMap;
import com.ozone.component.datamodel.TaskTimelog;
import com.ozone.component.datamodel.Tasks;
import com.ozone.component.datamodel.Timelog;
import com.ozone.notification.component.NotificationRepository;
import com.ozone.project.component.ProjectRepository;
import com.ozone.project.component.TaskMapRepository;
import com.ozone.project.component.TaskRepository;
import com.ozone.response.component.model.ResponseModel;
import com.ozone.user.component.EmployeeRepository;

@Controller
@RequestMapping("/tasktimelog")
public class TaskTimelogManagementController {

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private TaskMapRepository taskMapRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private TaskTimelogRepository taskTimelogRepository;
	
	@Autowired
	private TimelogRepository timelogRepository;
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	@PostMapping(value = "/savetasktimelog", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> add(@RequestBody Map<String, String> requestBody) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).add(requestBody));

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());

		Project project = new Project();
		Employee employee = new Employee();
		Map<String, String[]> taskTimelogEntry = new HashMap<>();
		List<TaskTimelog> taskTimelogList = new ArrayList<>();
		double totalWorkHrsForDay = 0.0;
	
		double oldTaskTimeValue = 0.0;
		double hrsUpdatedBy = 0.0;
		
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		try {
			project = projectRepository.findByProjectId(requestBody.get("projectId"));
			employee = employeeRepository.findByEmployeeId(requestBody.get("employeeId"));
			taskTimelogEntry = JSONUtils.getHasMapFromJSONString(requestBody.get("taskTimelog"));
			
			if(project!=null && employee!=null) {
				 
				String strDate = requestBody.get("day");
				
				for(String taskName : taskTimelogEntry.keySet()) {
					
					Tasks task = taskRepository.findTaskByNameCaseSensitive(taskName);
					
					if(task != null) {
						TaskMap taskMap = taskMapRepository.findByTaskProjectEmployeeId(task.getId(), employee.getEmployeeId(), project.getProjectId());
						
						if(taskMap != null && Double.parseDouble(taskTimelogEntry.get(taskName)[1])>0) {
							
							TaskTimelog taskTimeLog = new TaskTimelog();
							
							taskTimeLog = taskTimelogRepository.findByTaskMapIdForDay(taskMap.getId(), strDate);
							
							if(taskTimeLog != null) {
								oldTaskTimeValue  = taskTimeLog.getWorkHrs();
								hrsUpdatedBy = Double.parseDouble(taskTimelogEntry.get(taskName)[1]) - oldTaskTimeValue;
								totalWorkHrsForDay += hrsUpdatedBy;
							}else{
								taskTimeLog = new TaskTimelog();
								taskTimeLog.setTaskMapId(taskMap.getId());
								taskTimeLog.setDay(strDate);
								totalWorkHrsForDay += Double.parseDouble(taskTimelogEntry.get(taskName)[1]);
							}
							
							taskTimeLog.setWorkHrs(Double.parseDouble(taskTimelogEntry.get(taskName)[1]));
							taskTimeLog.setComment(taskTimelogEntry.get(taskName)[0]);
							taskTimeLog.setCreatedBy(requestBody.get("createdBy"));
							
							taskTimelogList.add(taskTimeLog);
						}
					}else {
						response.setStatus(HttpStatus.EXPECTATION_FAILED);
						response.setMessage("Not able to find task "+taskName);
						response.setContent("Not able to find task "+taskName);
						return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
								HttpStatus.EXPECTATION_FAILED);
					}
					
				}
				
				//save task specific time log
				if(!taskTimelogList.isEmpty()) {
					taskTimelogRepository.saveAll(taskTimelogList);
					
					Notification notification = new Notification();
					notification.setNotificationMessage("Timelog added for "+strDate+" by "+requestBody.get("employeeId"));
					notification.setSeenStatus(false);
					notification.setCreatedBy(requestBody.get("createdBy"));
					notificationRepository.save(notification);
				}
				
				//save time log for complete day
				if(totalWorkHrsForDay > 0) {
					Timelog timelog = new Timelog();
					
					timelog = timelogRepository.findByEmployeeId(requestBody.get("employeeId"), strDate);
					
					if(timelog != null) {
						double totalHrs = timelog.getWorkHrs() + totalWorkHrsForDay;
					
						if(totalHrs <= 24.0) {
							timelog.setWorkHrs(totalHrs);
							timelog.setUpdatedBy(requestBody.get("createdBy"));
							timelogRepository.save(timelog);
						}else {
							response.setStatus(HttpStatus.EXPECTATION_FAILED);
							response.setMessage("Please check total working hours for "+requestBody.get("day"));
							response.setContent("Please check total working hours for "+requestBody.get("day"));
							return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
									HttpStatus.EXPECTATION_FAILED);
						}
						
					}else {
						response.setStatus(HttpStatus.EXPECTATION_FAILED);
						response.setMessage("Login details not available for "+requestBody.get("employeeId")+" on "+requestBody.get("day"));
						response.setContent("Login details not available for "+requestBody.get("employeeId")+" on "+requestBody.get("day"));
						return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
								HttpStatus.EXPECTATION_FAILED);
					}
				}
			}else {
					response.setStatus(HttpStatus.EXPECTATION_FAILED);
					response.setMessage("Not able to add time log entry for project "+requestBody.get("projectId"));
					response.setContent("Not able to add time log entry for project "+requestBody.get("projectId"));
					return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
							HttpStatus.EXPECTATION_FAILED);
			}
			
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while adding time log");
			
			//Throwables.getStackTraceAsString(ex);
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}

		response.setStatus(HttpStatus.OK);
		response.setMessage("Timelog for project "+requestBody.get("projectId")+" added successfully");
		response.setContent("Timelog for project "+requestBody.get("projectId")+" added successfully");
		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}

	//not in scope for now 
	@PostMapping(value = "/delete", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> delete(@RequestBody Map<String, String> requestBody) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).delete(requestBody));

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());

		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		
		Project project = new Project();
		Employee employee = new Employee();
		Map<String, String[]> taskTimelogEntry = new HashMap<>();
		List<TaskTimelog> taskTimelogList = new ArrayList<>();
		double hrsToBeDeleted = 0.0;
		
		try {
			project = projectRepository.findByProjectId(requestBody.get("projectId"));
			employee = employeeRepository.findByEmployeeId(requestBody.get("employeeId"));
			taskTimelogEntry = JSONUtils.getHasMapFromJSONString(requestBody.get("taskTimelog"));
			

			if(project!=null && employee!=null) {
				
				//SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
				String strDate = requestBody.get("day"); 
				
				for(String taskName : taskTimelogEntry.keySet()) {
					
					Tasks task = taskRepository.findTaskByNameCaseSensitive(taskName);
					
					TaskMap taskMap = taskMapRepository.findByTaskProjectEmployeeId(task.getId(), employee.getEmployeeId(), project.getProjectId());
					
					TaskTimelog taskTimelog = taskTimelogRepository.findByTaskMapIdForDay(taskMap.getId(), strDate);
					
					if(taskTimelog != null) {
						taskTimelog.setDeleteStatus(true);
						taskTimelogList.add(taskTimelog);
						
						hrsToBeDeleted += taskTimelog.getWorkHrs();
					}
				}
				
				//save task specific time log
				if(!taskTimelogList.isEmpty()) {
					taskTimelogRepository.saveAll(taskTimelogList);
				}
				
				Timelog timelog = timelogRepository.findByEmployeeId(requestBody.get("employeeId"), strDate);
				if(timelog != null) {
					double totalHrs = timelog.getWorkHrs() + hrsToBeDeleted;
					
					if(totalHrs <= 24.0) {
						timelog.setWorkHrs(totalHrs);
						timelog.setUpdatedBy(requestBody.get("createdBy"));
						timelogRepository.save(timelog);
					}else {
						response.setStatus(HttpStatus.EXPECTATION_FAILED);
						response.setMessage("Please check total working hours for "+requestBody.get("day"));
						response.setContent("Please check total working hours for "+requestBody.get("day"));
						return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
								HttpStatus.EXPECTATION_FAILED);
					}
				}
			}

		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while deleting time log");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}

		response.setStatus(HttpStatus.OK);
		response.setMessage("Time log deleted successfully for project "+requestBody.get("projectId"));
		response.setContent("Time log deleted successfully for project "+requestBody.get("projectId"));
		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}

	@GetMapping(value = "/get_todays_tasktimelog_for_employee", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> readTodaysTimelog(@RequestParam("organizationEmailId") String organizationEmailId) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).readTodaysTimelog(organizationEmailId));
		
		ResponseModel response = new ResponseModel();
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		
		Timelog todaysTimelog = null;
		
		try {
			
			if(employeeRepository.findByEmailIdCaseSensitive(organizationEmailId) != null) {
				
				Employee employee = employeeRepository.findByEmailIdCaseSensitive(organizationEmailId);
				
				java.sql.Date todayDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
				String strDate = dateFormat.format(todayDate);  
				
				todaysTimelog = timelogRepository.findByEmployeeId(employee.getEmployeeId(), strDate);
				
			}else {
				response.setStatus(HttpStatus.EXPECTATION_FAILED);
				response.setMessage("Not able to find employee with " + organizationEmailId);
				response.setContent("Not able to find employee with " + organizationEmailId);
				return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
						HttpStatus.EXPECTATION_FAILED);
			}
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while getting today's timelog");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}
		
		if(todaysTimelog != null) {
			response.setMessage("Todays timelog for "+organizationEmailId+" : "+todaysTimelog);
			response.setContent("Todays timelog for "+organizationEmailId+" : "+todaysTimelog);
			response.setStatus(HttpStatus.OK);
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
		}else {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Not able to find timelog entry for " + organizationEmailId);
			response.setContent("Not able to find timelog entry for " + organizationEmailId);
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	@GetMapping(value = "/get_timelog_between_days_for_employee", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> readTimelogForEmployee(@RequestParam("employeeId") String employeeId, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(this.getClass()).readTimelogForEmployee(employeeId, startDate, endDate));
		ResponseModel response = new ResponseModel();

		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		
		List<Timelog> timelogList = new ArrayList<>();

		try {

			Employee employee = employeeRepository.findByEmployeeId(employeeId);
			if (employee != null) {
				
				timelogList = (List<Timelog>) timelogRepository.findTimelogofEmployeeIdBetweenDates(employeeId, startDate, endDate);
			}
			
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while getting timelog of employee");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}
		
		if(!timelogList.isEmpty()) {
			response.setMessage("Timelog for employee");
			response.setStatus(HttpStatus.OK);
			response.setContent(timelogList);

			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
		}else {
			response.setMessage("Timelog not available for employee "+employeeId+" between "+startDate+" and "+endDate);
			response.setStatus(HttpStatus.OK);
			response.setContent(timelogList);

			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
		}
		
	}
	
	@GetMapping(value = "/get_todays_timelog_entry", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> getTodaysTimelogEntry() {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getTodaysTimelogEntry());

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());

		List<Timelog> timelogList = new ArrayList<>();
		
		try {
			
			java.sql.Date todayDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
			String strDate = dateFormat.format(todayDate);
			
			timelogList = (List<Timelog>) timelogRepository.findTodaysTimelogEntry(strDate);

		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while getting today's timelog");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}

		if(!timelogList.isEmpty()) {
			response.setStatus(HttpStatus.OK);
			response.setMessage("Time log entries for today : "+timelogList);
			response.setContent(timelogList);
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
		}else {
			response.setStatus(HttpStatus.OK);
			response.setMessage("Not able to find timelog entry for today");
			response.setContent("Not able to find timelog entry for today");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
		}
	}
	
	@PostMapping(value = "/approvetimelog", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> approveTimelog(@RequestBody Map<String, String> requestBody) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).approveTimelog(requestBody));

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());

		Employee employee = new Employee();
		Timelog timelog = new Timelog();
		
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		try {
			employee = employeeRepository.findByEmployeeId(requestBody.get("employeeId"));
			timelog = timelogRepository.findByEmployeeId(requestBody.get("employeeId"), requestBody.get("day"));
			
			if(employee!=null && timelog!=null) {
				
				timelog.setApprovedHrs(Double.parseDouble(requestBody.get("approvedHrs")));
				timelog.setApproversComment(requestBody.get("comment"));
				
				timelogRepository.save(timelog);
				
			}else {
				response.setStatus(HttpStatus.EXPECTATION_FAILED);
				response.setMessage("Not able to update time log status of " + requestBody.get("employeeId") + requestBody.get("day"));
				response.setContent("Not able to update time log status " + requestBody.get("employeeId") + requestBody.get("day"));
				return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
						HttpStatus.EXPECTATION_FAILED);
			}
			
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while approving timelog");
			
			//Throwables.getStackTraceAsString(ex);
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}

		response.setStatus(HttpStatus.OK);
		response.setMessage("Timelog for project "+requestBody.get("projectId")+" approved successfully");
		response.setContent("Timelog for project "+requestBody.get("projectId")+" approved successfully");
		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}
}
