package com.zt.openapi.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zt
 * @since 2023/9/4 19:56
 **/
@Data
@Schema(title = "部门信息")
public class Dept {

    @Schema(title = "部门id")
    private Long id;
    @Schema(title = "部门名字")
    private String deptName;
}
