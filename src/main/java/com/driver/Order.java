package com.driver;

public class Order {


    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        int hours = Integer.parseInt(deliveryTime.substring(0,2));
        int mins = Integer.parseInt(deliveryTime.substring(3,5));
        int time = hours*60+mins;
        setDeliveryTime(time);
        setId(id);
        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setDeliveryTime(int time) {
        this.deliveryTime = time;
    }


    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
