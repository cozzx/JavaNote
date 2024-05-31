package com.cozz.es.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cozz.es.mapper.HotelMapper;
import com.cozz.es.pojo.Hotel;
import com.cozz.es.service.HotelService;
import org.springframework.stereotype.Service;

/**
 * @author zt
 * @date 2024/4/6
 **/
@Service
public class HotelServiceImpl extends ServiceImpl<HotelMapper, Hotel> implements HotelService {
}
