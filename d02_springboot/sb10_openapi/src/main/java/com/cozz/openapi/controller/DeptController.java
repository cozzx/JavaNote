package com.cozz.openapi.controller;

import com.cozz.openapi.entity.Employee;
import com.cozz.openapi.service.DeptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zt
 * @date 2023/9/4
 **/
@RestController
@Tag(name = "部门", description = "部门的CRUD")
public class DeptController {

    @Autowired
    DeptService deptService;

    @Operation(summary = "查询所有部门")
    @GetMapping("/depts")
    public List<Employee> getDept() {
        return deptService.getDepts();
    }

    @GetMapping("/dept/{id}")
    @Operation(summary = "查询", description = "按照id查询部门")
    public Employee getDept(@PathVariable("id") @Parameter(description = "部门id") Long id) {
        return deptService.getDeptById(id);
    }

    @PostMapping("/dept")
    @Operation(summary = "保存部门", description = "必须提交json")
    public String saveDept(@RequestBody Employee dept) {
        deptService.saveDept(dept);
        return "ok";
    }

    @DeleteMapping("/dept/{id}")
    @Operation(summary = "按照id删除部门", description = "必须提交部门id")
    public String deleteDept(@PathVariable("id") @Parameter(description = "部门id") Long id) {
        deptService.deleteDept(id);
        return "ok";
    }
}
