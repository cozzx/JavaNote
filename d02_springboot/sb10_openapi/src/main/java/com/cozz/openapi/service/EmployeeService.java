package com.cozz.openapi.service;

import com.cozz.openapi.entity.Dept;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zt
 * @date 2023/9/4
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
