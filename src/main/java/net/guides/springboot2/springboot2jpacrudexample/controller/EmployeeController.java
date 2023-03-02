package net.guides.springboot2.springboot2jpacrudexample.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.guides.springboot2.springboot2jpacrudexample.exception.ResourceNotFoundException;
import net.guides.springboot2.springboot2jpacrudexample.model.Employee;
import net.guides.springboot2.springboot2jpacrudexample.repository.EmployeeRepository;



@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @GetMapping("/employees")
    public List<Employee> getAllEmployees()
    {
		return employeeRepository.findAll();
    }
    
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value ="id") long id) 
    		throws ResourceNotFoundException
    {
		Employee emp=employeeRepository.findById(id).
				orElseThrow(()-> new ResourceNotFoundException("Employee not found for this id"+id));
		return ResponseEntity.ok().body(emp);
     }
    
    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id") long id,
    		@Valid @RequestBody Employee employeeDetails
    		) throws ResourceNotFoundException
    {
    	Employee emp=employeeRepository.findById(id)
    			.orElseThrow(()-> new ResourceNotFoundException("The employee not found for this id"+id));
    	emp.setFirstName(employeeDetails.getFirstName());
    	emp.setLastName(employeeDetails.getLastName());
    	emp.setEmailId(employeeDetails.getEmailId());
    	
    	final Employee updatedEmployee=employeeRepository.save(emp);
		return ResponseEntity.ok(updatedEmployee);
    	
    }
    
    @PostMapping("/employees")
    public Employee saveEmployee(@Valid @RequestBody Employee emp)
    {
		return employeeRepository.save(emp);
    	
    }
    
    @DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value="id") long id) throws ResourceNotFoundException
    {
    	
    	Employee emp=employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("The employee not found"));
    	
    	employeeRepository.delete(emp);
    	
    	Map<String, Boolean> response = new HashMap<>();
    	
    	response.put("deleted", true);
    	return response; 	
    }
    
    @PatchMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployeeUsingPatch(@PathVariable(value ="id") long id,  @Valid @RequestBody Employee empDetails) 
    		throws ResourceNotFoundException
    {
    	Employee emp=employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not found"+id));
    	
    	emp.setFirstName(empDetails.getFirstName());
    	
    	final Employee updatedEmployee=employeeRepository.save(emp);  
    	
		return ResponseEntity.ok(updatedEmployee);
    }
 
}