package com.driver.Service;


import com.driver.Repository.PartnerRepository;
import com.driver.Repository.OrderPartnerRepository;
import com.driver.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OrderPartnerService {

    @Autowired
    OrderPartnerRepository orderPartnerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PartnerRepository partnerRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    PartnerService partnerService;

    public void addOrderPartner(String orderId, String partnerId){
        orderPartnerRepository.orderToPartner.put(orderId,partnerId);
        if(!partnerRepository.deliveryPartner.containsKey(partnerId)){
            partnerService.addPartner(partnerId);
        }
        int noOfOrders = partnerRepository.deliveryPartner.get(partnerId).getNumberOfOrders()+1;
        partnerRepository.deliveryPartner.get(partnerId).setNumberOfOrders(noOfOrders);
        orderPartnerRepository.ordersOfPartner.get(partnerId).add(orderId);
    }
    public int getNumberOfOrders(String partnerId){
        if(partnerRepository.deliveryPartner.containsKey(partnerId)) {
            return partnerRepository.deliveryPartner.get(partnerId).getNumberOfOrders();
        }else{
            return 0;
        }
    }

    public List<String> getOrderList(String partnerId){
      return orderPartnerRepository.ordersOfPartner.getOrDefault(partnerId,new ArrayList<>());
    }
    public int countUnassignedOrders(){
        int totalOrders = orderRepository.orderDetails.size();
        int assignedOrders = orderPartnerRepository.orderToPartner.size();
        return totalOrders-assignedOrders;
    }
    public int countOrdersAfterTime(String time,String partnerId){
        int currTime = convertTimeToInt(time);
        List<Integer> deliveryTimes = new ArrayList<>();
        List<String> orders = orderPartnerRepository.ordersOfPartner.getOrDefault(partnerId,new ArrayList<>());
        for(String o :orders){
          deliveryTimes.add(orderRepository.orderDetails.get(o).getDeliveryTime());
        }
        int count=0;
        for(int i:deliveryTimes){
            if(i>currTime){
                count++;
            }
        }
        return count;
    }
    public String lastDeliveryTime(String partnerId){
        List<Integer> deliveryTimes = new ArrayList<>();
        List<String> orders = orderPartnerRepository.ordersOfPartner.getOrDefault(partnerId,new ArrayList<>());
        for(String o :orders){
            deliveryTimes.add(orderRepository.orderDetails.get(o).getDeliveryTime());
        }
        if(deliveryTimes.size()==0){
            return null;
        }else{
            int lastTime = deliveryTimes.get(0);

            for(Integer time: deliveryTimes){
                if(time>lastTime){
                    lastTime=time;
                }
            }
            return convertTimeToString(lastTime);
        }

    }
    public void deletePartner(String partnerId){
        partnerRepository.deliveryPartner.remove(partnerId);
        List<String> ordersOfPartner = getOrderList(partnerId);
        orderPartnerRepository.ordersOfPartner.remove(partnerId);
        for(String o : ordersOfPartner) {
            orderPartnerRepository.orderToPartner.remove(o);
        }
    }

    public void deleteOrder(String orderId){
        orderRepository.orderDetails.remove(orderId);
        if(orderPartnerRepository.orderToPartner.containsKey(orderId)){
            String partnerId = orderPartnerRepository.orderToPartner.get(orderId);
            orderPartnerRepository.orderToPartner.remove(orderId);
            List<String> orders = orderPartnerRepository.ordersOfPartner.get(partnerId);
            for(int i=0;i<orders.size();i++) {
                if(orders.get(i).equals(orderId)) {
                    orders.remove(i);
                    break;
                }
            }
            orderPartnerRepository.ordersOfPartner.put(partnerId,orders);
        }

    }
    public int convertTimeToInt(String time){
        int hours = Integer.parseInt(time.substring(0,2));
        int mins = Integer.parseInt(time.substring(3,5));
        int currTime = hours*60+mins;
        return currTime;
    }
    public String convertTimeToString(int time){
        String hour = Integer.toString(time/60);
        String mins = Integer.toString(time%60);
        String currTime = hour+":"+mins;
        return currTime;

    }
}
