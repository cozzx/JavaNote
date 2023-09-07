package com.zt.ssm.mapper;

import com.zt.ssm.bean.Employee;
import org.apache.ibatis.annotations.Param;

/**
 * @author zt
 * @since 2023/8/30 17:04
 **/
public interface EmployeeMapper {

    Employee getEmployee(@Param("id") Long id);
}
