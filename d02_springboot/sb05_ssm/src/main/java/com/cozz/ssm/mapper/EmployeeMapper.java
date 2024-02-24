package com.cozz.ssm.mapper;

import com.cozz.ssm.bean.Employee;
import org.apache.ibatis.annotations.Param;

/**
 * @author zt
 * @date 2023/8/30
 **/
public interface EmployeeMapper {

    Employee getEmployee(@Param("id") Long id);
}
