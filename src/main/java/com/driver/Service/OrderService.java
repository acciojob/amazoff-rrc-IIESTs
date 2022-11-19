package com.driver.Service;


import com.driver.Order;
import com.driver.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public void addOrder(Order order){
        String id = order.getId();

        orderRepository.orderDetails.put(id,order);
    }

    public Order getOrderById(String id){
        return orderRepository.orderDetails.getOrDefault(id, null);

    }

    public List<String> getAllOrders(){
        List<String> allOrders = new ArrayList<>();
        for(Map.Entry<String,Order> e: orderRepository.orderDetails.entrySet() ){
           allOrders.add(e.getKey()) ;
        }
        return allOrders;
    }



}
