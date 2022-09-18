package com.code.ordersmicroservice.controller;


import com.code.ordersmicroservice.common.ApiResponse;
import com.code.ordersmicroservice.dto.AddToCartDto;
import com.code.ordersmicroservice.dto.CartDto;
import com.code.ordersmicroservice.entity.User;
import com.code.ordersmicroservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto,
                                                 @RequestParam("userid") String userid) {

        // find the user
        RestTemplate restTemplate = new RestTemplate();
        User user=restTemplate.getForObject("http://localhost:9091/user/list/"+userid,User.class);
       // System.out.println("nuha halima");
        cartService.addToCart(addToCartDto, user );

        return new ResponseEntity<>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
    }



    // get all cart items for a user
    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("userid") String userid) {
        // authenticate the token
//        authenticationService.authenticate(token);
//
//        // find the user
//        User user = authenticationService.getUser(token);
        RestTemplate restTemplate = new RestTemplate();
        User user=restTemplate.getForObject("http://localhost:9091/user/list/"+userid,User.class);
        // get cart items

        CartDto cartDto = cartService.listCartItems(user);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    // delete a cart item for a user

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") Integer itemId,
                                                      @RequestParam("userid") String userid) throws Exception {

        // authenticate the token
        RestTemplate restTemplate = new RestTemplate();
        User user=restTemplate.getForObject("http://localhost:9091/user/list/"+userid,User.class);

        cartService.deleteCartItem(itemId, user);

        return new ResponseEntity<>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);

    }

}