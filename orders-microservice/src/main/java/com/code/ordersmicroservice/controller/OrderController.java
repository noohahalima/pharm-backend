package com.code.ordersmicroservice.controller;


import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/order")
public class OrderController {
//    @Autowired
//    private OrderService service;
//
//    @Autowired
//    private OrderRepository repo;
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    private Logger logger = LoggerFactory.getLogger(OrderController.class);
//
//
//    @GetMapping("/getall")
//    public List<Order> getOrder() {
//        return service.getOrder();
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<String> insertOrder(@RequestBody Order order) {
//        System.out.println("Order generated");
//        service.addOrder(order);
//        return new ResponseEntity<String>("Success", HttpStatus.OK);
//    }
//
//    @DeleteMapping("/deleteorder/{orderId}")
//    public ResponseEntity<String> deleteOrderById(@PathVariable("orderId") String orderId) {
//        service.deleteOrder(orderId);
//        return new ResponseEntity<String>("Delete-successfully", HttpStatus.OK);
//    }
//
//
//    @GetMapping("list/{id}")
//    public ResponseEntity <Object> getOrderById(@PathVariable String orderId) {
//        try {
//            return ResponseEntity.ok().body(this.service.getOrderById(orderId));
//        }
//        catch(Exception e)
//        {
//            return new ResponseEntity<Object>("Order not found with id"+orderId,HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @GetMapping("/getemail/{emailId}")
//    public Order getByEmailOrder(@PathVariable("emailId") String emailId) {
//        return service.getOrderByEmail(emailId);
//    }
//
//
//
//
//    @PostMapping("/placeOrder")
//    public ResponseEntity<ResponseOrderDTO> placeOrder(@RequestBody OrderDTO orderDTO) {
//        logger.info("Request Payload " + orderDTO.toString());
//        ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO();
//        float amount = service.getCartAmount(orderDTO.getCartItems());
//       User user = new User(orderDTO.getUserName(), orderDTO.getUserEmail());
//
//        Order order = new Order(user, orderDTO.getCartItems());
//        order = service.saveOrder(order);
//        logger.info("Order processed successfully..");
//
//        responseOrderDTO.setAmount(amount);
//        responseOrderDTO.setDate(DateUtil.getCurrentDateTime());
//        responseOrderDTO.setInvoiceNumber(new Random().nextInt(1000));
//        responseOrderDTO.setOrderId(order.getOrderId());
//
//        logger.info("test push..");
//
//        return ResponseEntity.ok(responseOrderDTO);
//    }
//



}