package com.hdfc.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdfc.dto.EmployeeResponseDto;
import com.hdfc.entity.Employee;
import com.hdfc.mapper.EmployeeDtoMapper;
import com.hdfc.repository.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private EmployeeRepository empRepo;
	private EmployeeDtoMapper empMapper;

	public EmployeeController(EmployeeRepository empRepo, EmployeeDtoMapper empMapper) {
		super();
		this.empRepo = empRepo;
		this.empMapper = empMapper;
	}
	
	@PostMapping
	public ResponseEntity<EmployeeResponseDto> create(@RequestBody Employee emp){
		return ResponseEntity.status(HttpStatus.OK)
				.body(empMapper.toDto(empRepo.save(emp)));
	}
	
	@GetMapping("/{empId}")
	public ResponseEntity<EmployeeResponseDto> getEmployeeDetailsById(@PathVariable Integer empId){
		return ResponseEntity.status(HttpStatus.OK)
				.body(empMapper.toDto(empRepo.findById(empId).get()));
	}
	
	@GetMapping
	public ResponseEntity<List<EmployeeResponseDto>> getAll(){
		
		List<Employee> employees = empRepo.findAll();
		
		List<EmployeeResponseDto> result = employees.stream().map(empMapper::toDto).toList();
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(result);
	}
}
