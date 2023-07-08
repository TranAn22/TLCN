package com.ecommerce.admin.admin.controller;

import com.ecommerce.library.library.dto.AdminDto;
import com.ecommerce.library.library.model.Admin;
import com.ecommerce.library.library.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class LoginController {
    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private BCryptPasswordEncoder   passwordEncoder;

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("title","Login");
        return "login";
    }

    @RequestMapping("/index")
    public String home(Model model, Principal principal){
        model.addAttribute("title", "Home Page");
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
//            return "redirect:/login";
//        }
//        Tuong tu phia tren
        if(principal==null)
        {
            return "redirect:/login";
        }
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model,HttpSession session){
        model.addAttribute("title","Register");
        model.addAttribute("adminDto",new AdminDto());
        session.removeAttribute("message");
        return "register";
    }
    @GetMapping("/forgot-password")
    public String forgotPassword(Model model){
        model.addAttribute("title","Forgot Password");
        return "forgot-password";
    }



    @PostMapping("/register-new")
    public String addNewAdmin(@Valid @ModelAttribute("adminDto")AdminDto adminDto,
                              BindingResult result,
                              Model model){
        try {

            if (result.hasErrors()) {
                model.addAttribute("adminDto", adminDto);
                result.toString();
                return "register";
            }
            String username = adminDto.getUsername();
            Admin admin = adminService.findByUsername(username);
            if (admin != null) {
                model.addAttribute("adminDto",adminDto);
                System.out.println("Admin is not null");
                model.addAttribute("emailError","You email has been registed");
                return "register";
            }
            if(adminDto.getPassword().equals(adminDto.getRepeatPassword())){
                adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));
                adminService.save(adminDto);
                System.out.println("Success");
                model.addAttribute("success","Register Succesfully!");
                model.addAttribute("adminDto",adminDto);
            }
            else{
                model.addAttribute("adminDto",adminDto);
                model.addAttribute("passwordError","Wrong password re-type!");
                System.out.println("Password is not the same");
                return "register";
            }

        }
        catch (Exception e){
            e.printStackTrace();
            model.addAttribute("errors","The server has an issue!");
        }

        return "register";
    }

}
