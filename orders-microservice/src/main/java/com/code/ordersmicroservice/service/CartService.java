package com.code.ordersmicroservice.service;


import com.code.ordersmicroservice.dto.AddToCartDto;
import com.code.ordersmicroservice.dto.CartDto;
import com.code.ordersmicroservice.dto.CartItemDto;
import com.code.ordersmicroservice.entity.Cart;
import com.code.ordersmicroservice.entity.Drugs;
import com.code.ordersmicroservice.entity.User;
import com.code.ordersmicroservice.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

//    @Autowired
//    ProductService productService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void addToCart(AddToCartDto addToCartDto, User user) {

        // validate if the product id is valid
       // Product product = productService.findById(addToCartDto.getProductId());
        RestTemplate restTemplate = new RestTemplate();
        Drugs drugs=restTemplate.getForObject("http://localhost:9092/drugs/list/"+addToCartDto.getDrugId(),Drugs.class);
        System.out.println("nooooooo");
        Cart cart = new Cart();
        cart.setDrug(drugs);
        cart.setUser(user);
        cart.setQuantity(addToCartDto.getQuantity());
//        cart.setCreatedDate(drugs.getExpiryDate());


        // save the cart
        cartRepository.save(cart);

    }

    public CartDto listCartItems(User user) {
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);

        List<CartItemDto> cartItems = new ArrayList<>();
        double totalCost = 0;
        for (Cart cart: cartList) {
            CartItemDto cartItemDto = new CartItemDto(cart);
            totalCost += cartItemDto.getQuantity() * cart.getDrug().getPrice();
            cartItems.add(cartItemDto);
        }

        CartDto cartDto = new CartDto();
        cartDto.setTotalCost(totalCost);
        cartDto.setCartItems(cartItems);
        return  cartDto;
    }

    public void deleteCartItem(Integer cartItemId, User user) throws Exception {
        // the item id belongs to user

        Optional<Cart> optionalCart = cartRepository.findById(cartItemId);

        if (optionalCart.isEmpty()) {
            throw new Exception("cart item id is invalid: " + cartItemId);
        }

        Cart cart = optionalCart.get();

        if (cart.getUser() != user) {
            throw  new Exception("cart item does not belong to user: " +cartItemId);
        }

        cartRepository.delete(cart);


    }
}
