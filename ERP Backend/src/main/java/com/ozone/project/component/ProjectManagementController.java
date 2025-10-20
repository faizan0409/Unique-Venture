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
import com.ozone.application.utils.ApplicationConstant;
import com.ozone.application.utils.JSONUtils;
import com.ozone.application.utils.NumberUtils;
import com.ozone.component.datamodel.Clients;
import com.ozone.component.datamodel.Employee;
import com.ozone.component.datamodel.Project;
import com.ozone.component.datamodel.ProjectMap;
import com.ozone.response.component.model.ResponseModel;
import com.ozone.roles.compoent.RoleRepository;
import com.ozone.user.component.ClientRepository;
import com.ozone.user.component.EmployeeRepository;

@Controller
@RequestMapping("/projects")
public class ProjectManagementController {

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private ProjectMapRepository projectMapRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private TaskMapRepository taskMapRepository;
	
	@PostMapping(value = "/saveproject", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
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
		String notificationMsg = "";
		boolean editProjectStatus = false;
		//Project createdProject = new Project();

		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		try {
		
			Project existingProject = projectRepository.findByProjectId(requestBody.get("projectId"));
					
			if(existingProject!=null) {
				project = existingProject;
				//project.setUpdatedDatetime(new java.sql.Date(new Date().getTime()));
				project.setUpdatedBy(requestBody.get("updatedBy"));
				notificationMsg = " Project details updated for project " + project.getProjectId();
				
				editProjectStatus = true;
			}else {
				
				//create a project id
				Project maxProject = projectRepository.findMaxProjectId();
				
				if(maxProject == null) {
					maxProject = new Project();
					maxProject.setProjectId("000");
				}
				
				String newEmployeeId = maxProject.getProjectId().substring(1);
				
				if(requestBody.get("projectType").equals("Cost")) {
					project.setProjectId(ApplicationConstant.COST_PROJECT_TYPE + NumberUtils.padZeroes(Integer.parseInt(newEmployeeId)+1));
				}else {
					project.setProjectId(ApplicationConstant.REVENUE_PROJECT_TYPE + NumberUtils.padZeroes(Integer.parseInt(newEmployeeId)+1));
				}
	
				project.setManagerId(requestBody.get("managerId"));
				
				/*//set client id
				Clients client = clientRepository.findByClientName(requestBody.get("clientName"));
				
				if(client!=null) {
					project.setClientId(client.getClientId());
				}else {
					response.setStatus(HttpStatus.EXPECTATION_FAILED);
					response.setMessage("Not able to assign client to project");
					response.setContent("Not able to assign client to project");
					return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
							HttpStatus.EXPECTATION_FAILED);
				}
				*/
				//project.setCreatedDatetime(new java.sql.Date(new Date().getTime()));
				project.setCreatedBy(requestBody.get("createdBy"));
			}
			
			// set project data
			project.setProjectTitle(requestBody.get("projectTitle"));
			project.setProjectDescription(requestBody.get("projectDescription"));
			project.setManagerId(requestBody.get("managerId"));
			project.setProjectStartDate(requestBody.get("projectStartDate"));
			project.setProjectEndDate(requestBody.get("projectEndDate"));
			project.setClientId(requestBody.get("clientId"));
			project.setProjectType(requestBody.get("projectType"));
			project.setProjectStatus(requestBody.get("projectStatus"));
			 
			projectRepository.save(project);
			
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while creating project");
			Throwables.getStackTraceAsString(ex);
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}

		response.setStatus(HttpStatus.OK);
		response.setMessage("Project created successfully");
		response.setContent(project);
		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}

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

		Project project = projectRepository.findByProjectId(requestBody.get("projectId"));

		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		try {

			Collection<ProjectMap> projectMapCollection = (Collection<ProjectMap>)projectMapRepository.findByProjectId(project.getProjectId());
			
			if(projectMapCollection.size()==0) {
				if (project != null) {
					
					project.setDeleteStatus(true);
					projectRepository.save(project);
				/*	projectMapRepository.upadteDeleteStatusByProjectId(project.getId());
					taskMapRepository.upadteDeleteStatusByProjectId(project.getProjectId());
					*/
				}else {
					response.setStatus(HttpStatus.OK);
					response.setMessage("Unable to find project details");
					response.setContent(project);
					return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
				}
			}else {
				
				response.setStatus(HttpStatus.OK);
				response.setMessage("Unable to delete a project as it is assigned to an employee");
				response.setContent(project);
				return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
			}
			

		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while deleting project");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}

		response.setStatus(HttpStatus.OK);
		response.setMessage("Project deleted successfully");
		response.setContent(project);
		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}

	@GetMapping(value = "/getprojects", produces = { MediaType.APPLICATION_JSON_VALUE })
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
		
		List<Project> projectList = new ArrayList<>();
		
