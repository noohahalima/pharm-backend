package com.code.ordersmicroservice.service;


import com.code.ordersmicroservice.entity.Drugs;
import com.code.ordersmicroservice.entity.Order;
import com.code.ordersmicroservice.entity.ShoppingCart;
import com.code.ordersmicroservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;
    @Autowired
    private RestTemplate restTemplate;

    public Order addOrder(Order order) {
        return repository.save(order);
    }

    public List<Order> getOrder() {
        List<Order> order = repository.findAll();
        System.out.println("Getting data from DB : " + order);
        return order;
    }

    public void deleteOrder(String orderId) {
        repository.deleteById(orderId);
    }

    public Order getOrderById(String orderId) throws Exception {

        Optional <Order> op = this.repository.findById(orderId);

        if (op.isPresent()) {
            return op.get();

        } else {
            throw new Exception("order not found");
        }
    }

    public Order getOrderByEmail(String emailId) {
        return repository.findByEmailId(emailId);
    }





    public Order getOrderDetail(String orderId) {
        Optional<Order> order = this.repository.findById(orderId);
        return order.isPresent() ? order.get() : null;
    }

    public float getCartAmount(List<ShoppingCart> shoppingCartList) {

        float totalCartAmount = 0f;
        float singleCartAmount = 0f;
        int availableQuantity = 0;

        for (ShoppingCart cart : shoppingCartList) {

            int drugId = cart.getDrugId();
            RestTemplate restTemplate = new RestTemplate();
            Drugs drugs=restTemplate.getForObject("http://localhost:8020/supplier-inventory-service/drugs/list/"+drugId,Drugs.class);
//            if (drugs().isPresent) {
//                Drugs drug1 = drugs.get();
            if(drugs!=null){
                Drugs drug1=drugs;
                if (drug1.getQuantity() < cart.getQuantity()) {
                    singleCartAmount = (float) (drug1.getPrice() * drug1.getQuantity());
                    cart.setQuantity(drug1.getQuantity());
                } else {
                    singleCartAmount = (float) (cart.getQuantity() * drug1.getPrice());
                    availableQuantity = drug1.getQuantity() - cart.getQuantity();
                }
                totalCartAmount = totalCartAmount + singleCartAmount;
                drug1.setQuantity(availableQuantity);
                availableQuantity=0;
                cart.setDrugName(drug1.getDrugName());
                cart.setAmount(singleCartAmount);
                 restTemplate.postForObject("http://localhost:9092/drugs/add",drug1, String.class);

            }
        }
        return totalCartAmount;
    }

    public Order saveOrder(Order order) {
        return repository.save(order);
    }
}

