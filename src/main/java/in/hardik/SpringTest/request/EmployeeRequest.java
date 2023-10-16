package in.hardik.SpringTest.request;

import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
public class EmployeeRequest {
	
	private String name;
	
	private String department;
	
	private String email;
	
	private String location;
	
	private Long age;
	
}
