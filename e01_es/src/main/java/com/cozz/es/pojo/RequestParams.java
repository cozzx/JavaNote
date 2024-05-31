package com.cozz.es.pojo;

import lombok.Data;

/**
 * @author zt
 * @date 2024/4/4
 */
@Data
public class RequestParams {
    private String key;
    private Integer page;
    private Integer size;
    private String sortBy;
    private String brand;
    private String city;
    private String starName;
    private Integer minPrice;
    private Integer maxPrice;
    private String location;
}
