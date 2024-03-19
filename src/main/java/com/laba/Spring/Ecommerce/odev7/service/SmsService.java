package com.laba.Spring.Ecommerce.odev7.service;

import com.laba.Spring.Ecommerce.odev7.entity.Order;
import com.laba.Spring.Ecommerce.odev7.entity.Users;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    public void sendSmsUser(Order order, Users users) {
       ReplaceFunction replaceFunction = (template, name, orderNumber) -> template.replace("NAME", name).replace("ORDERNUMBER", orderNumber);

       String orderNumber = order.getOrderNumber();
       String name = users.getName();
       String phoneNumber = users.getPhoneNumber();

       String smsBody = "Dear NAME your order with order number: ORDERNUMBER has been received. Thank you for your order. ";
       smsBody = replaceFunction.replace(smsBody, name, orderNumber);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
