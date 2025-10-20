package com.ozone.project.component;

import java.util.ArrayList;
import java.util.Collection;
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

import com.google.common.base.Throwables;
import com.ozone.application.utils.JSONUtils;
import com.ozone.component.datamodel.Employee;
import com.ozone.component.datamodel.Project;
import com.ozone.component.datamodel.TaskMap;
import com.ozone.component.datamodel.TaskTimelog;
import com.ozone.component.datamodel.Tasks;
import com.ozone.response.component.model.ResponseModel;
import com.ozone.timelog.component.TaskTimelogRepository;
import com.ozone.user.component.EmployeeRepository;

@Controller
@RequestMapping("/tasks")
public class TaskManagementController {

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private TaskMapRepository taskMapRepository;
	
	@Autowired
	private TaskTimelogRepository taskTimelogRepository;
	
	@PostMapping(value = "/savetask", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> add(@RequestBody Map<String, String> requestBody) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).add(requestBody));

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());

		Tasks createdTask = new Tasks();

		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		try {
		
			Tasks tasks = taskRepository.findTaskByNameCaseSensitive(requestBody.get("taskName"));
			
			if(tasks != null) {
			tasks.setTaskDescription(requestBody.get("taskDescription"));
			tasks.setProjectId(requestBody.get("projectId"));
			tasks.setPriority(requestBody.get("priority"));
			tasks.setStartDate(requestBody.get("startDate"));
			tasks.setEndDate(requestBody.get("endDate"));
			tasks.setTaskStatus(requestBody.get("taskStatus"));
			tasks.setCompanyId(requestBody.get("companyId"));
			tasks.setDeleteStatus(false);
			tasks.setCreatedBy(requestBody.get("createdBy"));
			System.out.println("Task Body"+tasks);
			createdTask = taskRepository.save(tasks);
				response.setStatus(HttpStatus.OK);
				response.setMessage("Task Updated Successfully");
				response.setContent(tasks);
				return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
			}
			tasks = new Tasks();
			tasks.setTaskName(requestBody.get("taskName"));
			tasks.setTaskDescription(requestBody.get("taskDescription"));
			tasks.setProjectId(requestBody.get("projectId"));
			tasks.setPriority(requestBody.get("priority"));
			tasks.setStartDate(requestBody.get("startDate"));
			tasks.setEndDate(requestBody.get("endDate"));
			tasks.setTaskStatus(requestBody.get("taskStatus"));
			tasks.setCompanyId(requestBody.get("companyId"));
			tasks.setDeleteStatus(false);
			tasks.setCreatedBy(requestBody.get("createdBy"));
			System.out.println("Task Body"+tasks);
			createdTask = taskRepository.save(tasks);

		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while creating task");
			Throwables.getStackTraceAsString(ex);
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}

		response.setStatus(HttpStatus.OK);
		response.setMessage("Task created successfully");
		response.setContent(createdTask);
		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}

	@PostMapping(value = "/deletetask", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> deleteTask(@RequestBody Map<String, String> requestBody) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).deleteTask(requestBody));

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());

		Tasks task = taskRepository.findTaskByNameCaseSensitive(requestBody.get("taskName"));

		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		try {

			Collection<TaskTimelog> taskTimelogCollection = (Collection<TaskTimelog>)taskTimelogRepository.findTaskTimelogByTaskName(requestBody.get("taskName"));
			
			if(taskTimelogCollection.size()==0) {
				if (task != null) {
					
					TaskMap taskMap = taskMapRepository.findTaskById(task.getId());
					
					if(taskMap != null) {
						response.setStatus(HttpStatus.OK);
						response.setMessage("Task can not be deleted as it is assigned to employee for project work");
						response.setContent(task);
						return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
					}
					
					task.setDeleteStatus(true);
					taskRepository.save(task);
					taskMapRepository.upadteDeleteStatusByTaskId(task.getId());
				}
			}else {
				response.setStatus(HttpStatus.OK);
				response.setMessage("Task can not be deleted as timelog added by employee against the task");
				response.setContent(task);
				return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
			}
			

		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while deleting task");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}

		response.setStatus(HttpStatus.OK);
		response.setMessage("Task deleted successfully");
		response.setContent(task);
		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}

	@GetMapping(value = "/gettasks", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> readProjectInformation() {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).readProjectInformation());
		
		ResponseModel response = new ResponseModel();
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		
		List<Tasks> taskList = new ArrayList<>();
		
		try {
			if (taskRepository.findTasks() != null) {
				
				taskList = (List<Tasks>)taskRepository.findTasks();
			} 
			
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while getting task list");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}
		response.setMessage("Task List");
		response.setStatus(HttpStatus.OK);
		
		if(!taskList.isEmpty()) {
			response.setContent(taskList);
		}else {
			response.setContent(null);
		}
		
		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}

	@GetMapping(value = "/gettasksforemployee", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> readProjectInformationForEmployee(@RequestParam String organizationEmailId) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(this.getClass()).readProjectInformationForEmployee(organizationEmailId));
		ResponseModel response = new ResponseModel();

		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		
		List<Tasks> taskList = new ArrayList<>();

		try {
			int id = -1;

				Employee employee = employeeRepository.findByEmailId(organizationEmailId);
				if (employee != null) {
					id = employee.getId();
				}
			 
				if (id != -1 && !((Collection<Tasks>)taskMapRepository.findTasksForEmployee(employee.getEmployeeId())).isEmpty()) {
					taskList = (List<Tasks>)taskMapRepository.findTasksForEmployee(employee.getEmployeeId());
				}
				
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while getting tasks assigned to employee");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}
		response.setMessage("Task list for employee");
		response.setStatus(HttpStatus.OK);
		response.setContent(taskList);

		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}
	

	@PostMapping(value = "/assigntask", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> assignTaskToEmployee(@RequestBody Map<String, String> requestBody) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).assignTaskToEmployee(requestBody));

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());

		Project project = projectRepository.findByProjectId(requestBody.get("projectId"));
		List<String> taskList = JSONUtils.getListFromJsonArray(requestBody.get("taskList"));
		List<String> employeeIdList = JSONUtils.getListFromJsonArray(requestBody.get("employeeList"));

		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		try {
			for(String taskName : taskList) {
				Tasks task = taskRepository.findTaskByNameCaseSensitive(taskName);
				
				if(task != null) {
					// Assign task to employee
					for(String employeeId : employeeIdList) {
						
						Employee employee = employeeRepository.findByEmployeeId(employeeId);
						
						if (!employee.isDeleteStatus() && !project.isDeleteStatus()) {
							TaskMap taskMap = new TaskMap();
							taskMap.setTaskId(task.getId());
							taskMap.setProjectId(project.getProjectId());
							taskMap.setEmployeeId(employee.getEmployeeId());

							taskMapRepository.save(taskMap);
						} else {
							response.setStatus(HttpStatus.EXPECTATION_FAILED);
							response.setMessage("Inavalid data for mapping project and employee, check project status and employee status");
							response.setContent(project);
							return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
						}
					}
				}else {
					response.setStatus(HttpStatus.EXPECTATION_FAILED);
					response.setMessage("No task is available with name "+taskName);
					response.setContent(project);
					return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);

				}
			}
			
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while assigning tasks to employee");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}

		response.setStatus(HttpStatus.OK);
		response.setMessage("Task assigned successfully to employees for given project "+project.getProjectId());
		response.setContent(project);
		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getprojectbasedtasks", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> readProjectBasedTasks(@RequestParam("projectId") String projectId, @RequestParam("employeeId") String employeeId) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).readProjectBasedTasks(projectId, employeeId));
		ResponseModel response = new ResponseModel();
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		
		List<Tasks> taskList = new ArrayList<>();
		
		try {
			if (projectRepository.findByProjectId(projectId) != null && 
					employeeRepository.findByEmployeeId(employeeId)  != null) {
				
				taskList = (List<Tasks>)taskMapRepository.findProjectBasedTasksForEmployee(employeeId,projectId);
			}
			
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while getting tasks");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}
		response.setMessage("Task list of employee based on project");
		response.setStatus(HttpStatus.OK);
		
		if(!taskList.isEmpty()) {
			response.setContent(taskList);
		}else {
			response.setContent(null);
		}
		
		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}
}
