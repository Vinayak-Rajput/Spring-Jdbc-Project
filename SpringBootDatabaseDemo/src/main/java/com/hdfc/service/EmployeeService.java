package com.hdfc.service;

import com.hdfc.dto.EmployeeRequestDto;
import com.hdfc.dto.EmployeeResponseDto;
import com.hdfc.entity.Employee;
import com.hdfc.exception.EmployeeAlreadyExistException;
import com.hdfc.exception.EmployeeNotFoundException;
import com.hdfc.mapper.EmployeeDtoMapper;
import com.hdfc.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    EmployeeRepository employeeRepository;
    EmployeeDtoMapper employeeDtoMapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeDtoMapper employeeDtoMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeDtoMapper = employeeDtoMapper;
    }

    public Employee create(EmployeeRequestDto emp) {

        if(employeeRepository.existsById(emp.getEmpId())) {
            throw new EmployeeAlreadyExistException("Employee with same ID already exists.");
        }

        return employeeRepository.save(employeeDtoMapper.toEntity(emp));
    }

    public EmployeeResponseDto findById(Integer empId) {

        if(employeeRepository.existsById(empId)) {
            return employeeDtoMapper.toDto(employeeRepository.findById(empId).get());
        }

        throw new EmployeeNotFoundException("Employee with ID "+ empId + " does not exist.");
    }

    public List<EmployeeResponseDto> getAllEmployees() {

        return employeeDtoMapper.toDto(employeeRepository.findAll());
    }

    public EmployeeResponseDto updateEmployee(Integer empId, EmployeeRequestDto emp) {

        if(employeeRepository.existsById(empId)) {
            employeeRepository.save(employeeDtoMapper.toEntity(emp));
        }

        throw new EmployeeNotFoundException("Employee with ID "+ empId + " does not exist.");
    }

    public EmployeeResponseDto deleteEmployee(Integer empId) {

        if(employeeRepository.existsById(empId)) {
            employeeRepository.deleteById(empId);
        }

        throw new EmployeeNotFoundException("Employee with ID "+ empId + " does not exist.");
    }

    public long countAllEmployees() {

        return employeeRepository.count();
    }
}
