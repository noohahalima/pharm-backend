package com.code.ordersmicroservice.controller;

import com.code.ordersmicroservice.dto.OrderDTO;
import com.code.ordersmicroservice.dto.ResponseOrderDTO;
import com.code.ordersmicroservice.entity.Drugs;
import com.code.ordersmicroservice.entity.Order;
import com.code.ordersmicroservice.entity.User;
import com.code.ordersmicroservice.repository.OrderRepository;
import com.code.ordersmicroservice.service.OrderService;
import com.code.ordersmicroservice.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService service;

    @Autowired
    private OrderRepository repo;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(OrderController.class);


    @GetMapping("/getall")
    public List<Order> getOrder() {
        return service.getOrder();
    }

    @PostMapping("/register")
    public ResponseEntity<String> insertOrder(@RequestBody Order order) {
        System.out.println("Order generated");
        service.addOrder(order);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @DeleteMapping("/deleteorder/{orderId}")
    public ResponseEntity<String> deleteOrderById(@PathVariable("orderId") String orderId) {
        service.deleteOrder(orderId);
        return new ResponseEntity<String>("Delete-successfully", HttpStatus.OK);
    }


    @GetMapping("list/{id}")
    public ResponseEntity <Object> getOrderById(@PathVariable String orderId) {
        try {
            return ResponseEntity.ok().body(this.service.getOrderById(orderId));
        }
        catch(Exception e)
        {
            return new ResponseEntity<Object>("Order not found with id"+orderId,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getemail/{emailId}")
    public Order getByEmailOrder(@PathVariable("emailId") String emailId) {
        return service.getOrderByEmail(emailId);
    }




    @PostMapping("/placeOrder")
    public ResponseEntity<ResponseOrderDTO> placeOrder(@RequestBody OrderDTO orderDTO) {
        logger.info("Request Payload " + orderDTO.toString());
        ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO();
        float amount = service.getCartAmount(orderDTO.getCartItems());



       User user = new User(orderDTO.getUserName(), orderDTO.getUserEmail());

        String userIdFromDb =restTemplate.postForObject("http://localhost:9091/user/ispresent/"+user,user, String.class);
        if (userIdFromDb != null) {
            user.setId(userIdFromDb);
            logger.info("Customer already present in db with id : " + userIdFromDb);
        }else{

            logger.info("Customer not present.. with id : " + user.getId());
        }
        Order order = new Order(user, orderDTO.getCartItems());
        order = service.saveOrder(order);
        logger.info("Order processed successfully..");

        responseOrderDTO.setAmount(amount);
        responseOrderDTO.setDate(DateUtil.getCurrentDateTime());
        responseOrderDTO.setInvoiceNumber(new Random().nextInt(1000));
        responseOrderDTO.setOrderId(order.getOrderId());

        logger.info("test push..");

        return ResponseEntity.ok(responseOrderDTO);
    }

//    @GetMapping("showorderbyid/{orderId}")
//    public ShoppingCart getFullOrder(@PathVariable("orderId") String orderId) throws Exception {
//        Order order=service.getOrderById(orderId);
//        String useremail=order.getEmailId();
//        RestTemplate restTemplate = new RestTemplate();
//        User user=restTemplate.getForObject("http://localhost:9091/user-service/user/listbyemail/"+useremail,User.class);
//        String drugname=order.getDrugName();
//        Drugs drugs=restTemplate.getForObject("http://localhost:8020/supplier-inventory-service/drugs/listbydrugname/"+drugname,Drugs.class);
//        ShoppingCart userOrder=new ShoppingCart();
//        userOrder.setDrug(drugs);
//        userOrder.setUser(user);
//        return userOrder;
//    }
}