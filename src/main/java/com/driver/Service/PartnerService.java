package com.driver.Service;


import com.driver.DeliveryPartner;
import com.driver.Repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartnerService {

    @Autowired
    PartnerRepository partnerRepository;

    public void addPartner(String partnerId){
        DeliveryPartner deliveryPartner = new DeliveryPartner(partnerId);
        partnerRepository.deliveryPartner.put(partnerId,deliveryPartner);
    }

    public DeliveryPartner getPartner(String partnerId){
        return partnerRepository.deliveryPartner.getOrDefault(partnerId,null) ;
    }

}
