package com.code.ordersmicroservice.repository;

import com.code.ordersmicroservice.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order,String> {
    @Query("{'orderId':?0}")
    Order findByOrderId(String orderId);


    @Query("{'emailId':?0}")
    Order findByEmailId(String emailId);

}
