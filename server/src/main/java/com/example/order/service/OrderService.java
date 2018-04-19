package com.example.order.service;

import com.example.order.dto.OrderDTO;

public interface OrderService {

    /**
     * 创建订单
     */
    OrderDTO create(OrderDTO orderDTO);
}
