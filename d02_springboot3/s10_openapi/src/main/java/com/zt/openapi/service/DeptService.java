package com.zt.openapi.service;

import com.zt.openapi.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zt
 * @since 2023/9/4 19:55
 **/
@Service
public class DeptService {

    Map<Long, Employee> data = new ConcurrentHashMap<Long, Employee>();

    public void deleteDept(Long id) {
        data.remove(id);
    }

    public void saveDept(Employee dept) {
        data.put(dept.getId(), dept);
    }

    public List<Employee> getDepts() {
        return data.values().stream().toList();
    }

    public Employee getDeptById(Long id) {
        return data.get(id);
    }
}
