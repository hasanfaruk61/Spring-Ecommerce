package com.laba.Spring.Ecommerce.odev7.repository;


import com.laba.Spring.Ecommerce.odev7.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {

}
