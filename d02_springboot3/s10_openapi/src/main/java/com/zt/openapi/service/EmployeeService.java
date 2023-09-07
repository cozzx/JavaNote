package com.zt.openapi.service;

import com.zt.openapi.entity.Dept;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zt
 * @since 2023/9/4 19:55
 **/
@Service
public class EmployeeService {

    Map<Long, Dept> data = new ConcurrentHashMap<>();

    public Dept getEmployeeById(Long id) {
        return data.get(id);
    }

    public List<Dept> getEmployees() {
        return data.values().stream().toList();
    }

    public void saveEmployee(Dept employee) {
        data.put(employee.getId(), employee);
    }

    public void deleteEmployee(Long id) {
        data.remove(id);
    }
}
