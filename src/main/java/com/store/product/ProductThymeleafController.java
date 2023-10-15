package com.store.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping(path = "/api/v2/product")
public class ProductThymeleafController {

    private final ProductService productService;

    @Autowired
    public ProductThymeleafController(ProductService productService) {
        this.productService = productService;
    }

   @GetMapping(path = "/list")
    public String listProducts(Model model){
        List<Product> products = productService.getProducts();
        model.addAttribute("products", products);
        return "product-list";
   }

   @GetMapping(path = "test")
    public String test(Model model){
        model.addAttribute("message", "Hello World!");
        return "hello-world";
   }
}
