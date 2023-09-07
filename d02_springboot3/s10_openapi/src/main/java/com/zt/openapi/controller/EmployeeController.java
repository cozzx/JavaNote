package com.zt.openapi.controller;

import com.zt.openapi.entity.Dept;
import com.zt.openapi.service.EmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zt
 * @since 2023/9/4 19:55
 **/
@Tag(name = "员工", description = "员工CRUD")
@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/emp/{id}")
    public Dept getEmployee(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/emps")
    public List<Dept> getEmployee() {
        return employeeService.getEmployees();
    }

    @PostMapping("/emp")
    public String saveEmployee(@RequestBody Dept employee) {
        employeeService.saveEmployee(employee);
        return "ok";
    }

    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return "ok";
    }
}
