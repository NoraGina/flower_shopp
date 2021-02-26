package com.gina.flowerShop.web.controller;

import com.gina.flowerShop.model.Customer;
import com.gina.flowerShop.model.OrderCustomer;
import com.gina.flowerShop.model.OrderItem;
import com.gina.flowerShop.model.Status;
import com.gina.flowerShop.repository.*;
import com.gina.flowerShop.service.OrderCustomerService;
import com.gina.flowerShop.service.ProductService;
import com.gina.flowerShop.web.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerOrderController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired private ProductService productService;
    @Autowired private ProductRepository productRepository;
    @Autowired private OrderItemRepository orderItemRepository;
    @Autowired private HttpSession httpSession;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private ShippingAddressRepository shippingAddressRepository;
    @Autowired private OrderCustomerService orderCustomerService;

    @GetMapping("/by/product/{idProduct}")
    public String byProduct(@PathVariable(value = "idProduct", required = false)Long idProduct, @AuthenticationPrincipal UserDetails currentUser,
                            Model model){
        try{
            Customer customer =customerRepository.findByUsername(currentUser.getUsername());
            if(customer !=null){
                if(httpSession.getAttribute("order")==null){
                    List<OrderItem>orderItemList = new ArrayList<>();
                    OrderCustomer orderCustomer = new OrderCustomer();

                    OrderItem orderItem = new OrderItem(productRepository.findProductByIdProduct(idProduct), 1, orderCustomer);

                    orderItemList.add(orderItem);
                    orderCustomer.setStatus(Status.AFFECTED);
                    orderCustomer.setDate(LocalDate.now());
                    orderCustomer.setTime(LocalTime.now());
                    orderCustomer.setOrderItemList(orderItemList);

                    orderCustomer.setCustomer(customer);
                    httpSession.setAttribute("order", orderCustomer);
                    model.addAttribute("orderCustomer", orderCustomer);


                }else{
                    OrderCustomer orderCustomer = (OrderCustomer) httpSession.getAttribute("order");
                    List<OrderItem>orderItems = orderCustomer.getOrderItemList();
                    int index = this.exists(idProduct,  orderItems);
                    if(index == -1){
                        OrderItem newOrderItem = new OrderItem(productRepository.findById(idProduct).get(), 1);
                        orderItems.add(newOrderItem);

                        newOrderItem.setOrderCustomer(orderCustomer);
                    }else{
                        int quantity = ( orderItems).get(index).getQuantity()+1;
                        orderItems.get(index).setQuantity(quantity);

                    }
                    orderCustomer.setOrderItemList(orderItems);
                    httpSession.setAttribute("order", orderCustomer);
                    model.addAttribute("orderCustomer", orderCustomer);

                }

                return "redirect:/customer/byPage";
            }else{
                model.addAttribute("customer", new CustomerDto());
                return "customer-registration";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return "index";


    }

    private int exists(Long idProduct, List<OrderItem> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getIdProduct().equals(idProduct)) {
                return i;
            }
        }
        return -1;
    }

}
