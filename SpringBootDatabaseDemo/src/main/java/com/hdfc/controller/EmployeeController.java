package com.hdfc.controller;

import java.util.List;

import com.hdfc.dto.EmployeeRequestDto;
import com.hdfc.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hdfc.dto.EmployeeResponseDto;
import com.hdfc.entity.Employee;
import com.hdfc.mapper.EmployeeDtoMapper;
import com.hdfc.repository.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private EmployeeService empService;
	private EmployeeDtoMapper empMapper;

	public EmployeeController(EmployeeService empService, EmployeeDtoMapper empMapper) {
		super();
		this.empService = empService;
		this.empMapper = empMapper;
	}
	
	@PostMapping
	public ResponseEntity<EmployeeResponseDto> create(@RequestBody EmployeeRequestDto emp){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(empMapper.toDto(empService.create(emp)));
	}
	
	@GetMapping("/{empId}")
	public ResponseEntity<EmployeeResponseDto> getEmployeeDetailsById(@PathVariable Integer empId){
		return ResponseEntity.status(HttpStatus.OK)
				.body(empService.findById(empId));
	}
	
	@GetMapping
	public ResponseEntity<List<EmployeeResponseDto>> getAll(){
		return ResponseEntity.status(HttpStatus.OK)
				.body(empService.getAllEmployees());
	}

	@PutMapping("/{empId}")
	public ResponseEntity<EmployeeResponseDto> update(@PathVariable Integer empId,@RequestBody EmployeeRequestDto emp){
		return ResponseEntity.status(HttpStatus.OK)
				.body(empService.updateEmployee(empId,emp));
	}

	@DeleteMapping("/{empId}")
	public ResponseEntity<EmployeeResponseDto> delete(@PathVariable Integer empId){
		return ResponseEntity.status(HttpStatus.NO_CONTENT)
				.body(empService.deleteEmployee(empId));
	}

	@GetMapping("/count")
	public ResponseEntity<Long> countAllEmployees(){
		return ResponseEntity.status(HttpStatus.OK)
				.body(empService.countAllEmployees());
	}

}
