package com.hdfc.mapper;

import org.springframework.stereotype.Component;

import com.hdfc.dto.EmployeeResponseDto;
import com.hdfc.entity.Employee;

@Component
public class EmployeeDtoMapper {
	
	public EmployeeResponseDto toDto(Employee emp) {
		EmployeeResponseDto dto = new EmployeeResponseDto(emp.getEmpId(),emp.getEmpName());
	
		return dto;
	}

}
