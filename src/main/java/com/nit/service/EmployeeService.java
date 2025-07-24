package com.nit.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nit.entity.Address;
import com.nit.entity.Employee;
import com.nit.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
       try {
    	   for (Address address : employee.getAddresses()) {
               address.setEmployee(employee);
           }
           return employeeRepository.save(employee);
       }
       catch (Exception e) {
		throw new RuntimeException("Error Adding Employee");
	}
    }

    public List<Employee> getAllEmployees() {
       try {
    	   return employeeRepository.findAll();
       }
       catch (Exception e) {
		throw new RuntimeException("Error in getting Employees");
	}
    }

    public Optional<Employee> getEmployeeById(Long id) {
        try {
        	return employeeRepository.findById(id);
        }
        catch (Exception e) {
		   throw new RuntimeException("Error in getting Employee");
		}
    }

    public Boolean deleteEmployee(Long id) {
      Optional<Employee> emp=employeeRepository.findById(id);
    	
    	   if(employeeRepository.existsById(id))
    	   {
    		   employeeRepository.deleteById(id);    		   
    		   return true;
    	   }
    	   return false;
    }

    public Employee updateEmployee(Long id, Employee updatedEmp) {
        return employeeRepository.findById(id).map(emp -> {
            emp.setName(updatedEmp.getName());
            emp.setDepartment(updatedEmp.getDepartment());

            emp.getAddresses().clear();
            for (Address addr : updatedEmp.getAddresses()) {
                addr.setEmployee(emp);
                emp.getAddresses().add(addr);
            }
            return employeeRepository.save(emp);
        }).orElseThrow(()->new RuntimeException("Error in updating Employee"));
    }
}
