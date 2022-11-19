package com.driver.Repository;

import com.driver.DeliveryPartner;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class PartnerRepository {

    public HashMap<String, DeliveryPartner> deliveryPartner = new HashMap<>();


}
