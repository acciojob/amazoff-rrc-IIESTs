package com.driver.Repository;

import com.driver.DeliveryPartner;
import com.driver.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class OrderPartnerRepository {
   public HashMap<String, String> orderToPartner = new HashMap<>();

   public HashMap<String, List<String>> ordersOfPartner = new HashMap<>();
}
