package in.hardik.SpringTest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import in.hardik.SpringTest.model.Employee;
import in.hardik.SpringTest.repository.EmployeeRepository;



@Service
public class EmployeeServiceImpl implements EmployeeService {
    
	@Autowired
    private EmployeeRepository eRepository; 
    

	
	@Override
	public List<Employee> getEmployees() {
		
		return eRepository.findAll();
	}
	
	@Override
	public Employee saveEmployee (Employee employee) {
		return eRepository.save(employee);
	}
	
	@Override
	public Employee getSingleEmployee(Long id) {
		Optional<Employee> employee = eRepository.findById(id);
		if(employee.isPresent()) {
			return employee.get();
		}
		throw new RuntimeException("Employee not found with id "+id);
	}
	
	@Override
	public void deleteEmployee(Long id) {
		eRepository.deleteById(id);
	}
	
	@Override
	public Employee updateEmployee(Employee employee) {
		return eRepository.save(employee);
	}

	@Override
	public List<Employee> getEmployeesByName(String name) {
		return eRepository.findByName(name);
	}

	@Override
	public List<Employee> getEmployeesByNameAndLocation(String name, String location) {
		// TODO Auto-generated method stub
		return eRepository.findByNameAndLocation(name, location);
	}

	@Override
	public List<Employee> getEmployeesByKeyword(String keyword) {
		// TODO Auto-generated method stub
		Sort sort = Sort.by(Sort.Direction.DESC , "id");
		return eRepository.findByNameContaining(keyword , sort);
	}

	@Override
	public Integer deleteByEmployeeName(String name) {
		// TODO Auto-generated method stub
		return eRepository.deleteEmployeeByName(name);
	}



}
