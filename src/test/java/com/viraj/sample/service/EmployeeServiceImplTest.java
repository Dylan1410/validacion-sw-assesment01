package com.viraj.sample.service;

import com.viraj.sample.entity.Employee;
import com.viraj.sample.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void shouldSaveEmployee() {
        Employee employee = new Employee();
        employee.setName("Juan");
        employee.setDepartment("IT");

        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee saved = employeeService.saveEmployee(employee);

        assertNotNull(saved);
        assertEquals("Juan", saved.getName());
        verify(employeeRepository).save(employee);
    }

    @Test
    void shouldGetEmployeeById() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Maria");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Employee result = employeeService.getEmployeeById(1L);

        assertEquals("Maria", result.getName());
    }

    @Test
    void shouldThrowExceptionWhenEmployeeNotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            employeeService.getEmployeeById(1L);
        });
    }
}
