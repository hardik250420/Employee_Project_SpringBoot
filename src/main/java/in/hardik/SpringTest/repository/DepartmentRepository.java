package in.hardik.SpringTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.hardik.SpringTest.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

}
 