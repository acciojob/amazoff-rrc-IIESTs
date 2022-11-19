package com.driver.Repository;


import com.driver.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class OrderRepository {
    public HashMap<String , Order> orderDetails = new HashMap<>();



}
