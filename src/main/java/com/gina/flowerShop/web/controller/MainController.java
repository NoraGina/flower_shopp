package com.gina.flowerShop.web.controller;

import com.gina.flowerShop.service.UserService;
import com.gina.flowerShop.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @Autowired private UserService userService;
    //@Autowired private CustomerService customerService;
    /*@Autowired
    private ProductService productService;
    @Autowired
    private CategoryRepository categoryRepository;*/

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @GetMapping("/")
    public String homePage(Model model){
        //model.addAttribute("products", productService.findAll());
        //model.addAttribute("categoryList", categoryRepository.findByOrderByCategoryNameAsc());

        return "index";
    }

    @GetMapping("/customer/byPage")
    public String byPage(Model model, @AuthenticationPrincipal UserDetails currentUser){
        /*CustomerDto customerDto = customerService.findByUsername(currentUser.getUsername());
        model.addAttribute("customer", customerDto);
        model.addAttribute("products", productService.findAll());
        model.addAttribute("categoryList", categoryRepository.findByOrderByCategoryNameAsc());
        List<String> origins = new ArrayList<>();
        for(String s : productService.findDistinctOrigin()){
            s = s.replaceAll(",", "");
            origins.add(s);
        }
        model.addAttribute("origins",origins);*/
        return "customer-byPage";
    }

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal UserDetails currentUser, Model model ){
        // SecurityContext context = SecurityContextHolder.getContext();

        // model.addAttribute("message", "You are logged in as "
        //         + context.getAuthentication().getName());
        UserDto user= userService.findByUsername(currentUser.getUsername());
        model.addAttribute("currentUser", user);

        return "dashboard";
    }
}