		try {
			if (projectRepository.findProjects() != null) {
				
				projectList = (List<Project>)projectRepository.findProjects();
			} 
			
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while getting project list");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}
		response.setMessage("Project list");
		response.setStatus(HttpStatus.OK);
		
		if(!projectList.isEmpty()) {
			response.setContent(projectList);
		}else {
			response.setContent(null);
		}
		
		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getemployeesforproject", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> getEmployeesForProject(String projectId) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getEmployeesForProject(projectId));
		
		ResponseModel response = new ResponseModel();
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		
		List<Employee> employeeList = new ArrayList<>();
		
		try {
			if (projectRepository.findByProjectId(projectId) != null) {
				
				employeeList = (List<Employee>) projectMapRepository.findEmployeesForProject(projectId);
				
				if(employeeList == null || employeeList.isEmpty()) {
					response.setStatus(HttpStatus.EXPECTATION_FAILED);
					response.setMessage("Unable to find employees for project");
					return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
							HttpStatus.EXPECTATION_FAILED);
				}
			} 
			
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while getting employees assigned for project");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}
		response.setMessage("Employee list");
		response.setStatus(HttpStatus.OK);
		
		if(!employeeList.isEmpty()) {
			response.setContent(employeeList);
		}else {
			response.setContent(null);
		}
		
		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}

	@GetMapping(value = "/getprojectsforemployee", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> readProjectInformationForEmployee(@RequestParam String employeeId) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(this.getClass()).readProjectInformationForEmployee(employeeId));
		ResponseModel response = new ResponseModel();

		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		
		List<Project> projectList = new ArrayList<>();
		//List<Category> categoryList = new ArrayList<>();

		try {
			int id = -1;

			Employee employee = employeeRepository.findByEmployeeId(employeeId);
			if (employee != null) {
				id = employee.getId();
			}

			if (id != -1 && !((Collection<Project>) projectRepository.findProjectByEmployeeId(employeeId)).isEmpty()) {
				projectList = (List<Project>) projectRepository.findProjectByEmployeeId(employeeId);
			}

		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while getting projects assigned to employee");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}
		response.setMessage("Project list for employee");
		response.setStatus(HttpStatus.OK);
		response.setContent(projectList);

		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}
	
	@PostMapping(value = "/assignproject", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> assignProjectToEmployee(@RequestBody Map<String, String> requestBody) {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).assignProjectToEmployee(requestBody));

		ResponseModel response = new ResponseModel();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());

		Project project = projectRepository.findByProjectId(requestBody.get("projectId"));
		
		List<String> employeeList = JSONUtils.getListFromJsonArray(requestBody.get("employeeList").toString());

		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		try {
			
			// Assign project to employees
			for(String employeeId : employeeList) {
				
				Employee employee = employeeRepository.findByEmployeeId(employeeId);
				if (!employee.isDeleteStatus() && !project.isDeleteStatus()) {
					ProjectMap projectmap = new ProjectMap();
					projectmap.setProjectId(project.getProjectId());
					projectmap.setEmployeeId(employee.getEmployeeId());
					//projectmap.setCreatedDatetime(new java.sql.Date(new Date().getTime()));
					projectmap.setCreatedBy(requestBody.get("updatedBy"));
					projectmap.setJobRoleId(roleRepository.findByRoleName(requestBody.get("roleName")).getId());
					 
					projectMapRepository.save(projectmap);

				} else {
					response.setStatus(HttpStatus.EXPECTATION_FAILED);
					response.setMessage("Inavalid data for mapping project and employee, check project status and employee status");
					response.setContent(project);
					return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
				}
			}
			
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while assigning project to employee");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}

		response.setStatus(HttpStatus.OK);
		response.setMessage(
				"Project" + project.getProjectTitle() + " assigned successfully to employees");
		response.setContent(project);
		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getprojectIds", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<Resource<ResponseModel>> readProjectIds() {
		final LinkBuilder selfLinkBuilder = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).readProjectIds());
		ResponseModel response = new ResponseModel();
		final Resource<ResponseModel> entityResource = new Resource<ResponseModel>(response,
				selfLinkBuilder.withSelfRel());
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(selfLinkBuilder.toUri());
		List<String> projectList = new ArrayList<>();
		
		try {
			if (projectRepository.findProjectIds() != null) {
				projectList = (List<String>)projectRepository.findProjectIds();

			}
		} catch (Exception ex) {
			response.setStatus(HttpStatus.EXPECTATION_FAILED);
			response.setMessage("Error while getting project IDs");
			return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders,
					HttpStatus.EXPECTATION_FAILED);
		}
		response.setMessage("ProjectId list");
		response.setStatus(HttpStatus.OK);
		
		if(!projectList.isEmpty()) {
			response.setContent(projectList);
		}else {
			response.setContent(null);
		}
		
		return new ResponseEntity<Resource<ResponseModel>>(entityResource, httpHeaders, HttpStatus.OK);
	}
}
