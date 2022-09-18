package com.code.ordersmicroservice.repository;


import com.code.ordersmicroservice.entity.Cart;
import com.code.ordersmicroservice.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CartRepository extends MongoRepository<Cart, Integer> {

    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
}

