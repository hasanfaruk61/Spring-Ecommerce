package com.laba.Spring.Ecommerce.odev11.service;

@FunctionalInterface
public interface ReplaceFunction {
    String replace(String template,String name, String orderNumber);
}
