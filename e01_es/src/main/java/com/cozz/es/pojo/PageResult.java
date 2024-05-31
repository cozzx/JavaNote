package com.cozz.es.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author zt
 * @date 2024/4/4
 */
@Data
public class PageResult {
    private Long total;
    private List<HotelDoc> hotels;

    public PageResult() {
    }

    public PageResult(Long total, List<HotelDoc> hotels) {
        this.total = total;
        this.hotels = hotels;
    }
}
