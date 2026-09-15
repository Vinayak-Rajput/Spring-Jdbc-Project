package com.hdfc.dto;

// import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDto {

    @Id
    private Integer empId;

    private String empName;

    private String email;

    private String address;

    private Double salary;
}
