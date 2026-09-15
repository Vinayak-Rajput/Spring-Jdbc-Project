package com.hdfc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@GeneratedValue(
//			strategy = GenerationType.SEQUENCE,
//			generator = "emp_seq"
//	)
//	@SequenceGenerator(
//			name = " emp_seq", 
//			sequenceName = "employee_seq", 
//			initialValue =1000, 
//			allocationSize = 1
//	)
	@Column(name = "emp_id")
	private Integer empId;
	
	@Column(name = "emp_name")
	private String empName;
	
	@Column(name = "emp_email", unique = true, nullable = false)
	private String email;
	
	private String address;
	
	private Double salary;
	
}

//CREATE table employee{
//	emp_id serial primary key,
//	emp_name varchar(12),
//	emp_email varchar(15),
//	salary numeric(10,2)
//}

