package in.hardik.SpringTest.model;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import in.hardik.SpringTest.request.EmployeeRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_employee")
public class Employee {
	
	//JsonProperty - alias ex full_name
	//JsonIgnore - ignores that value ex age
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
//	@NotBlank(message = "name should not be null")
	private String name;
	
	@Column(name = "age")
	private Long age = 0L;
	
	@Column(name = "location")
	private String location;
	
	@Email
	@Column(name = "email")
	private String email;

	@CreationTimestamp
	@Column(name = "created_at" , nullable = false , updatable = false)
	private String createdAt;
	
	@UpdateTimestamp
	@Column(name = "updated_at")
	private String updatedAt;
	
	@JoinColumn(name = "department_id")
	@OneToOne
	private Department department;
	
	public Employee() {
		
	}
	
	public Employee(EmployeeRequest req) {
		this.name = req.getName();
		this.age = req.getAge();
		this.email = req.getEmail();
		this.location = req.getLocation();
	}

}
