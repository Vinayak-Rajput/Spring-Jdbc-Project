package com.hdfc.mapper;

import com.hdfc.dto.EmployeeRequestDto;
import org.springframework.stereotype.Component;

import com.hdfc.dto.EmployeeResponseDto;
import com.hdfc.entity.Employee;

import java.util.List;

@Component
public class EmployeeDtoMapper {
	
	public EmployeeResponseDto toDto(Employee emp) {
		return new EmployeeResponseDto(emp.getEmpId(),emp.getEmpName());;
	}

	public Employee toEntity(EmployeeRequestDto emp) {
		return new Employee(emp.getEmpId(),emp.getEmpName(), emp.getEmail(), emp.getAddress(), emp.getSalary());
	}

	public List<EmployeeResponseDto> toDto(List<Employee> employees) {
		return employees.stream()
				.map(new EmployeeDtoMapper()::toDto)
				.toList();
	}

}
