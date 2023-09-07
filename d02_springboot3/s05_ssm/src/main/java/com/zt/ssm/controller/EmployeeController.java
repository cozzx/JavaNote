package com.zt.ssm.controller;

import com.zt.ssm.bean.Employee;
import com.zt.ssm.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zt
 * @since 2023/8/30 17:18
 **/
@RestController
public class EmployeeController {

    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable("id") Long id) {
        Employee employee = employeeMapper.getEmployee(id);
        return employee;
    }
}
