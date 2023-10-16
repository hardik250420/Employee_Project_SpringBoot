package in.hardik.SpringTest.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.hardik.SpringTest.model.Department;
import in.hardik.SpringTest.model.Employee;
import in.hardik.SpringTest.repository.DepartmentRepository;
import in.hardik.SpringTest.repository.EmployeeRepository;
import in.hardik.SpringTest.request.EmployeeRequest;
import in.hardik.SpringTest.service.EmployeeService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//@Controller   //server will send htpp request

@RestController // @controller + @responsebody
public class EmployeeController {
   
	//@RequestMapping(value = "/employees" , method = RequestMethod.GET)
	
	@Autowired
	private EmployeeService eService;
	
	@Autowired
	private EmployeeRepository eRepo;
	
	@Autowired
	private DepartmentRepository dRepo;
	
	@Value("${app.name: Employee Tracker}")
	private String appName;
	
	@Value("${app.version: version 1}")
	private String appVersion;
	
	@GetMapping("/version")
	public String getAppDetails () {
		return appName+"-"+appVersion;
	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getEmployees() {
		return new ResponseEntity<List<Employee>>(eService.getEmployees(), HttpStatus.OK);
	}
	
	@GetMapping("/employees/{id}")
	public Employee getEmployee(@PathVariable("id") Long id) {
		return eService.getSingleEmployee(id);
	}
	
	@PostMapping("/employees")
	public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody EmployeeRequest eRequest) {
		Department dept = new Department();
		dept.setName(eRequest.getDepartment());
		
		dept = dRepo.save(dept);
		
		Employee employee = new Employee(eRequest);
		employee.setDepartment(dept);
		
		return new ResponseEntity<Employee>(eRepo.save(employee) , HttpStatus.CREATED);
		
	}
	
	@PutMapping("/employees/{id}")
	public Employee updateEmployee(@PathVariable("id") Long id,@RequestBody Employee employee) {
		//System.out.println("Updating the details of the employee "+id);
		employee.setId(id);
		return eService.updateEmployee(employee);
	}
	
	@DeleteMapping("/employees")
	public void deleteEmployee(@RequestParam("id") Long id) {
		 eService.deleteEmployee(id);
	 }
	 
	@GetMapping("/employees/filterByName")
	public ResponseEntity<List<Employee>> getEmployeesByName(@RequestParam String name) {
	  return new ResponseEntity<List<Employee>>(eService.getEmployeesByName(name) , HttpStatus.OK);	
	}
	
	@GetMapping("/employees/filterByNameAndLocation")
	public ResponseEntity<List<Employee>> getEmployeesByNameAndLocation(@RequestParam String name , @RequestParam String location) {
	  return new ResponseEntity<List<Employee>>(eService.getEmployeesByNameAndLocation(name , location) , HttpStatus.OK);	
	}
	
	@GetMapping("/employees/filterByKeyword")
	public ResponseEntity<List<Employee>> getEmployeesByKeyword(@RequestParam String name) {
	  return new ResponseEntity<List<Employee>>(eService.getEmployeesByKeyword(name) , HttpStatus.OK);	
	}
	
	@DeleteMapping("/employees/delete/{name}")
	public ResponseEntity<String> deleteEmployeesByName(@PathVariable String name) {
	  return new ResponseEntity<String>(eService.deleteByEmployeeName(name)+ "No of records deleted " , HttpStatus.OK);	
	}
	
	@GetMapping("/employees/filter/{name}")
	public ResponseEntity<List<Employee>> getEmployeeByDepartment(@PathVariable String name) {
		return new ResponseEntity<List<Employee>>(eRepo.getEmployeesByDeptName(name), HttpStatus.OK);
	}
	
}
