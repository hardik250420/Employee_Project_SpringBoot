package in.hardik.SpringTest.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.hardik.SpringTest.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
     
	List<Employee> findByName(String name);
	
	// select * from table where name="ram" and location="india"
	List<Employee> findByNameAndLocation(String name,String location);
	
	// select * from table where name like "%ram%"
	List<Employee> findByNameContaining(String keyword , Sort sort);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Employee WHERE name = :name")
	Integer deleteEmployeeByName(String name);
    
	@Query("FROM Employee WHERE department.name = :name")
	List<Employee> getEmployeesByDeptName(String name);
}

