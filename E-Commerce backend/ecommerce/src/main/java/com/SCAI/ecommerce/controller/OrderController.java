package com.SCAI.ecommerce.controller;

import com.SCAI.ecommerce.service.OrderService;
import com.SCAI.ecommerce.service.UserService;
import com.SCAI.ecommerce.model.Ordine;
import com.SCAI.ecommerce.model.Product;
import com.SCAI.ecommerce.model.User;
import com.SCAI.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @GetMapping("/findAll")
    public List<Ordine> findAll(@RequestHeader("Authorization") String jwt) throws Exception{
        List<Ordine> ordines = orderService.findAll(jwt);
        return ordines;
    }

    @PostMapping("/insert/{userId}/{productId}")
    public Ordine insertOrder(@RequestBody Ordine ordine,
                              @PathVariable Long userId,
                              @PathVariable Long productId) throws Exception{
        User user = userService.findUserById(userId);
        Product product = productService.findProductById(productId);
        Ordine insertedOrdine = orderService.insertOrder(ordine, user, product);
        return insertedOrdine;
    }

    @DeleteMapping("/delete/{orderId}")
    public String deleteOrder(@PathVariable Long orderId,
                              @RequestHeader("Authorization") String jwt) throws Exception{
        orderService.deleteOrder(orderId, jwt);
        return "Order Deleted Successfully";
    }

    @PutMapping("/update/{id}")
    public Ordine updateOrder(@RequestBody Ordine ordine,
                              @PathVariable Long id) throws Exception{
        Ordine updatedOrdine = orderService.updateOrder(ordine, id);
        return updatedOrdine;
    }

}
