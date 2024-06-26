package com.laba.Spring.Ecommerce.odev11.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@Data
public class Users extends BaseEntity{
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private boolean premium = false;
}
