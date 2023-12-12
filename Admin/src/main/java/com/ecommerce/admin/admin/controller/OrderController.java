package com.ecommerce.admin.admin.controller;

import com.ecommerce.library.library.model.Order;
import com.ecommerce.library.library.model.OrderDetail;
import com.ecommerce.library.library.repository.OrderDetailRepository;
import com.ecommerce.library.library.service.OrderDetailService;
import com.ecommerce.library.library.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderDetailService orderDetailService;

    @GetMapping("/orders")
    public String getAll(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        } else {
            List<Order> orderList = orderService.findAllOrders();
            List<OrderDetail> orderDetailList=orderDetailService.findAllOrderDeatails();
            model.addAttribute("orders", orderList);
            model.addAttribute("orderDetails",orderDetailList);
            return "orders";
        }
    }


    @RequestMapping(value = "/accept-order", method = {RequestMethod.PUT, RequestMethod.GET})
    public String acceptOrder(Long id, RedirectAttributes attributes, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        } else {
            orderService.acceptOrder(id);
            attributes.addFlashAttribute("success", "Order Accepted");
            return "redirect:/orders";
        }
    }

    @RequestMapping(value = "/cancel-order", method = {RequestMethod.PUT, RequestMethod.GET})
    public String cancelOrder(Long id, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        } else {
            orderService.cancelOrder(id);
            return "redirect:/orders";
        }
    }


}