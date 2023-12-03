package com.ecommerce.customer.controller;

import com.ecommerce.library.library.model.Customer;
import com.ecommerce.library.library.model.Order;
import com.ecommerce.library.library.model.ShoppingCart;
import com.ecommerce.library.library.service.CityService;
import com.ecommerce.library.library.service.CustomerService;
import com.ecommerce.library.library.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CityService cityService;

    @Autowired
    private OrderService orderService;


    @GetMapping("/check-out")
    public String checkout(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);
        if (customer.getPhoneNumber().trim().isEmpty() || customer.getAddress().trim().isEmpty()
                || customer.getCity().trim().isEmpty() || customer.getCountry().trim().isEmpty()) {

            model.addAttribute("customer", customer);
            model.addAttribute("error", "You must fill the information after checkout!");
            return "account";
        } else {
            model.addAttribute("customer", customer);
            ShoppingCart cart = customer.getShoppingCart();
            model.addAttribute("cart", cart);
            model.addAttribute("order",new Order());
        }
        return "checkout";
    }


    @GetMapping("/order")
    public String order(Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/login";
        }
        String username=principal.getName();
        Customer customer=customerService.findByUsername(username);
        List<Order> orderList=customer.getOrders();
        model.addAttribute("orders",orderList);
        return "order";
    }

    @PostMapping("/save-order")
    public String saveOrder(@ModelAttribute("order") Order order,
                            @RequestParam("city") String city,
                            @RequestParam("district") String district,
                            @RequestParam("ward") String ward,
            Principal principal){
        if(principal==null){
            return "redirect:/login";
        }
        String username=principal.getName();
        Customer customer=customerService.findByUsername(username);
        order.setCity(city);
        order.setDistrict(district);
        order.setWard(ward);
        ShoppingCart cart=customer.getShoppingCart();
        orderService.saveOrder(cart, order);
        return "redirect:/order";
    }

}