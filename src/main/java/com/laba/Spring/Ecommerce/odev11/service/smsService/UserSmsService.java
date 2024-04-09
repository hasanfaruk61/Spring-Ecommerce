package com.laba.Spring.Ecommerce.odev11.service.smsService;

import com.laba.Spring.Ecommerce.odev11.entity.Users;
import com.laba.Spring.Ecommerce.odev11.service.ReplaceFunction;
import org.springframework.stereotype.Service;

@Service
public class UserSmsService {

    public void sendSmsUser(Users user) {

        ReplaceFunction replaceFunction = (template, name, phoneNumber) -> template.replace("NAME", name).replace("PHONENUMBER", phoneNumber);
        String name = user.getName();
        String phoneNumber = user.getPhoneNumber();

        String smsBody = " Dear Name your personal information has been changed successfully. ";
        smsBody = replaceFunction.replace(smsBody, name, phoneNumber);

        if (isPremiumUser(user)) {
            sendPremiumSms(smsBody);
        } else {
            sendRegularSms(smsBody);
        }
    }

    public boolean isPremiumUser(Users user) {
        return user.isPremium();
    }


    //Premium user
    private void sendPremiumSms(String smsBody) {
        System.out.println("Premium SMS sent: " + smsBody);
    }

    //Regular user
    private void sendRegularSms(String smsBody) {
        System.out.println("Regular SMS sent: " + smsBody);
    }
}
